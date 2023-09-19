package com.example.app.ecommerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AdminAddNewProductActivity extends AppCompatActivity {
    private String CategoryName;
    private ImageView select_product_image;
    private EditText product_name, product_description, product_price;
    private Button add_new_product_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_new_product);

        CategoryName = getIntent().getExtras().get("category").toString();

        select_product_image = (ImageView) findViewById(R.id.select_product_image);
        product_name = (EditText) findViewById(R.id.product_name);
        product_description = (EditText) findViewById(R.id.product_description);
        product_price = (EditText) findViewById(R.id.product_price);
        add_new_product_button = (Button) findViewById(R.id.add_new_product_button);
    }
}
