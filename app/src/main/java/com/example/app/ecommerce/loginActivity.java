package com.example.app.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.ecommerce.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.app.ecommerce.Model.Users;
import com.rey.material.widget.CheckBox;

import io.paperdb.Paper;

public class loginActivity extends AppCompatActivity {


    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private  String parentdbname = "Users";
    private CheckBox chkBoxRememberme;

    private TextView admin_link , user_link ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.second_login_button);
         InputPhoneNumber = (EditText) findViewById(R.id.login_phonenumber_input);
        InputPassword = (EditText) findViewById(R.id.login_password_input);
        loadingBar = new ProgressDialog(this);
        chkBoxRememberme = findViewById(R.id.CheckBox_Btn);
        admin_link = findViewById(R.id.admin_panel_link);
        user_link = findViewById(R.id.Users_panel_link);
        Paper.init(this);


        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUser();
            }
        });
        admin_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentdbname = "Admins";
                LoginButton.setText("Login Admin");
                admin_link.setText("Not an Admin");

                admin_link.setVisibility(View.INVISIBLE);
                user_link.setVisibility(View.VISIBLE);
            }
        });
        user_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginButton.setText("Login");
                admin_link.setText("I am an Admin");
                parentdbname ="Users";
                admin_link.setVisibility(View.VISIBLE);
                user_link.setVisibility(View.INVISIBLE);
            }
        });
}

    private void LoginUser() {
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();
         if(TextUtils.isEmpty(phone)){
            Toast.makeText(loginActivity.this,"please enter your phone number ", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(loginActivity.this,"please enter your password", Toast.LENGTH_SHORT).show();
        }
        else{
             loadingBar.setTitle("Login Account");
             loadingBar.setMessage("Please wait, while we are checking the creditial");
             loadingBar.setCanceledOnTouchOutside(false);
             loadingBar.show();

            AllowAccessToAccount(phone , password);
         }
    }

    private void AllowAccessToAccount(String phone, String password) {

        if(chkBoxRememberme.isChecked()){
            Paper.book().write(Prevalent.UserPhoneKey,phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);

        }

        final DatabaseReference Rootref;
        Rootref = FirebaseDatabase.getInstance().getReference();

        Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentdbname).child(phone).exists()){
                    Users usersData = snapshot.child(parentdbname).child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone)){
                        if(usersData.getPassword().equals(password))

                        {

                           if(parentdbname.equals("Admins")) {
                                Toast.makeText(loginActivity.this, "Logged in as ADMIN", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(loginActivity.this, Admin_Category_Activity.class);
                                startActivity(intent);
                            }

                           else if(parentdbname.equals("Users"))
                            {
                               Toast.makeText(loginActivity.this, "Logged in Succesfully", Toast.LENGTH_SHORT).show();
                               loadingBar.dismiss();

                               Intent intent = new Intent( loginActivity.this, HomeActivity.class);
                               startActivity(intent);
                           }

                        }
                        else
                        {
                            Toast.makeText(loginActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                }
                else{
                    Toast.makeText(loginActivity.this, "Account with this"+ phone+ "number does not exist" , Toast.LENGTH_SHORT).show();
                     loadingBar.dismiss();}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}