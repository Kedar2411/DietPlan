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
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

public class UpdationActivity extends AppCompatActivity {


    EditText weight,height;
    Button ok;
    BackendlessUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updation);

        weight=findViewById(R.id.et_upweight);
        height=findViewById(R.id.et_upheight);
        ok=findViewById(R.id.btn_upok);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String weightfield = weight.getText().toString();
                final String heightfield = height.getText().toString();


                BackendlessUser currentUser = Backendless.UserService.CurrentUser();

               String email= currentUser.getEmail();

               Backendless.UserService.login(email, LoginActivity.pass , new AsyncCallback<BackendlessUser>() {
                   @Override
                   public void handleResponse(BackendlessUser response) {
                       response.setProperty("weight",weightfield);
                       response.setProperty("height",heightfield);
                       Backendless.UserService.update(response, new AsyncCallback<BackendlessUser>() {
                           @Override
                           public void handleResponse(BackendlessUser response) {
                               Toast.makeText(getApplicationContext(),"Profile Successfully Updated..!!!",Toast.LENGTH_SHORT).show();
                           }

                           @Override
                           public void handleFault(BackendlessFault fault) {
                               Toast.makeText(getApplicationContext(),"Failed To Update Profile..!!!",Toast.LENGTH_SHORT).show();

                           }
                       });
                   }

                   @Override
                   public void handleFault(BackendlessFault fault) {
                       Toast.makeText(getApplicationContext(),"Failed To Login..!!!",Toast.LENGTH_SHORT).show();

                   }
               });
            }
        });
    }
}
