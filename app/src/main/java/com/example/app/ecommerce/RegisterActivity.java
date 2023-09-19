package com.example.app.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button CreateAccountButton;
    private EditText Inputname , InputPhoneNumber, InputPassword;
    private ProgressDialog loadingBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        CreateAccountButton = (Button) findViewById(R.id.Register_Button);
        Inputname = (EditText) findViewById(R.id.Register_username_input);
        InputPhoneNumber = (EditText) findViewById(R.id.Register_phonenumber_input);
        InputPassword = (EditText) findViewById(R.id.Register_password_input);
        loadingBar = new ProgressDialog(this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccount();
            }

            private void CreateAccount() {
                String name = Inputname.getText().toString();
                String phone = InputPhoneNumber.getText().toString();
                String password = InputPassword.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this,"please enter your name", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(phone)){
                    Toast.makeText(RegisterActivity.this,"please enter your phone number ", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this,"please enter your password", Toast.LENGTH_SHORT).show();
                }
                else{
                    loadingBar.setTitle("Create Account");
                    loadingBar.setMessage("Please wait, while we are checking the creditial");
                    loadingBar.setCanceledOnTouchOutside(false);
                    loadingBar.show();

                    Validatephonenumber(name , phone, password);
                }
            }

            private void Validatephonenumber(String name, String phone, String password) {

                final DatabaseReference Rootref;
                Rootref = FirebaseDatabase.getInstance().getReference();

                Rootref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(!(snapshot.child("Users").child(phone).exists()) ){
                            HashMap<String , Object> userDataMap = new HashMap<>();
                            userDataMap.put("phone" , phone);
                            userDataMap.put("password" , password);
                            userDataMap.put("name" , name);

                           Rootref.child("Users").child(phone).updateChildren(userDataMap)
                                   .addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if (task.isSuccessful())
                                       {
                                               Toast.makeText(RegisterActivity.this, "Congratulations, Your account has been succesfully created", Toast.LENGTH_SHORT).show();
                                               loadingBar.dismiss();

                                           Intent intent = new Intent(RegisterActivity.this , loginActivity.class);
                                           startActivity(intent);
                                       }
                                           else {
                                               loadingBar.dismiss();
                                               Toast.makeText(RegisterActivity.this, "Network Error , Please try again", Toast.LENGTH_SHORT).show();
                                           }
                                       }
                                   })     ;
                        }
                        else{
                            Toast.makeText(RegisterActivity.this , "This" + phone + "already exist " , Toast.LENGTH_SHORT ).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(RegisterActivity.this , MainActivity.class);
                            startActivity(intent);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });
    }
}