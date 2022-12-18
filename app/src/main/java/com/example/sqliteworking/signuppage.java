package com.example.sqliteworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class signuppage extends AppCompatActivity {

    TextView signuplogintxt;
    EditText signupemail,signuppassword,signupconfirmpassword;
    Button signupbtn;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        initializeVariables();

        signuplogintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signuppage.this,MainActivity.class));
                finish();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatesignup();
            }
        });
    }
    private void initializeVariables(){
        signuplogintxt = findViewById(R.id.signuppagelogintxt);
        signupemail = findViewById(R.id.signuppageemailid);
        signuppassword = findViewById(R.id.signuppagepassword);
        signupconfirmpassword = findViewById(R.id.signuppageconfirmpassword);
        signupbtn = findViewById(R.id.signuppagesignupbtn);

        db=new DBHelper(this);
    }

    private void validatesignup(){
        String email,password,confirmpass;
        email=signupemail.getText().toString();
        password=signuppassword.getText().toString();
        confirmpass = signupconfirmpassword.getText().toString();

        if(email.isEmpty()){
            signupemail.setError("email id cannot be empty");
        }
        else if(password.isEmpty()){
            signuppassword.setError("password cannot be null");
        }
        else if(!email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
            signupemail.setError("Invalid email id");
        }
        else if(password.length()<8){
            signuppassword.setError("password less than 8 characters");
        }
        else if(!password.equals(confirmpass)){
            signupconfirmpassword.setError("password does not match");
        }

        else{
            boolean result = db.insertdata(email,password);
            if(result==false){
                Toast.makeText(this, "Resgistration fails", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(signuppage.this,MainActivity.class));
                finish();
            }

        }


    }

}