package com.example.lenovo.dietconsultant.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.dietconsultant.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder> {

    private List<FeedbackInfo> feedbackInfoList;

    public FeedbackAdapter(List<FeedbackInfo> feedbackInfoList) {
        this.feedbackInfoList = feedbackInfoList;
    }

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {
        public TextView feedback,name,email;

        public FeedbackViewHolder(View view) {
            super(view);
            feedback = (TextView) view.findViewById(R.id.feedback);
            name=(TextView) view.findViewById(R.id.name);
            email=(TextView) view.findViewById(R.id.email);
        }
    }


    @Override
    public void onBindViewHolder(FeedbackViewHolder holder, int position) {
        FeedbackInfo f = feedbackInfoList.get(position);

        holder.feedback.setText(f.getFeedback());
        holder.name.setText(f.getName());
        holder.email.setText(f.getEmail());


    }

    @Override
    public int getItemCount() {
        return feedbackInfoList.size();
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout, parent, false);
        return new FeedbackViewHolder(v);
    }
}