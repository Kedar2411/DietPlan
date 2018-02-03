package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
    }
    public void gotoactivity_diet_plan(View v) {
        Intent ActivityPage = new Intent(this, DietPlan.class);
        startActivity(ActivityPage);
    }
    public void gotoactivity_my_profile(View v) {
        Intent ActivityPage = new Intent(this, MyProfile.class);
        startActivity(ActivityPage);
    }
    public void gotoactivity_feed_back(View v) {
        Intent ActivityPage = new Intent(this, FeedBack.class);
        startActivity(ActivityPage);
    }
}
