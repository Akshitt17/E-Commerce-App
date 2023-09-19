package com.example.app.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Admin_Category_Activity extends AppCompatActivity {

    private ImageView tshirts ,Sports_tshirt, female_dresses , sweaters;
    private ImageView glasses , hats_caps , begs , shoes;
    private ImageView headphones, watches, laptops, mobiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        tshirts = findViewById(R.id.t_Shirts);
        Sports_tshirt = findViewById(R.id.Sports_t_Shirts);
        female_dresses = findViewById(R.id.Female_Dresses);
        sweaters = findViewById(R.id.Sweaters);
        glasses = findViewById(R.id.glasses);
        hats_caps = findViewById(R.id.hats_cap);
        begs = findViewById(R.id.purse_begs_wallet);
        shoes = findViewById(R.id.shoes);
        headphones = findViewById(R.id.headphones);
        watches = findViewById(R.id.watches);
        laptops = findViewById(R.id.laptop);
        mobiles = findViewById(R.id.mobiles);

        tshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "tshirts");
                startActivity(intent);
            }
        });
        Sports_tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "Sports tshirts");
                startActivity(intent);
            }
        });
         female_dresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "female_dresses");
                startActivity(intent);
            }
        });
         sweaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "sweaters");
                startActivity(intent);
            }
        });
        glasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "glasses");
                startActivity(intent);
            }
        });
  hats_caps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "hats_caps");
                startActivity(intent);
            }
        });
  begs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "begs");
                startActivity(intent);
            }
        });
  shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "shoes");
                startActivity(intent);
            }
        });
  headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "headphones");
                startActivity(intent);
            }
        });
  watches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "watches");
                startActivity(intent);
            }
        });
  laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "laptops");
                startActivity(intent);
            }
        });
  mobiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Category_Activity.this , AdminAddNewProductActivity.class);
                intent.putExtra("category" , "mobiles");
                startActivity(intent);
            }
        });


    }
}