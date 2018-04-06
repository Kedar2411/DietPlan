package com.example.lenovo.dietconsultant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.lenovo.dietconsultant.model.FeedbackAdapter;
import com.example.lenovo.dietconsultant.model.FeedbackInfo;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;
import java.util.List;
import com.google.gson.Gson;

public class ViewFeedbackActivity extends AppCompatActivity {

    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);

        rv  = (RecyclerView) findViewById(R.id.recycler_view);


        AsyncHttpClient client = new AsyncHttpClient();
        client.get(Defaults.FEEDBACK_INFO_URL, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
                List<FeedbackInfo> feedbackInfos = new ArrayList<>();
                Gson gson = new Gson();
                FeedbackInfo[] feedbackInfo = gson.fromJson(new String(response, StandardCharsets.UTF_8), FeedbackInfo[].class);
                for (FeedbackInfo feedbackInfo1 :  feedbackInfo){
                    feedbackInfos.add(feedbackInfo1);
                }

                LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
                llm.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(llm);

                FeedbackAdapter ca = new FeedbackAdapter(feedbackInfos);
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