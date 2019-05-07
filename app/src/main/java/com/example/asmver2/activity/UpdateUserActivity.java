package com.example.asmver2.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asmver2.R;
import com.example.asmver2.database.DbHelper;
import com.example.asmver2.model.User;

public class UpdateUserActivity extends AppCompatActivity {

    EditText edUsernameUpdate;
    EditText edDescriptionUpdate;
    Spinner spGenderUpdate;
    Activity context;
    Button btUpdate;
    Button btDelete;
    public UpdateUserActivity() {
        context = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        final DbHelper dbHelper =  new DbHelper(context);

        Intent intent = this.getIntent();

        Bundle bundle = intent.getExtras();

        assert bundle != null;
        final User user = (User) bundle.getSerializable("User");

        spGenderUpdate = findViewById(R.id.spGenderUpdate);
        edDescriptionUpdate = findViewById(R.id.edDescriptionUpdate);
        edUsernameUpdate = findViewById(R.id.edUserUpdate);
        btDelete = findViewById(R.id.btDelete);
        btUpdate = findViewById(R.id.btUpdate);

        String[] genders = {"Male", "Female", "Other"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, genders);

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spGenderUpdate.setAdapter(adapter);

        assert user != null;
        spGenderUpdate.setSelection(adapter.getPosition(user.getGender()));
        edUsernameUpdate.setText(user.getUsername());
        edDescriptionUpdate.setText(user.getDescription());

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User userUpdate = new User(edUsernameUpdate.getText().toString(), spGenderUpdate.getSelectedItem().toString(), edDescriptionUpdate.getText().toString());
                long result = dbHelper.updateUser(userUpdate, user.getId());
                if (result > 0){
                    Toast.makeText(context, "Update Success!", Toast.LENGTH_SHORT).show();
                    SystemClock.sleep(500);
                    finish();
                    return;
                }
                Toast.makeText(context, "Update Fail!", Toast.LENGTH_LONG).show();
            }
        });


        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = dbHelper.deleteUser(user.getId());
                if (result > 0){
                    Toast.makeText(context, "Delete Success!", Toast.LENGTH_SHORT).show();
                    SystemClock.sleep(500);
                    finish();
                    return;
                }
                Toast.makeText(context, "Delete Fail!", Toast.LENGTH_LONG).show();
            }
        });

    }
}
