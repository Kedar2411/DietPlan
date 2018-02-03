package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Foodlist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
    }

    public void gotoactivity_addfood(View v)
    {
        Intent ActivityPage = new Intent(this, Addfood.class);
        startActivity(ActivityPage);
    }
}
