package com.example.lenovo.dietconsultant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cz.msebera.android.httpclient.Header;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.StringEntity;

import static com.loopj.android.http.AsyncHttpClient.LOG_TAG;

public class FeedBackActivity extends AppCompatActivity {


    EditText feedback,name,email;
    Button submit;

    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        feedback = findViewById(R.id.et_feedback);
        submit = findViewById(R.id.btn_submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                    String feedbackString = feedback.getText().toString();
                    String nameString=name.getText().toString();
                    String mailString=email.getText().toString();
                    if (feedbackString != null) {

                        JSONObject jsonParams = new JSONObject();
                        jsonParams.put("feedback", feedbackString);
                        jsonParams.put("name",nameString);
                        jsonParams.put("email",mailString);


                        StringEntity entity = new StringEntity(jsonParams.toString());


                        asyncHttpClient.post(getApplicationContext(), Defaults.FEEDBACK_URL, entity, "application/json", new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                Toast.makeText(getApplicationContext(), "ONSUCCESS", Toast.LENGTH_LONG).show();

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                Toast.makeText(getApplicationContext(), "onFailure", Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
