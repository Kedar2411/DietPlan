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

public class RequestActivity extends AppCompatActivity {

    EditText request,name,email;
    Button send;
    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        request=findViewById(R.id.change_request);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        send=findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String timeString=request.getText().toString();
                String nameString=name.getText().toString();
                String emailString=email.getText().toString();

                try {
                    JSONObject jsonParams = new JSONObject();
                    jsonParams.put("request",timeString);
                    jsonParams.put("name",nameString);
                    jsonParams.put("email",emailString);

                    StringEntity entity = new StringEntity(jsonParams.toString());

                    asyncHttpClient.post(getApplicationContext(), Defaults.REQUEST_URL, entity, "application/json", new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            Toast.makeText(getApplicationContext(), "Request Sent Successfully..!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(getApplicationContext(), "Failed To Send Request", Toast.LENGTH_SHORT).show();
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
