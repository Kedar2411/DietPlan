package com.example.lenovo.dietconsultant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class UpdationActivity extends AppCompatActivity {


    EditText age;
    Button ok;
    BackendlessUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updation);

        age=findViewById(R.id.et_upage);
        ok=findViewById(R.id.btn_upok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String agefield=age.getText().toString();
                String email=user.getEmail();
                String password=user.getPassword();

                Backendless.UserService.login(email, password, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        user.setProperty("age",agefield);

                        Backendless.UserService.update(user, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                Toast.makeText(UpdationActivity.this,"Updated",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(UpdationActivity.this,"Failed",Toast.LENGTH_LONG).show();
                                fault.getCode();

                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        fault.getCode();
                    }
                });
            }
        });
    }
}
