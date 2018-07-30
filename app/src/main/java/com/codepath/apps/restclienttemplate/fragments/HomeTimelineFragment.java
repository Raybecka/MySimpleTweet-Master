package com.codepath.apps.restclienttemplate.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.TimelineActivity;
import com.codepath.apps.restclienttemplate.TweeterApp;
import com.codepath.apps.restclienttemplate.TweeterClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class HomeTimelineFragment extends TweetsListFragment {

    TweeterClient client;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = TweeterApp.getRestClient(getContext());
        populateTimeline();
    }
    private void  populateTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("TweeterClient", response.toString());
               // Toast.makeText(TimelineActivity.this, "the object" + response.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //Log.d("TweeterClient", response.toString());
                addItems(response);
                //iterate through the JSON array
                // for each entry, deserialize the JSON object
                // convert each object to a tweet model
                //add that tweet model to our datasource
                //notify the adapter that we've add an item

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TweeterClient", responseString);
               // Toast.makeText(TimelineActivity.this, "error log" + responseString.toString(), Toast.LENGTH_LONG).show();
                throwable.printStackTrace();
            }


        });
    }
}
