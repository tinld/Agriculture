package com.example.agriculture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.agriculture.model.ProductItem;
import com.example.agriculture.model.ShippingProduct;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Shipping extends AppCompatActivity {
    private CheckBox online, direct;
    private EditText location;
    private LinearLayout paymentOnline;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    private Integer quanPro;
    private String keyPro;
    private Integer flag = 0;

    private Integer newQuantity;
    private final ArrayList<String> productKey = new ArrayList<String>();
    private final ArrayList<String> product = new ArrayList<String>();
    private final ArrayList<Integer> productAllQuantity = new ArrayList<Integer>();
    private final ArrayList<Integer> productQuantity = new ArrayList<Integer>();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("shipping");
    private DatabaseReference databaseCart = FirebaseDatabase.getInstance().getReference("cart");
    private DatabaseReference databaseProduct = FirebaseDatabase.getInstance().getReference("products");
    private Button order;
    private String paymentMethod, locationText;
    private Integer updateQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
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
                for(int i=0; i<product.size(); i++){
                    keyPro = productKey.get(i);
                    quanPro = productQuantity.get(i);
                    databaseCart.child(product.get(i)).child("status").setValue("Payment");
                    combineProduct(keyPro, quanPro);
//                    Toast.makeText(Shipping.this, quanPro.toString(), Toast.LENGTH_SHORT).show();
//                    databaseProduct.child(keyPro).child("getProductIteQty").setValue(newQuantity - productQuantity.get(i));
                }
                dialog.dismiss();
                transformMainActivity();
            }
        });
    }
    public void combineProduct(String key, Integer quantity){
        databaseProduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    if(itemSnapshot.exists()){
                        if(itemSnapshot.child("key").getValue().equals(key)){
                            if(flag < product.size()){
                                newQuantity = Integer.parseInt(itemSnapshot.child("getProductIteQty").getValue().toString());
                                updateProduct(key, quantity, newQuantity);
                                flag = flag + 1;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void updateProduct(String key, Integer quantity, Integer newQuan){
        Integer result = newQuan - quantity;
        if(result > 0){
            databaseProduct.child(key).child("getProductIteQty").setValue(result).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });
        }
    }
    public void transformMainActivity(){
        new Timer().schedule(
                new TimerTask(){
                    @Override
                    public void run(){
                        Intent intent = new Intent(Shipping.this, HomeFragment.class);
                        startActivity(intent);
                    }
                }, 2000);
    }

}