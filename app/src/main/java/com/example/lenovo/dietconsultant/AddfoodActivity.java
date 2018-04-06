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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                String fruitString = fruit.getText().toString();
                String vegetableString = vegetable.getText().toString();
                String juiceString = juice.getText().toString();
                String riceString = rice.getText().toString();
                String rotiString = roti.getText().toString();
                String saladString = salad.getText().toString();
                try {




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

               /* Backendless.Persistence.of("Food").find(new AsyncCallback<List<Map>>() {
                    @Override
                    public void handleResponse(List<Map> response) {
                        Toast.makeText(AddfoodActivity.this,"Done",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(AddfoodActivity.this,"wrong",Toast.LENGTH_LONG).show();

                    }
                });

                Backendless.Persistence.of("Food").findFirst(new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        Toast.makeText(AddfoodActivity.this,"Done1",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(AddfoodActivity.this,"wrong1",Toast.LENGTH_LONG).show();

                    }
                });

                Backendless.Persistence.of("Food").findLast(new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        Toast.makeText(AddfoodActivity.this,"Done2",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(AddfoodActivity.this,"wrong2",Toast.LENGTH_LONG).show();

                    }
                });

                HashMap food=new HashMap();
                food.put("fruits",fruitString);


                Backendless.Persistence.of("Food").save(food, new AsyncCallback<Map>() {
                    @Override
                    public void handleResponse(Map response) {
                        Backendless.Persistence.of(AddfoodActivity.class).findById((AddfoodActivity) response.get("objectId"), new AsyncCallback<AddfoodActivity>() {
                            @Override
                            public void handleResponse(AddfoodActivity response) {
                                Toast.makeText(AddfoodActivity.this,"Done3",Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(AddfoodActivity.this,"wrong3",Toast.LENGTH_LONG).show();

                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(AddfoodActivity.this,"Done4",Toast.LENGTH_LONG).show();

                    }
                });
*/
            }
        });



    }
}
