package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;

public class UserProfileActivity extends AppCompatActivity {
    Button go;
    TextView name,age,weight,height,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        go=findViewById(R.id.btn_go);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),UpdationActivity.class));
            }
        });


        name=findViewById(R.id.tv_name);
        age=findViewById(R.id.tv_age);
        weight=findViewById(R.id.tv_weight);
        height=findViewById(R.id.tv_height);
        phone=findViewById(R.id.tv_phone);


        BackendlessUser currentUser = Backendless.UserService.CurrentUser();

        String nameStr= (String) currentUser.getProperty("name");
        String ageStr=(String)currentUser.getProperty("age");
        String weightStr=(String) currentUser.getProperty("weight");
        String heightStr=(String) currentUser.getProperty("height");
        String phoneStr=(String) currentUser.getProperty("phone");



        name.setText(nameStr);
        age.setText(ageStr);
        weight.setText(weightStr);
        height.setText(heightStr);
        phone.setText(phoneStr);

    }
}
