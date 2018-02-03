package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }
    public void gotoactivity_foodlist(View v) {
        Intent ActivityPage = new Intent(this, Foodlist.class);
        startActivity(ActivityPage);
    }

    public void gotoactivity_add_diet(View v) {
        Intent ActivityPage = new Intent(this, AddDiet.class);
        startActivity(ActivityPage);
    }
}
