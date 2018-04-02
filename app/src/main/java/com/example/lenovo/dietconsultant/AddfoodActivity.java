package com.example.lenovo.dietconsultant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class AddfoodActivity extends AppCompatActivity {

    EditText fruit,vegetable,juice,rice,roti,salad;
    Button add;

    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);

        fruit=findViewById(R.id.et_fruit);
        add=findViewById(R.id.btn_add);
        vegetable=findViewById(R.id.et_vegs);
        juice=findViewById(R.id.et_juice);
        rice=findViewById(R.id.et_rice);
        roti=findViewById(R.id.et_roti);
        salad=findViewById(R.id.et_salad);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {


                    String fruitString = fruit.getText().toString();
                    String vegetableString = vegetable.getText().toString();
                    String juiceString = juice.getText().toString();
                    String riceString = rice.getText().toString();
                    String rotiString = roti.getText().toString();
                    String saladString = salad.getText().toString();


                        JSONObject jsonParams = new JSONObject();
                        jsonParams.put("fruits", fruitString);
                        jsonParams.put("vegetable", vegetableString);
                        jsonParams.put("juice", juiceString);
                        jsonParams.put("rice", riceString);
                        jsonParams.put("roti", rotiString);
                        jsonParams.put("salad", saladString);

                    StringEntity entity = new StringEntity(jsonParams.toString());


                        asyncHttpClient.post(getApplicationContext(), Defaults.FOOD_URL, entity, "application/json", new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                Toast.makeText(getApplicationContext(), "ONSUCCESS", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_LONG).show();
                            }
                        });


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
