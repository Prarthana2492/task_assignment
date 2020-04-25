package com.example.task_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Login_Activity extends AppCompatActivity {

    Button login_btn,forgt_btn;
    TextView email,password,hello_txt;
    UserDB userDB;
    String user_Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        login_btn = findViewById(R.id.login_btn);
        forgt_btn = findViewById(R.id.frgt_btn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        hello_txt = findViewById(R.id.hello_txt);

        user_Name = getIntent().getStringExtra("NAME");

        hello_txt.setText("Hi " + user_Name + " Welcome!");

       userDB = new UserDB(this);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (email.getText().toString().equals("")) {

                    Toast.makeText(Login_Activity.this, "Enter Your Email", Toast.LENGTH_SHORT).show();


                } else if (password.getText().toString().equals("")) {


                    Toast.makeText(Login_Activity.this, "Enter Password", Toast.LENGTH_SHORT).show();


                } else {

                    boolean response = userDB.checkUser(email.getText().toString(), password.getText().toString());


                    if(response){

                        Toast.makeText(Login_Activity.this, "User Successfully Login", Toast.LENGTH_SHORT).show();

                    }



            }
        }
        });


        forgt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login_Activity.this,Forget_Password_Activity.class);
                startActivity(intent);

            }
        });





    }
}