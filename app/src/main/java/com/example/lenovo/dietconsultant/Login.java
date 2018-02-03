package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity
{

    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       final EditText email = (EditText) findViewById(R.id.et_username);
        final EditText password = (EditText) findViewById(R.id.t_password);
        final Button Login = (Button) findViewById(R.id.login);


        //EditText a=(EditText)findViewById(R.id.et_username);
        //String str=email.getText().toString();
        //EditText b=(EditText)findViewById(R.id.t_password);
        //final String pass=password.getText().toString();

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
                        startActivity(new Intent(getApplication(),Admin.class));
                    }
                    else
                    {
                        String str=email.getText().toString();
                        String pass=password.getText().toString();
                        String password1=helper.searchPass(str);

                        if (pass.equals(password1))
                        {
                            Toast.makeText(Login.this, "Logged In", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplication(),Profile.class));
                        }
                        else
                        {
                            Toast.makeText(Login.this, "email and password do not match", Toast.LENGTH_LONG).show();
                        }

                    }
            }


            /*private int login(String name, String password) {
                if (name.isEmpty() || password.isEmpty()) {
                    return 0;
                } else if (name.equals("admin@gmail.com") && password.equals("admin123456")) {
                    return 1;
                }
                return 0;
            }*/
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

    public void gotoactivity_register(View v) {
        Intent ActivityPage = new Intent(this, Register.class);
        startActivity(ActivityPage);

    }
}
