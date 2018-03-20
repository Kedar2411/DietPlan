package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    Button logout,dietplan,feedback,bmi,myprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        logout=findViewById(R.id.btn_logout);
        dietplan=findViewById(R.id.btn_deitpln);
        feedback=findViewById(R.id.btn_fb);
        bmi=findViewById(R.id.btn_bmi);
        myprofile=findViewById(R.id.btn_mprofile);


        dietplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), DietPlanActivity.class));
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), FeedBackActivity.class));
            }
        });

        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), MyProfileActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishAffinity();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                Toast.makeText(getApplication(),"Logged Out",Toast.LENGTH_LONG).show();
            }
        });
    }
}
