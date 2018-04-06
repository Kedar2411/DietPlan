package com.example.lenovo.dietconsultant.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.dietconsultant.R;

import java.util.List;

/**
 * Created by lenovo on 05-04-2018.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.RequestViewHolder> {

    private List<RequestInfo> requestInfoList;

    public RequestAdapter(List<RequestInfo> requestInfoList) {
        this.requestInfoList = requestInfoList;
    }

    public class RequestViewHolder extends RecyclerView.ViewHolder {
        public TextView time,type,sub;

        public RequestViewHolder(View view) {
            super(view);
            time = (TextView) view.findViewById(R.id.time);
            type=(TextView) view.findViewById(R.id.type);
            sub=(TextView) view.findViewById(R.id.sub);
        }
    }


    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        RequestInfo f = requestInfoList.get(position);

        holder.time.setText(f.getFood_time());
        holder.type.setText(f.getFood_type());
        holder.sub.setText(f.getSub_type());


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
