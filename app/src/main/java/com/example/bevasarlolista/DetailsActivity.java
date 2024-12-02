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

        TextView nameTextView = findViewById(R.id.detail_item_name);
        TextView quantityTextView = findViewById(R.id.detail_item_quantity);
        Button buttonBack = findViewById(R.id.back_button);

        nameTextView.setText(name);
        quantityTextView.setText(String.valueOf(quantity));

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Bejáratott Activity bezárása
            }
        });
    }
}