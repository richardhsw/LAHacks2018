package com.example.ryan.laparking;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class LotList extends Activity{
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_lot_list);
        lv = (ListView) findViewById(R.id.listView1);
        Intent i = getIntent();

        // Grab GEOJSON data from API
        RetrieveFeedTask get_geojson = new RetrieveFeedTask();
        get_geojson.execute();
        try
        {
            Thread.sleep(2500);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        String geojson = get_geojson.getGson();
        ParkingLot[] pl_list = new Gson().fromJson(geojson, LotsData.class).features;

        CustomAdapter adapter = new CustomAdapter(this, pl_list);
        lv.setAdapter(adapter);
    }
}
