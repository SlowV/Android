package com.example.asmver2.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asmver2.R;
import com.example.asmver2.adapter.UserAdapter;
import com.example.asmver2.database.DbHelper;
import com.example.asmver2.model.User;

import java.io.Serializable;
import java.util.List;

public class ListUserActivity extends AppCompatActivity implements UserAdapter.IOnClickItem {

    TextView tvId;
    TextView tvUsername;
    TextView tvGender;
    List<User> userList;
    Activity context;
    DbHelper dbHelper;
    RecyclerView rvListUser;

    public ListUserActivity() {
        this.context = ListUserActivity.this;
        dbHelper = new DbHelper(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        userList = dbHelper.getListUser();
        rvListUser = findViewById(R.id.rvListUser);
        bindContent(context, userList, this, rvListUser);
    }

    @Override
    public void onClickItem(int index) {
        User user = userList.get(index);
        goToActivityUpdateUser(user);
    }

    public void goToActivityUpdateUser(User user){
        Intent intent = new Intent(context, UpdateUserActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("User", (Serializable) user);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void bindContent(Activity context, List<User> userList, UserAdapter.IOnClickItem iOnClickItem, RecyclerView rvListUser){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);

        UserAdapter adapter = new UserAdapter(context, userList, iOnClickItem);

        rvListUser.setLayoutManager(layoutManager);
        rvListUser.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userList = dbHelper.getListUser();
        bindContent(context, userList, this, rvListUser);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
