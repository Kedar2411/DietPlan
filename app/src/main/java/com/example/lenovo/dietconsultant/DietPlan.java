package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DietPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);
    }
    public void gotoactivity_foodlist(View v) {
        Intent ActivityPage = new Intent(this, Foodlist.class);
        startActivity(ActivityPage);
    }

    public void gotoactivity_request(View v) {
        Intent ActivityPage = new Intent(this, Request.class);
        startActivity(ActivityPage);
    }
}
