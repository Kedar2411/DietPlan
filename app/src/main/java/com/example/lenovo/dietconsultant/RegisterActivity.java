package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    public static final String APP_ID="C40EF691-47BD-6935-FF77-8ED38FAAA200";
    public static final String SECRET_KEY="E96746B1-1306-74F8-FFE1-DB1339651E00";
    public static final String VERSION="4.4.17";

    EditText name,age,weight,height,phone,email,cpassword,cnpassword;
    Button register;
    RadioButton male,female;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Backendless.setUrl( Defaults.SERVER_URL );
        Backendless.initApp( getApplicationContext(), Defaults.APPLICATION_ID, Defaults.API_KEY );


        //email =(EditText)findViewById(R.id.email);
        //cpassword=(EditText)findViewById(R.id.cpassword);

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String emailField= email.getText().toString();
                String passwordField=cpassword.getText().toString();

                BackendlessUser backendlessUser = new BackendlessUser();
                backendlessUser.setPassword(passwordField);
                backendlessUser.setEmail(emailField);
                backendlessUser.setProperty("name",name);
                backendlessUser.setProperty("age",age);
                backendlessUser.setProperty("weight",weight);
                backendlessUser.setProperty("height",height);
                backendlessUser.setProperty("mobile_number",phone);
                backendlessUser.setProperty("gender",rg);

                Backendless.UserService.register(backendlessUser, new AsyncCallback<BackendlessUser>() {
                    @Override
                    public void handleResponse(BackendlessUser response) {
                        Toast.makeText(getApplicationContext(),"You Registered..",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getApplicationContext(),"Registeration Failed..",Toast.LENGTH_LONG).show();
                    }
                });


                if (!registerName(name.getText().toString()))
                {
                    name.setError("Invalid Name...!");
                    name.requestFocus();
                }   else
                if (!registerAge(age.getText().toString()))
                {
                    age.setError("Invalid Age...!");
                    age.requestFocus();
                }
                else
                if (!registerWeight(weight.getText().toString()))
                {
                    weight.setError("Invalid Weight...!");
                    weight.requestFocus();
                }
                else
                if (!registerHeight(height.getText().toString()))
                {
                    height.setError("Invalid Height...!");
                    height.requestFocus();
                }
                else
                if (!registerMono(phone.getText().toString()))
                {
                    phone.setError("Invalid Mobile Number...!");
                    phone.requestFocus();
                }
                else
                if (!registerEmail(email.getText().toString()))
                {
                    email.setError("Invalid E-Mail..!");
                    email.requestFocus();
                }
                else
                if (!registerCpassword(cpassword.getText().toString()))
                {
                    cpassword.setError("Invalid Password...!");
                    cpassword.requestFocus();
                }
                else
                if (!Confirm(cpassword.getText().toString(),cnpassword.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this, "Password do not match.....!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Registered Successfully.....!!!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplication(), LoginActivity.class));
                }
            }
            private boolean Confirm(String cpassword, String cnpassword)
            {
                if (cpassword.equals(cnpassword))
                {
                    return true;
                }
                else {
                    return false;
                }
            }


            private boolean registerCpassword(String cpassword)
            {
                if (cpassword!=null && cpassword.length()>8)
                {
                    return true;
                }
                else
                {
                    return false;
                }

            }

            private boolean registerEmail(String email)
            {
                String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[_A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern=Pattern.compile(emailPattern);
                Matcher matcher=pattern.matcher(email);

                return matcher.matches();
            }

            private boolean registerMono(String mono)
            {
                if (mono!=null && mono.length()==10)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

            private boolean registerHeight(String height)
            {
                if (height!=null && height.length()>0 && height.length()<=3)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

            private boolean registerWeight(String weight)
            {
                if (weight!=null && weight.length()>0 && weight.length()<=3)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

            private boolean registerAge(String age)
            {
                if (age!=null && age.length()>0 && age.length()<=3)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }

            private boolean registerName(String name)
            {
                if (name!=null && name.length()>0 && name.length()<=15)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });

    }

}
