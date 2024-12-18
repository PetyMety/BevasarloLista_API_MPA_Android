package com.example.bevasarlolista;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ShoppingItem> shoppingList;
    private ShoppingListAdapter adapter;
    private LinearLayout formLinearLayout;

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

        formLinearLayout = findViewById(R.id.formLinearLayout);

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


        RetrofitApiService apiService = RetrofitClient.getInstance().create(RetrofitApiService.class);
        loadShoppingItem(apiService);

    }

    public void loadShoppingItem(RetrofitApiService apiService) {

        apiService.getAllShoppingItem().enqueue(new Callback<List<ShoppingItem>>() {
            @Override
            public void onResponse(Call<List<ShoppingItem>> call, Response<List<ShoppingItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    shoppingList.clear();
                    shoppingList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Fail to load the people list", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ShoppingItem>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error loading the people list", Toast.LENGTH_SHORT).show();
            }
        });
    }
}