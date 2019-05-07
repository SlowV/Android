package com.example.asmver2.activity;

import android.app.Activity;
//import android.app.ListActivity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmver2.R;
import com.example.asmver2.database.DbHelper;
import com.example.asmver2.model.User;
import com.example.asmver2.model.UserError;

public class MainActivity extends AppCompatActivity {

    TextView tvRegister;
    EditText edUser;
    EditText edDescription;
    CheckBox checkbox;
    Spinner spGender;
    Button btRegister;
    Activity context;
    public DbHelper dbHelper;

    public MainActivity() {
        context = MainActivity.this;
        dbHelper = new DbHelper(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spGender = findViewById(R.id.spGender);
        edUser = findViewById(R.id.edUser);
        edDescription = findViewById(R.id.edDescription);
        spGender = findViewById(R.id.spGender);
        checkbox = findViewById(R.id.checkbox);
        dbHelper.getWritableDatabase();

        String[] genders = {"Male", "Female", "Other"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, genders);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spGender.setAdapter(adapter);

        btRegister = findViewById(R.id.btRegister);
        checkbox = findViewById(R.id.checkbox);
        btRegister.setEnabled(false);

        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    btRegister.setEnabled(true);
                } else {
                    btRegister.setEnabled(false);
                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserError error = validFormUser(edUser.getText().toString(), spGender.getSelectedItem().toString(), checkbox.isChecked());
                if (error.isError()) {
                    Toast.makeText(context, error.getMsg(), Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(edUser.getText().toString(), spGender.getSelectedItem().toString() , edDescription.getText().toString());

                boolean checkRegister = dbHelper.insertUser(user);

                if (!checkRegister){
                    Toast.makeText(context, "Register fail!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(context, "Register Success!", Toast.LENGTH_SHORT).show();
                SystemClock.sleep(1 * 1000);
                goToListUserActivity();
            }
        });

    }

    public void goToListUserActivity(){
        Intent intent = new Intent(this, ListUserActivity.class);
        startActivity(intent);
    }

    public UserError validFormUser(String username, String description, boolean check) {
        if (username.isEmpty()) {
            return new UserError(true, "Username can not empty or length bigger 0!");
        } else if (description.isEmpty()) {
            return new UserError(true, "Description can not empty!");
        } else if (!check) {
            return new UserError(true, "Please check \"I have read and agree\"!");
        }
        return new UserError(false, "Valid");
    }

}
