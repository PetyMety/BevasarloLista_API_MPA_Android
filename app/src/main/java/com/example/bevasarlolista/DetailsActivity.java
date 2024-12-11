package com.example.bevasarlolista;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        String name = getIntent().getStringExtra("ITEM_NAME");
        int quantity = getIntent().getIntExtra("ITEM_QUANTITY", 0);
        int price = getIntent().getIntExtra("ITEM_PRICE", 0);
        String category = getIntent().getStringExtra("ITEM_CATEGORY");

        TextView nameTextView = findViewById(R.id.detail_item_name);
        TextView quantityTextView = findViewById(R.id.detail_item_quantity);
        TextView priceTextView = findViewById(R.id.detail_item_price);
        TextView categoryTextView = findViewById(R.id.detail_item_category);

        Button buttonBack = findViewById(R.id.back_button);

        nameTextView.setText(name);
        quantityTextView.setText(String.valueOf(quantity));
        priceTextView.setText(String.valueOf(price));
        categoryTextView.setText(category);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Bejáratott Activity bezárása
            }
        });
    }
}