package com.example.lenovo.dietconsultant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lenovo.dietconsultant.model.FeedbackAdapter;
import com.example.lenovo.dietconsultant.model.FeedbackInfo;
import com.example.lenovo.dietconsultant.model.RequestAdapter;
import com.example.lenovo.dietconsultant.model.RequestInfo;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ViewRequestActivity extends AppCompatActivity {


    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);

        rv  = (RecyclerView) findViewById(R.id.recycler_view);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Defaults.REQUEST_URL, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                List<RequestInfo> requestInfos = new ArrayList<>();
                Gson gson = new Gson();
                RequestInfo[] requestInfo = gson.fromJson(new String(response, StandardCharsets.UTF_8), RequestInfo[].class);
                for (RequestInfo requestInfo1 :  requestInfo){
                    requestInfos.add(requestInfo1);
                }

                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(llm);

                RequestAdapter ca = new RequestAdapter(requestInfos);
                rv.setAdapter(ca);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Toast.makeText(getApplicationContext() , errorResponse.toString(),Toast.LENGTH_LONG );
            }
            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });

    }
}
