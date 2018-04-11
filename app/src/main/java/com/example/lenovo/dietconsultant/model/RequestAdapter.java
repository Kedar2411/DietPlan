package com.example.lenovo.dietconsultant.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.example.lenovo.dietconsultant.R;

import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<RequestInfo> requestInfoList;

    public RequestAdapter(List<RequestInfo> requestInfoList) {
        this.requestInfoList = requestInfoList;
    }


    public class RequestViewHolder extends RecyclerView.ViewHolder {
        public TextView UserName;
        public TextView UserEmail;
        public TextView FeedbackBody;

        public RequestViewHolder(View view) {
            super(view);
            UserName = (TextView) view.findViewById(R.id.UserName);
            UserEmail = (TextView) view.findViewById(R.id.UserEmail);
            FeedbackBody=(TextView) view.findViewById(R.id.FeedbackBody);
        }
    }


    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        final RequestInfo e= requestInfoList.get(position);
        holder.UserName.setText(e.getRequest());
        holder.UserEmail.setText(e.getName());
        holder.FeedbackBody.setText(e.getEmail());
    }

    @Override
    public int getItemCount() {
        return requestInfoList.size();
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.request, parent, false);
        return new RequestViewHolder(v);
    }

}