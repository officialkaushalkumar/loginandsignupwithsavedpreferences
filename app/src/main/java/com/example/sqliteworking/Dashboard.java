package com.example.sqliteworking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends AppCompatActivity {

    Button logout;
    SharedPreferences sp ;
    SharedPreferences.Editor editor;
    TextView email,pass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sp = getSharedPreferences("spdata",MODE_PRIVATE);
        editor = sp.edit();

        email= findViewById(R.id.dashemail);
        pass=findViewById(R.id.dashpassword);

        String thisemail = sp.getString("email","null");
        String thispassword = sp.getString("password","null");
        email.setText(thisemail);
        pass.setText(thispassword);


        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.apply();
                startActivity(new Intent(Dashboard.this,MainActivity.class));
                finish();
            }
        });
    }
}