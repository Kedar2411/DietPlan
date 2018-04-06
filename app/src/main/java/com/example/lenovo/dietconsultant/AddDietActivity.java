package com.example.lenovo.dietconsultant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    Spinner users_spninner, breakfast, lunch, snacks, dinner;
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

        asyncHttpClient.get(Defaults.FOOD_URL, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                breakfast_str = new ArrayList<String>();
                lunch_str = new ArrayList<String>();
                snacks_str = new ArrayList<String>();
                dinner_str = new ArrayList<String>();

                List<FoodInfo> foodInfos = new ArrayList<>();
                Gson gson = new Gson();
                FoodInfo[] foodInfos1 = gson.fromJson(new String(responseBody, StandardCharsets.UTF_8), FoodInfo[].class);

                for (FoodInfo foodInfo : foodInfos1) {
                    breakfast_str.add(foodInfo.getFruits());
                    breakfast_str.add(foodInfo.getJuice());
                    lunch_str.add(foodInfo.getFruits());
                    lunch_str.add(foodInfo.getVegetable());
                    lunch_str.add(foodInfo.getJuice());
                    lunch_str.add(foodInfo.getRice());
                    lunch_str.add(foodInfo.getRoti());
                    lunch_str.add(foodInfo.getSalad());
                    snacks_str.add(foodInfo.getFruits());
                    snacks_str.add(foodInfo.getJuice());
                    dinner_str.add(foodInfo.getFruits());
                    dinner_str.add(foodInfo.getVegetable());
                    dinner_str.add(foodInfo.getJuice());
                    dinner_str.add(foodInfo.getRice());
                    dinner_str.add(foodInfo.getRoti());
                    dinner_str.add(foodInfo.getSalad());


                }



                ArrayAdapter<String> dataAdapter_breakfast = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, breakfast_str);
                ArrayAdapter<String> dataAdapter_lunch = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, lunch_str);
                ArrayAdapter<String> dataAdapter_snacks = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, snacks_str);
                ArrayAdapter<String> dataAdapter_dinner = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, dinner_str);

                dataAdapter_breakfast.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                breakfast.setAdapter(dataAdapter_breakfast);

                dataAdapter_lunch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                lunch.setAdapter(dataAdapter_lunch);

                dataAdapter_snacks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                snacks.setAdapter(dataAdapter_snacks);

                dataAdapter_dinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dinner.setAdapter(dataAdapter_dinner);

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), "Could'nt load data, Please try again later !!", Toast.LENGTH_SHORT).show();

            }
        });

        add = findViewById(R.id.btn_adddiet);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String breakfast_string = breakfast.getSelectedItem().toString();
                String lunch_string = lunch.getSelectedItem().toString();
                String snacks_string = snacks.getSelectedItem().toString();
                String dinner_string = dinner.getSelectedItem().toString();
                try {
                    Gson gson = new Gson();
                    DietInfo dietInfo = new DietInfo();
                    dietInfo.setBreakfast(breakfast_string);
                    dietInfo.setLunch(lunch_string);
                    dietInfo.setDinner(dinner_string);
                    dietInfo.setSnacks(snacks_string);


                    StringEntity entity = new StringEntity(gson.toJson(dietInfo), "utf-8");

                    asyncHttpClient.post(getApplicationContext(), Defaults.DIET_URL, entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(getApplicationContext(), "failed to load", Toast.LENGTH_SHORT).show();

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
        breakfast = findViewById(R.id.spn_breakfast);
        lunch = findViewById(R.id.spn_lunch);
        snacks = findViewById(R.id.spn_snacks);
        dinner = findViewById(R.id.spn_dinner);

    }
}
