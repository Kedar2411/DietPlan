package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.lenovo.dietconsultant.model.DietInfo;
import com.example.lenovo.dietconsultant.model.FoodInfo;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class DietPlanActivity extends AppCompatActivity {

   TextView breakfast,lunch,snacks,dinner;

    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    Button change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        initViews();

        change = findViewById(R.id.change_diet);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), RequestActivity.class));
            }
        });

        asyncHttpClient.get(Defaults.DIET_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                List<DietInfo> dietInfos = new ArrayList<>();
                Gson gson = new Gson();
                DietInfo[] dietInfos1 = gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), DietInfo[].class);

                for (DietInfo dietInfo : dietInfos1)
                {

                      breakfast.setText(dietInfo.getBreakfast());
                      lunch.setText(dietInfo.getLunch());
                      snacks.setText(dietInfo.getSnacks());

                      dinner.setText(dietInfo.getDinner());

                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

   private void initViews() {
        breakfast=findViewById(R.id.tv_breakfast);
        lunch=findViewById(R.id.tv_lunch);
        snacks=findViewById(R.id.tv_snacks);
        dinner=findViewById(R.id.tv_dinner);
    }
}
