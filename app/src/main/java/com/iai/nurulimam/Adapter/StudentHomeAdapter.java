package com.iai.nurulimam.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iai.nurulimam.Model.UserResponse;
import com.iai.nurulimam.R;

import java.util.List;

public class StudentHomeAdapter extends RecyclerView.Adapter<StudentHomeAdapter.MyViewHolder> {

    private List<UserResponse> userResponseList;
    private Context context;

    public StudentHomeAdapter() {

    }

    public void setData(List<UserResponse> userResponses) {
        this.userResponseList = userResponses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new StudentHomeAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserResponse userResponse = userResponseList.get(position);
        String name = userResponse.getName();
        String title = userResponse.getTitle();
        String content = userResponse.getContent();

        holder.name.setText(name);
        holder.title.setText(title);
        holder.content.setText(content);
    }

    @Override
    public int getItemCount() {
        return userResponseList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, title, content;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            title = itemView.findViewById(R.id.tvTitle);
            content = itemView.findViewById(R.id.tvContent);
        }
    }
}
