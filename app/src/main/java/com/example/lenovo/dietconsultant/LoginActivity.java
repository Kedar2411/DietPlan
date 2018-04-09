package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    TextView register,recoverpass;
    Button  Login;
    EditText email, password;
    public static String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );


        email = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.t_password);
        Login = (Button) findViewById(R.id.login);



        register = (TextView) findViewById(R.id.t_register1);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(),RegisterActivity.class));

            }
        });


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String emailField=email.getText().toString();
                final String passwordField=password.getText().toString();


                Backendless.UserService.login(emailField, passwordField, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        pass = passwordField;
                        Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplication(), ProfileActivity.class));
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(LoginActivity.this, "Log In Failed", Toast.LENGTH_SHORT).show();
                    }
                });

                if (!validateEmail(email.getText().toString())) {
                    email.setError("Invalid E-Mail...!");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password....!");
                    password.requestFocus();
                } else if (validateAdmin(email.getText().toString(), password.getText().toString()) == 1) {
                    Toast.makeText(getApplication(), "WELCOME ADMIN", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplication(), AdminActivity.class));
                }
            }
        });

        recoverpass=findViewById(R.id.t_recoverpass);
        recoverpass.setOnClickListener(new View.OnClickListener() {


            final String emailField=email.getText().toString();


            @Override
            public void onClick(View view) {
                final String emailField=email.getText().toString();

                //Toast.makeText(getApplication(), "email"+emailField, Toast.LENGTH_LONG).show();

                Backendless.UserService.restorePassword(emailField, new AsyncCallback <Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Toast.makeText(getApplicationContext(),"temporary password has been sent to your registered email..!!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getApplicationContext(),"failed to restore password",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private int validateAdmin(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return 0;
        } else if (email.equals("admin@gmail.com") && password.equals("Admin123#")) {
            return 1;
        }
        return 0;
    }

    private boolean validatePassword(String password) {
        if (password != null && password.length() > 8) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateEmail(String email) {
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[_A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }



}
