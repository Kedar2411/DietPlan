package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

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
                startActivity(new Intent(getApplication(), UserProfileActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finishAffinity();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
               Toast.makeText(getApplication(),"User Logged Out..!!!",Toast.LENGTH_SHORT).show();
            }
        });

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackendlessUser currentUser = Backendless.UserService.CurrentUser();
                String ageStr=(String)currentUser.getProperty("age");
                String weightStr=(String) currentUser.getProperty("weight");
                String heightStr=(String) currentUser.getProperty("height");


                String result,height,weight,sum,divide,square;
                float m1= (float) 3.2808;

                height = String.valueOf(Float.valueOf(heightStr) / Float.valueOf(m1));
                square=String.valueOf(Float.valueOf(height)*Float.valueOf(height));
                divide=String.valueOf(Float.valueOf(weightStr)/Float.valueOf(square));
                result=divide;

                Toast.makeText(getApplication(),"BMI:-"+result,Toast.LENGTH_LONG).show();
            }

        });
    }
}
