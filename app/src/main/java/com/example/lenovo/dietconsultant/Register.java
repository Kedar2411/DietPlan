package com.example.lenovo.dietconsultant;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.StringReader;
import java.security.PrivateKey;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    EditText name,age,weight,height,phone,email,cpassword,cnpassword;
    Button register;
    RadioButton male,female;
    RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         name = (EditText) findViewById(R.id.name);
         age = (EditText) findViewById(R.id.age);
         weight = (EditText) findViewById(R.id.weight);
         height = (EditText) findViewById(R.id.height);
         phone = (EditText) findViewById(R.id.phone);
         email = (EditText) findViewById(R.id.email);
         cpassword = (EditText) findViewById(R.id.cpassword);
         cnpassword = (EditText) findViewById(R.id.cnpassword);
         male=(RadioButton)findViewById(R.id.male);
         female=(RadioButton)findViewById(R.id.female);
         rg=(RadioGroup) findViewById(R.id.radiogroup);
        register=(Button)findViewById(R.id.t_register);

        final String namestr=name.getText().toString();
        final String agestr=age.getText().toString();
        final String weightstr=weight.getText().toString();
        final String heightstr=height.getText().toString();
        final String emailstr=email.getText().toString();
        final String phonestr=phone.getText().toString();
        final String passstr=cpassword.getText().toString();

        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

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
                    Toast.makeText(Register.this, "Password do not match.....!!!", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Register.this, "Registered Successfully.....!!!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplication(), Login.class));
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
