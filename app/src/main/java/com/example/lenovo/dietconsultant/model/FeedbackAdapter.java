package com.example.lenovo.dietconsultant.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lenovo.dietconsultant.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>  {

    private List<FeedbackInfo> feedbackInfoList;

    public FeedbackAdapter(List<FeedbackInfo> feedbackInfoList) {
        this.feedbackInfoList = feedbackInfoList;
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {
        public TextView UserName;
        public TextView UserEmail;
        public TextView FeedbackBody;

        public FeedbackViewHolder(View view) {
            super(view);
            UserName = (TextView) view.findViewById(R.id.UserName);
            UserEmail = (TextView) view.findViewById(R.id.UserEmail);
            FeedbackBody=(TextView) view.findViewById(R.id.FeedbackBody);
        }
    }


    @Override
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        final FeedbackInfo  e= feedbackInfoList.get(position);
        holder.UserName.setText(e.getName());
        holder.UserEmail.setText(e.getEmail());
        holder.FeedbackBody.setText(e.getFeedback());
    }

    @Override
    public int getItemCount() {
        return feedbackInfoList.size();
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item3, parent, false);
        return new FeedbackViewHolder(v);
    }

}