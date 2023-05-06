package com.example.agriculture;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agriculture.model.ProductItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;


public class PostProduct extends AppCompatActivity {

    ImageView uploadImage;
    EditText productName, productPrice, productLocation, productUnit, productQuantity;
    String imageURL;
    Button uploadButton;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_product);

        uploadImage = findViewById(R.id.imageView6);
        productName = findViewById(R.id.productItemName);
        productPrice = findViewById(R.id.productItemPrice);
        productLocation = findViewById(R.id.productItemLocation);
        productUnit = findViewById(R.id.productItemUnit);
        productQuantity = findViewById(R.id.productItemQuantity);
        uploadButton = findViewById(R.id.uploadFile);
//
//        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK){
//                            Intent data = result.getData();
//                            uri = data.getData();
//                            uploadImage.setImageURI(uri);
//                        } else {
//                            Toast.makeText(PostProduct.this, "No Image Selected", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//        uploadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);
//            }
//        });
//
//        uploadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

//    public void saveData(){
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("products")
//                .child(uri.getLastPathSegment());
//        AlertDialog.Builder builder = new AlertDialog.Builder(PostProduct.this);
//        builder.setCancelable(false);
//        builder.setView(R.layout.activity_progress_layout);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//
//        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                while(!uriTask.isComplete());
//                Uri urlImage = uriTask.getResult();
//                imageURL = urlImage.toString();
//                uploadData();
//                dialog.dismiss();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                dialog.dismiss();
//            }
//        });
//    }
//    public void uploadData(){
//        String name = productName.getText().toString();
//        String location = productLocation.getText().toString();
//        String unit = productUnit.getText().toString();
//        Integer price = Integer.valueOf(productPrice.getText().toString());
//        Integer quantity = Integer.valueOf(productQuantity.getText().toString());
//
//        ProductItem product = new ProductItem();
//        product.setProductItemName(name);
//        product.setGetProductIteQty(quantity);
//        product.setLocationItem(location);
//        product.setUnitItem(unit);
//        product.setProductItemPrice(price);
//
//        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
//
//        FirebaseDatabase.getInstance().getReference("products").child(currentDate).setValue(product)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(PostProduct.this, "Saved", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(PostProduct.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
}