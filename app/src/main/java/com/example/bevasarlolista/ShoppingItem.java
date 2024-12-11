package com.example.bevasarlolista;

public class ShoppingItem {

    private String name;
    private int quantity;
    private int price;
    private String category;

    public ShoppingItem(String name, int quantity, int price, String category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }


}
