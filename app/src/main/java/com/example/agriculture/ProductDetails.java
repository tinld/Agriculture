package com.example.agriculture;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.agriculture.model.CartProduct;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class ProductDetails extends AppCompatActivity {
    Context context;
    private Button addToCart, decreaseBtn, increaseBtn;
    TextView numberProduct;
    Integer totalPrice, price;
    Integer quantity = 1;
    String nameProduct, imageProduct, unitProduct, keyProduct;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("cart");
    CartProduct cart;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = context;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ImageButton returnPage = (ImageButton) findViewById(R.id.imageReturn);

        TextView nameItem = (TextView) findViewById(R.id.textView2);
        Bundle itemData = getIntent().getExtras();
        nameProduct = itemData.getString("Name");
        nameItem.setText(nameProduct);

        imageProduct = itemData.getString("Image");
        ImageView imageItem = (ImageView) findViewById(R.id.imageView8);
        Glide.with(this).load(imageProduct).into(imageItem);

        keyProduct = itemData.getString("Key");
        unitProduct = itemData.getString("Unit");
        TextView priceItem = (TextView) findViewById(R.id.textView3);

        price = itemData.getInt("Price");
        priceItem.setText(String.valueOf(price));


        Integer maxQuantity = itemData.getInt("Number");

        returnPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addToCart = findViewById(R.id.addToCart);
        increaseBtn = findViewById(R.id.increaseProduct);
        decreaseBtn = findViewById(R.id.decreaseProduct);
        numberProduct = findViewById(R.id.numberProduct);
        numberProduct.setText("1");

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(numberProduct.getText().toString());
                quantity++;
                if(quantity > maxQuantity){
                    quantity = maxQuantity;
                    Toast.makeText(ProductDetails.this, "Can not higher than quantity", Toast.LENGTH_SHORT).show();
                }
                numberProduct.setText(String.valueOf(quantity));
            }
        });

        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity = Integer.parseInt(numberProduct.getText().toString());
                quantity--;
                if(quantity < 0){
                    quantity = 0;
                    Toast.makeText(ProductDetails.this, "Can not smaller than 0", Toast.LENGTH_SHORT).show();
                }
                numberProduct.setText(String.valueOf(quantity));
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });
    }

    private void addedToCart(){
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        String keyCart = String.valueOf(DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()).hashCode());
        totalPrice = quantity*price;
        String userID = auth.getCurrentUser().getUid();
        cart = new CartProduct(quantity, price, nameProduct, unitProduct, imageProduct, currentDate, keyCart,userID);

        database.child(keyCart).setValue(cart)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}