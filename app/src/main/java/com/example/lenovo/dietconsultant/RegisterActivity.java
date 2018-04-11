package com.example.lenovo.dietconsultant;

import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.UserService;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.property.UserProperty;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty
    EditText  phone;

    @NotEmpty
    EditText height;

    @NotEmpty
    EditText weight;

    @NotEmpty
    EditText age;

    @NotEmpty
    EditText name;

    @NotEmpty
    @Email
    EditText email;

    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    EditText cpassword;

    @ConfirmPassword
    EditText cnpassword;

    Button register;


    Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);





        register = findViewById(R.id.t_register);
        email = findViewById(R.id.email);
        cpassword = findViewById(R.id.cpassword);
        cnpassword = findViewById(R.id.cnpassword);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        weight=findViewById(R.id.weight);
       height=findViewById(R.id.et_height);
        phone=findViewById(R.id.phone);

        validator = new Validator(this);
        validator.setValidationListener(this);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validator.validate();
            }
        });

    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "User Validated!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplication(), LoginActivity.class));




        String emailField = email.getText().toString();
        String passwordField = cpassword.getText().toString();
        String nameField=name.getText().toString();
        String ageField=age.getText().toString();
        String weightField=weight.getText().toString();
        String heightField=height.getText().toString();
        String phoneField=phone.getText().toString();



        BackendlessUser user = new BackendlessUser();
        user.setProperty("email", emailField);
        user.setPassword(passwordField);
        user.setProperty("name",nameField);
        user.setProperty("age",ageField);
        user.setProperty("weight",weightField);
        user.setProperty("height",heightField);
        user.setProperty("phone",phoneField);

        


        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
            public void handleResponse(BackendlessUser registeredUser) {
                Toast.makeText(RegisterActivity.this, "Registered Successfully.....!!!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplication(), LoginActivity.class));

            }

            public void handleFault(BackendlessFault fault) {
                Toast.makeText(RegisterActivity.this, "Registeration Failed.....!!!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        Toast.makeText(this, "Failed to Validate!", Toast.LENGTH_SHORT).show();

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }

        }

    }
}
