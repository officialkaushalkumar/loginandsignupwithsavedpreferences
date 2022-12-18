package com.example.sqliteworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView signuptxt;
    EditText loginpageemail,loginpagepassword;
    Button loginbtn;
    DBHelper db;

    SharedPreferences sp ;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();

        sp = getSharedPreferences("spdata",MODE_PRIVATE);
        editor = sp.edit();
        boolean login = sp.getBoolean("ISLOGGEDIN",false);
        if(login==true){
            startActivity(new Intent(MainActivity.this,Dashboard.class));
            finish();
        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validatelogin();
            }
        });
        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,signuppage.class));
                finish();

            }
        });

    }

    private void initializeVariables(){
        signuptxt = findViewById(R.id.loginpagesignuptxt);
        loginpageemail = findViewById(R.id.loginpageemailid);
        loginpagepassword = findViewById(R.id.loginpagepassword);
        loginbtn = findViewById(R.id.loginpageloginbtn);

        db = new DBHelper(this);
    }

    private void validatelogin(){
        String email, password;
        email = loginpageemail.getText().toString();
        password= loginpagepassword.getText().toString();

        if(email.isEmpty()){
            loginpageemail.setError("email cannot be empty");
        }
        else if(password.isEmpty()){
            loginpagepassword.setError("password cannot be empty");
        }
        else{
            boolean result = db.checkemailandpass(email,password);
            if(result==true){
                editor.putString("email",email);
                editor.putString("password",password);
                editor.putBoolean("ISLOGGEDIN",true);
                editor.apply();
                Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Dashboard.class));
                finish();
            }
            else{
                Toast.makeText(this, "login fail", Toast.LENGTH_SHORT).show();
            }
        }
    }
}