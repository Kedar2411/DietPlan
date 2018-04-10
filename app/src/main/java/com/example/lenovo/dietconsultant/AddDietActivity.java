package com.example.lenovo.dietconsultant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.lenovo.dietconsultant.model.DietInfo;
import com.example.lenovo.dietconsultant.model.FoodInfo;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AddDietActivity extends AppCompatActivity {

    Spinner users_spninner;
    EditText breakfast,lunch,snacks,dinner;
    Button add;

    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    ArrayList<String> breakfast_str, lunch_str, snacks_str, dinner_str, user_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diet);

        InitFood();

        Backendless.Data.of(BackendlessUser.class).find(new AsyncCallback<List<BackendlessUser>>() {
            @Override
            public void handleResponse(List<BackendlessUser> users) {
                Iterator<BackendlessUser> userIterator = users.iterator();
                user_list = new ArrayList<>();
                while (userIterator.hasNext()) {
                    BackendlessUser user = userIterator.next();
                    user_list.add(user.getEmail());

                }

                ArrayAdapter<String> dataAdapter_users = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, user_list);
                dataAdapter_users.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                users_spninner.setAdapter(dataAdapter_users);

            }

            @Override
            public void handleFault(BackendlessFault backendlessFault) {
                System.out.println("Server reported an error - " + backendlessFault.getMessage());
            }
        });


        add = findViewById(R.id.btn_adddiet);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    String breakfastString = breakfast.getText().toString();
                    String lunchString=lunch.getText().toString();
                    String snacksString=snacks.getText().toString();
                    String dinnerString=dinner.getText().toString();


                    JSONObject jsonParams = new JSONObject();
                    jsonParams.put("breakfast", breakfastString);
                    jsonParams.put("lunch",lunchString);
                    jsonParams.put("snacks",snacksString);
                    jsonParams.put("dinner",dinnerString);


                    StringEntity entity = new StringEntity(jsonParams.toString());

                    asyncHttpClient.post(getApplicationContext(), Defaults.DIET_URL, entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    private void InitFood() {
        users_spninner =findViewById(R.id.spn_users);
        breakfast=findViewById(R.id.et_breakfast);
        lunch=findViewById(R.id.et_lunch);
        snacks=findViewById(R.id.et_snacks);
        dinner=findViewById(R.id.et_dinner);


    }
}
