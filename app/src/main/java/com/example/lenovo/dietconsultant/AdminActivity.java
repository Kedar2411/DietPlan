package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {
    Button Food,Diet,feedback,c_req,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Diet=(Button)findViewById(R.id.add_diet);
        Food=(Button)findViewById(R.id.food);
        feedback =(Button)findViewById(R.id.btn_fb);
        c_req=findViewById(R.id.c_req);
        logout=findViewById(R.id.btn_logout);

        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),FoodlistActivity.class));
            }
        });


        Diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(),AddDietActivity.class));
            }
        });


        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewFeedbackActivity.class));
            }
        });


        c_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewRequestActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                Toast.makeText(getApplication(),"Admin Logged Out..!!!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
