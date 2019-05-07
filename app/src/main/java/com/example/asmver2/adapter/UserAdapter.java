package com.example.asmver2.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asmver2.R;
import com.example.asmver2.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<User> userList;
    private IOnClickItem iOnClickItemListener;

    public UserAdapter(Activity activity, List<User> userList,IOnClickItem iOnClickItemListener) {
        this.activity = activity;
        this.userList = userList;
        this.iOnClickItemListener = iOnClickItemListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.user_item_recyclerview, viewGroup, false);
        UserHolder holder = new UserHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        UserHolder holder = (UserHolder) viewHolder;
        User user = userList.get(i);
        holder.tvId.setText( Integer.toString(user.getId()));
        holder.tvGender.setText(user.getGender());
        holder.tvUsername.setText(user.getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickItemListener.onClickItem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserHolder extends RecyclerView.ViewHolder {
        TextView tvId;
        TextView tvUsername;
        TextView tvGender;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvGender = itemView.findViewById(R.id.tvGender);
        }
    }

    public interface IOnClickItem{
        void onClickItem(int index);
    }
}
