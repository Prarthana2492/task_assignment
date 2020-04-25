package com.example.task_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Prarthana on 25,April,2020
 */
public class MainActivity extends AppCompatActivity {
    Button register_btn;
    EditText name,email,password;
    UserDB userDB;
    public static String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register_btn = findViewById(R.id.register_btn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        userDB = UserDB.getUserDB(this);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (email.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (!(userDB.isEmailExist(email.getText().toString()))) {
                        boolean response = userDB.insertdata(name.getText().toString(), email.getText().toString(), password.getText().toString());
                        if (response) {
                            Toast.makeText(MainActivity.this, "Registered Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                            intent.putExtra("NAME", user_name = name.getText().toString());
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User Already Registered", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    }