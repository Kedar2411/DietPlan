package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


       /* Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );

        HashMap testObject = new HashMap<>();
        testObject.put( "foo", "bar" );
        Backendless.Data.of( "TestTable" ).save(testObject, new AsyncCallback<Map>() {
            @Override
            public void handleResponse(Map response) {
                Log.d("ug", "handleResponse: ");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.e( "MYAPP", "Server reported an error " + fault.getMessage() );
            }
        });*/


        final EditText email = (EditText) findViewById(R.id.et_username);
        final EditText password = (EditText) findViewById(R.id.t_password);
        final Button Login = (Button) findViewById(R.id.login);



        EditText a=(EditText)findViewById(R.id.et_username);
        String str=email.getText().toString();
        EditText b=(EditText)findViewById(R.id.t_password);
        final String pass=password.getText().toString();

        //final String password1=helper.searchPass(str);


        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (!validateEmail(email.getText().toString()))
                {
                    email.setError("Invalid E-Mail...!");
                    email.requestFocus();
                }
                else if (!validatePassword(password.getText().toString()))
                {
                    password.setError("Invalid Password....!");
                    password.requestFocus();
                }
                else
                    if (validateAdmin (email.getText().toString(),password.getText().toString())==1)
                    {
                        Toast.makeText(getApplication(),"WELCOME ADMIN",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplication(),AdminActivity.class));
                    }
                    else
                    {

                            Toast.makeText(LoginActivity.this, "Logged In", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplication(),ProfileActivity.class));

                    }
            }
        });
    }

            private int validateAdmin(String email, String password)
         {
              if (email.isEmpty() || password.isEmpty())
              {
                 return 0;
              }
             else if (email.equals("admin@gmail.com") && password.equals("admin1234"))
             {
                  return 1;
             }
                 return 0;
         }

            private boolean validatePassword(String password)
            {
                if (password!=null && password.length()>8)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

            private boolean validateEmail(String email)
            {
                String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[_A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern=Pattern.compile(emailPattern);
                Matcher matcher=pattern.matcher(email);

                return matcher.matches();
            }

    public void gotoactivity_register(View v)
    {
        Intent ActivityPage = new Intent(this, RegisterActivity.class);
        startActivity(ActivityPage);
       // Toast.makeText(getApplicationContext(),"djavj",Toast.LENGTH_LONG).show();
    }
}
