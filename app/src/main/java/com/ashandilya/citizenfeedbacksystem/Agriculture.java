package com.ashandilya.citizenfeedbacksystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Agriculture extends AppCompatActivity {

    //private static final String schemeList_URL = "http://192.168.0.12/MyApi/api.php";

    RecyclerView recyclerview;
    List<schemeList> schemeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agriculture);

        schemeList = new ArrayList<>();

        recyclerview = findViewById(R.id.recyclerView);
        recyclerview.setHasFixedSize(true);
       // recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager( new LinearLayoutManager(this));

        schemeList.add(
                new schemeList(
                        1,
                        "Pradhan Mantri Kisan Sampada Yojana",
                        "PM Kisan SAMPADA Yojana is a comprehensive package which will result in creation of modern infrastructure with efficient supply chain management from farm gate to retail outlet.",
                        R.drawable.kisansampda));

        schemeList.add(
                new schemeList(
                        1,
                        "Pradhan Mantri Fasal Bima Yojana",
                        "The PMFBY was launched in 2016 and replaces all the prevailing yield insurance schemes in India. The scheme has been launched with an impetus on crop sector. The scheme has extended coverage under localized risks, post-harvest losses etc. and aims at adoption of technology for the purpose of yield estimation. Through increased farmer awareness and low farmer premium rates the scheme aims at increasing the crop insurance penetration in India.",
                        R.drawable.cropinsurance));

        schemeList.add(
                new schemeList(
                        1,
                        "Pradhan Mantri Krishi Sinchai Yojana",
                        "Pradhan Mantri Krishi Sinchai Yojana is a national mission to improve farm productivity and ensure better utilization of the resources in the country. The budget of 253 billion (US$770 million) in a time span of one year 2015-2016 has been allocated to this scheme.",
                        R.drawable.pmksy));

        schemeList.add(
                new schemeList(
                        1,
                        "Soil Health Card Scheme",
                        "Soil Health Card (SHC) is a Government of India's scheme promoted by the Department of Agriculture & Co-operation under the Ministry of Agriculture and Farmers' Welfare. It is being implemented through the Department of Agriculture of all the State and Union Territory Governments.",
                        R.drawable.soilhealthcardinner));

        schemeList.add(
                new schemeList(
                        1,
                        "National Agriculture Market (NAM)",
                        "National Agriculture Market (NAM) is envisaged as a pan-India electronic trading portal which seeks to network the existing APMC and other market yards to create a unified national market for agricultural commodities. NAM will create a national network of physical mandis which can be accessed online.",
                        R.drawable.agrimarket));

        schemeList.add(
                new schemeList(
                        1,
                        "National livestock mission",
                        "National Livestock Mission is an initiative of the Ministry of Agriculture and Farmers’ Welfare. The mission, which commenced from 2014-15, has the objective of sustainable development of the livestock sector.",
                        R.drawable.nlsm));

        //creating recyclerview adapter
       schemeListAdapter adapter = new schemeListAdapter(this, schemeList);

        //setting adapter to recyclerview
        recyclerview.setAdapter(adapter);

       // loadSchemes();
    }

    public void schemeFeedback(View view) {
        Intent intent = new Intent(Agriculture.this, schemeFeedback.class);
        startActivity(intent);
        finish();
    }
//Dynamic recyclerView Method
//    private void loadSchemes() {
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, schemeList_URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONArray schemeListArray = new JSONArray(response);
//
//                    for ( int i = 0; i < schemeListArray.length();  i++){
//                        JSONObject schemeObjects = schemeListArray.getJSONObject(i);
//
//                        int scheme_ID = schemeObjects.getInt("ID");
//                        String scheme_Name = schemeObjects.getString("Scheme_Name");
//                        String scheme_Description = schemeObjects.getString("Scheme_Description");
//                        String scheme_Image = schemeObjects.getString("Scheme_Image");
//
//                        schemeList schemelist = new schemeList(scheme_ID, scheme_Name, scheme_Description,scheme_Image);
//                        schemeList.add(schemelist);
//                    }
//
//                    schemeListAdapter adapter = new schemeListAdapter(Agriculture.this,schemeList);
//                    recyclerview.setAdapter(adapter);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Agriculture.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Volley.newRequestQueue(this).add(stringRequest);
//    }
}
