package com.example.task_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Prarthana on 25,April,2020
 */
public class Forget_Password_Activity extends AppCompatActivity {
    EditText email;
    Button submit;
    UserDB userDB;
    TextView text_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_layout);
        email = findViewById(R.id.email);
        submit = findViewById(R.id.submit_btn);
        text_password = findViewById(R.id.your_passwrd);
        userDB=UserDB.getUserDB(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userDB.isEmailExist(email.getText().toString().trim())) {
                    Cursor res = userDB.getPassword(email.getText().toString().trim());
                    if (res.getCount() == 0) {
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        buffer.append(res.getString(0) + "\n");
                    }
                    text_password.setText(buffer.toString().trim());
                } else {
                    Toast.makeText(Forget_Password_Activity.this, "User Not Registered", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}