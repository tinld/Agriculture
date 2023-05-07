package com.example.agriculture;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class ProductDetails extends AppCompatActivity {
    Context context;
    Button addToCart, decreaseBtn, increaseBtn;
    TextView numberProduct;
    Integer quantity, totalPrice, price;
    String nameProduct, imageProduct;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("cart");
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

        addToCart = (Button) findViewById(R.id.addtocart);
        increaseBtn = (Button) findViewById(R.id.increaseProduct);
        decreaseBtn = (Button) findViewById(R.id.decreaseProduct);
        numberProduct = (TextView) findViewById(R.id.numberProduct);
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
//
//
//        addToCart.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//
//            }
//        });
    }

    private void addedToCart(){
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        totalPrice = quantity*price;
        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("productName", nameProduct);
        cartMap.put("productPrice", price.toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.toString());
        cartMap.put("totalPrice", totalPrice.toString());


    }
}