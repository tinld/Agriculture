package com.example.agriculture;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetails extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = context;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ImageButton returnPage = (ImageButton) findViewById(R.id.imageReturn);

        TextView nameItem = (TextView) findViewById(R.id.textView2);
        Bundle itemData = getIntent().getExtras();
        nameItem.setText(itemData.getString("Name"));

        ImageView imageItem = (ImageView) findViewById(R.id.imageView8);
        imageItem.setImageResource(itemData.getInt("Image"));

        TextView priceItem = (TextView) findViewById(R.id.textView3);
        priceItem.setText(String.valueOf(itemData.getInt("Price")));

        returnPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}