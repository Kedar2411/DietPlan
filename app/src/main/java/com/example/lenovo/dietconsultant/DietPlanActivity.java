package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.dietconsultant.model.DietInfo;
import com.example.lenovo.dietconsultant.model.FoodInfo;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DietPlanActivity extends AppCompatActivity {

    TextView breakfast,lunch,snacks,dinner;

    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    Button change;

    ArrayList<String> breakfast_str, lunch_str, snacks_str, dinner_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        initViews();

        change=findViewById(R.id.change_diet);

       change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), RequestActivity.class));
            }
        });

        asyncHttpClient.get(Defaults.DIET_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
           Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();

                List<DietInfo> dietInfos = new ArrayList<>();
                Gson gson = new Gson();

                DietInfo[] dietInfos1 = gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), DietInfo[].class);

                for (DietInfo dietInfo : dietInfos) {

                    breakfast_str.add(dietInfo.getBreakfast());
                    lunch_str.add(dietInfo.getLunch());
                    snacks_str.add(dietInfo.getSnacks());
                    dinner_str.add(dietInfo.getDinner());
                }
                    //javaobj.getSome

                breakfast.setText(breakfast_str.get(0));
                //textv.settext(



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(),"failed badly...",Toast.LENGTH_SHORT).show();

            }
        });

        }

    private void initViews() {
        breakfast=findViewById(R.id.tv_breakfast);
        lunch=findViewById(R.id.tv_lunch);
        snacks=findViewById(R.id.tv_snacks);
        dinner=findViewById(R.id.tv_dinner);
    }
   /* public void gotoactivity_foodlist(View v) {
        Intent ActivityPage = new Intent(this, FoodlistActivity.class);
        startActivity(ActivityPage);
    }*/
}
