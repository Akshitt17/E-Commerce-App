package com.example.app.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    private Button LogoutButton;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LogoutButton =(Button)findViewById(R.id.LogoutButton);

        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Paper.book().destroy();

                Intent intent = new Intent( HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}