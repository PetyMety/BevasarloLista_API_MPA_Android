package com.example.bevasarlolista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.List;

public class ShoppingListAdapter extends ArrayAdapter<ShoppingItem> {
    private final Context context;
    private final List<ShoppingItem> items;

    public ShoppingListAdapter(Context context, List<ShoppingItem> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShoppingItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.item_name);
        TextView quantityTextView = convertView.findViewById(R.id.item_quantity);
        TextView priceTextView = convertView.findViewById(R.id.item_price);
        TextView categoryTextView = convertView.findViewById(R.id.item_category);
        CardView cardView = convertView.findViewById(R.id.card_view);

        nameTextView.setText(item.getName());
        quantityTextView.setText(String.valueOf(item.getQuantity()));
        priceTextView.setText(String.valueOf(item.getPrice()));
        categoryTextView.setText(item.getCategory());

        return convertView;
    }
}