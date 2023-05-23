package com.example.agriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.agriculture.model.CartProduct;
import com.example.agriculture.model.ShippingProduct;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Shipping extends AppCompatActivity {
    private CheckBox online, direct;
    private EditText location;
    private LinearLayout paymentOnline;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    private ArrayList<String> product, productKey;
    private ArrayList<Integer> productQuantity;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("shipping");
    private DatabaseReference databaseCart = FirebaseDatabase.getInstance().getReference("cart");
    private DatabaseReference databaseProduct = FirebaseDatabase.getInstance().getReference("products");
    private Button order;
    private String paymentMethod, locationText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        product = new ArrayList<String>();
        productKey = new ArrayList<String>();
        productQuantity = new ArrayList<Integer>();
        online = findViewById(R.id.online);
        direct = findViewById(R.id.direct);
        location = findViewById(R.id.shippingLocation);
        order = findViewById(R.id.order);
        paymentOnline = findViewById(R.id.paymentOnline);
        paymentOnline.setVisibility(View.INVISIBLE);

        Bundle bundle = getIntent().getExtras();
        ArrayList<CartProduct> list = bundle.getParcelableArrayList("carts");
        for(int i=0; i<list.size(); i++){
            product.add(list.get(i).getKey());
            productKey.add(list.get(i).getProductKey());
            productQuantity.add(list.get(i).getProductQuantity());
        }

        direct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(direct.isChecked()){
                    online.setEnabled(false);
                    paymentOnline.setVisibility(View.INVISIBLE);
                    paymentMethod = "After receiving product";
                }else{
                    online.setEnabled(true);
                    paymentOnline.setVisibility(View.INVISIBLE);
                }
            }
        });

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(online.isChecked()){
                    direct.setEnabled(false);
                    paymentMethod = "Online";
                    paymentOnline.setVisibility(View.VISIBLE);
                }else{
                    direct.setEnabled(true);
                    paymentOnline.setVisibility(View.INVISIBLE);
                }
            }
        });


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadShipping();
            }
        });
    }

    public void uploadShipping(){
        String userID = auth.getCurrentUser().getUid();
        String locationText = location.getText().toString();
        String currentDate = String.valueOf(DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()).hashCode());
        ShippingProduct shipping = new ShippingProduct(currentDate, locationText, product, paymentMethod);

        AlertDialog.Builder builder = new AlertDialog.Builder(Shipping.this);
        builder.setCancelable(false);
        builder.setView(R.layout.activity_progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        database.child(userID).child(currentDate).setValue(shipping).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                dialog.dismiss();
                for(int i=0; i<product.size(); i++){
                    databaseCart.child(product.get(i)).child("Status").setValue("Payment");
                    databaseProduct.child(productKey.get(i)).child("getProductIteQty").setValue()
                }
                Toast.makeText(Shipping.this, "Successful", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }
}