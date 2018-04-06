package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lenovo.dietconsultant.model.FeedbackInfo;
import com.example.lenovo.dietconsultant.model.FoodInfo;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class FoodlistActivity extends AppCompatActivity {

    Spinner fruits, vegitables, salad, juice, roti, rice;

    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();


    ArrayList<String> fruits_str, vegitables_str, salad_str, juice_str, roti_str, rice_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        initViews();

        asyncHttpClient.get(Defaults.FOOD_URL, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                fruits_str = new ArrayList<String>();
                vegitables_str = new ArrayList<String>();
                salad_str = new ArrayList<String>();
                juice_str = new ArrayList<String>();
                roti_str = new ArrayList<String>();
                rice_str = new ArrayList<String>();


                List<FoodInfo> foodInfos = new ArrayList<>();
                Gson gson = new Gson();
                FoodInfo[] foodInfos1 = gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), FoodInfo[].class);

                for (FoodInfo foodInfo : foodInfos1) {
                    fruits_str.add(foodInfo.getFruits());
                    vegitables_str.add(foodInfo.getVegetable());
                    salad_str.add(foodInfo.getSalad());
                    juice_str.add(foodInfo.getJuice());
                    roti_str.add(foodInfo.getRoti());
                    rice_str.add(foodInfo.getRice());
                }

                ArrayAdapter<String> dataAdapter_fruits = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, fruits_str);
                ArrayAdapter<String> dataAdapter_vegitables= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, vegitables_str);
                ArrayAdapter<String> dataAdapter_juice = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, juice_str);
                ArrayAdapter<String> dataAdapter_rice = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, rice_str);
                ArrayAdapter<String> dataAdapter_roti = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, roti_str);
                ArrayAdapter<String> dataAdapter_salad= new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, salad_str);

                dataAdapter_fruits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                fruits.setAdapter(dataAdapter_fruits);
                dataAdapter_vegitables.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                vegitables.setAdapter(dataAdapter_vegitables);
               dataAdapter_juice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                juice.setAdapter(dataAdapter_juice);
                dataAdapter_rice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rice.setAdapter(dataAdapter_rice);
                dataAdapter_roti.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                roti.setAdapter(dataAdapter_roti);
                dataAdapter_salad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                salad.setAdapter(dataAdapter_salad);


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Could'nt retrive data, Please try again later !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        fruits = findViewById(R.id.spn_fruits);
        vegitables = findViewById(R.id.spn_vegitables);
        salad = findViewById(R.id.spn_salad);
        juice = findViewById(R.id.spn_juice);
        roti = findViewById(R.id.spn_roti);
        rice = findViewById(R.id.spn_rice);
    }

    public void gotoactivity_addfood(View v) {
        Intent ActivityPage = new Intent(this, AddfoodActivity.class);
        startActivity(ActivityPage);
    }
}
