package com.example.myapp2;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class MainActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_json();
    }

    public void get_json() {
        final String[] json = new String[1];

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://fetch-hiring.s3.amazonaws.com/hiring.json";

        JsonArrayRequest
                jsonArrayRequest
                = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("response", response.toString());

                        String json = response.toString();

                        Gson gson = new Gson();
                        List<ListId> list = gson.fromJson(json, new TypeToken<List<ListId>>(){}.getType());
                        //grouping by listId
                        Map<Integer, List<ListId
                                >> groupedMap = list.stream().collect(Collectors.groupingBy(ListId::getListId));

                        //Sorting

                        Collections.sort(list, new Comparator<ListId>() {
                            @Override
                            public int compare(ListId l1, ListId l2) {
                                return Integer.compare(l1.getListId(), l2.getListId());
                            }
                        });

                        /*Collections.sort(list, new Comparator<ListId>() {
                            @Override
                            public int compare(ListId l1, ListId l2) {

                                    return l1.getName().compareTo(l2.getName());

                            }
                        });*/

                        //filtering

                        List<ListId> listFilter = list.stream()
                                .filter(l -> l.getName() == null || l.getName().equals("")).collect(Collectors.toList());


                        Log.e("group", groupedMap.toString());
                        Toast.makeText(getApplicationContext(), groupedMap.toString(), Toast.LENGTH_LONG).show();

                        Log.e("sort", list.toString());
                        Toast.makeText(getApplicationContext(), list.toString(), Toast.LENGTH_LONG).show();


                        Log.e("filter", listFilter.toString());
                        Toast.makeText(getApplicationContext(), listFilter.toString(), Toast.LENGTH_LONG).show();






                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        queue.add(jsonArrayRequest);


    }
}