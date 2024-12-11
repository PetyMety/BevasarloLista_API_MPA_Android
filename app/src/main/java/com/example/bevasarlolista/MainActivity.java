package com.example.bevasarlolista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ShoppingItem> shoppingList;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shoppingList = new ArrayList<>();
        adapter = new ShoppingListAdapter(this, shoppingList);

        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        EditText itemNameEditText = findViewById(R.id.item_name);
        EditText itemQuantityEditText = findViewById(R.id.item_quantity);
        EditText itemPriceEditText = findViewById(R.id.item_price);
        EditText itemCategoryEditText = findViewById(R.id.item_category);
        Button addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = itemNameEditText.getText().toString();
                int quantity = Integer.parseInt(itemQuantityEditText.getText().toString());
                int price = Integer.parseInt(itemPriceEditText.getText().toString());
                String category = itemCategoryEditText.getText().toString();

                shoppingList.add(new ShoppingItem(name, quantity, price, category));
                adapter.notifyDataSetChanged();
                itemNameEditText.setText("");
                itemQuantityEditText.setText("");
                itemPriceEditText.setText("");
                itemCategoryEditText.setText("");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShoppingItem item = shoppingList.get(position);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("ITEM_NAME", item.getName());
                intent.putExtra("ITEM_QUANTITY", item.getQuantity());
                intent.putExtra("ITEM_PRICE", item.getPrice());
                intent.putExtra("ITEM_CATEGORY", item.getCategory());
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                shoppingList.remove(position);
                adapter.notifyDataSetChanged();
                return true;
            }
        });

        /*
        RetrofitApiService apiService = RetrofitClient.getInstance().create(RetrofitApiService.class);
        loadPeople(apiService);
        showAddFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formLinearLayout.setVisibility(View.VISIBLE);
                showAddFormButton.setVisibility(View.GONE);
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formLinearLayout.setVisibility(View.GONE);
                showAddFormButton.setVisibility(View.VISIBLE);
            }
        });
        */

    }
}