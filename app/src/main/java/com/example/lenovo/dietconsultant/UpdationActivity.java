package com.example.lenovo.dietconsultant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class UpdationActivity extends AppCompatActivity {


    EditText age,weight,height;
    Button ok;
    BackendlessUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updation);

        age=findViewById(R.id.et_upage);
        weight=findViewById(R.id.et_upweight);
        height=findViewById(R.id.et_upheight);
        ok=findViewById(R.id.btn_upok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 final String agefield = age.getText().toString();
                final String weightfield = weight.getText().toString();
                final String heightfield = height.getText().toString();


                BackendlessUser currentUser = Backendless.UserService.CurrentUser();

               String email= currentUser.getEmail();
               //String password= (String) currentUser.getProperty( "password" );

               Backendless.UserService.login(email, LoginActivity.pass , new AsyncCallback<BackendlessUser>() {
                   @Override
                   public void handleResponse(BackendlessUser response) {
                       response.setProperty("age",agefield);
                       response.setProperty("weight",weightfield);
                       response.setProperty("height",heightfield);
                       Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                           @Override
                           public void handleResponse(BackendlessUser response) {
                               Toast.makeText(getApplicationContext(),"updated",Toast.LENGTH_LONG).show();
                           }

                           @Override
                           public void handleFault(BackendlessFault fault) {
                               Toast.makeText(getApplicationContext(),"failed to update",Toast.LENGTH_LONG).show();

                           }
                       });
                   }

                   @Override
                   public void handleFault(BackendlessFault fault) {

                   }
               });
            }
        });
    }
}
