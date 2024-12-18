package com.example.bevasarlolista;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApiService {

    // GET all products
    @GET("gIvxIY/data")
    Call<List<ShoppingItem>> getAllShoppingItem();

    // GET product by ID
    @GET("gIvxIY/data/{id}")
    Call<ShoppingItem> getShoppingItemById(@Path("id") int id);

    // POST (create a new product)
    @POST("gIvxIY/data")
    Call<ShoppingItem> createShoppingItem(@Body ShoppingItem shoppingItem);

    // PUT (update a product)
    @PUT("gIvxIY/data/{id}")
    Call<ShoppingItem> updateShoppingItem(@Path("id") int id, @Body ShoppingItem shoppingItem);

    // DELETE (delete a product by ID)
    @DELETE("gIvxIY/data/{id}")
    Call<Void> deleteShoppingItem(@Path("id") int id);
}
