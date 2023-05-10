package com.example.agriculture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    EditText editName, editEmail, editPhone, editPassword, editAddress;
    Button saveButton;
    String nameUser, emailUser, phoneUser, passwordUser, addressUser;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("users");

        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        editPhone = findViewById(R.id.editPhone);
        editPassword = findViewById(R.id.editPassword);
        editAddress = findViewById(R.id.editAddress);
        saveButton = findViewById(R.id.saveButton);

        showData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNameChanges() || isEmailChanges() || isPhoneChanges() || isPasswordChanges() || isAddressChanges()) {
                    Toast.makeText(EditProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EditProfileActivity.this, "No Changes Found", Toast.LENGTH_SHORT).show();
                }

//                Intent intent = new Intent(EditProfileActivity.this, SettingFragment.class);
//                String name = editName.getText().toString();
//                String phone = editPhone.getText().toString();
//                String address = editAddress.getText().toString();
//                intent.putExtra("name", name);
//                intent.putExtra("phone", phone);
//                intent.putExtra("address", address);
//
//                startActivity(intent);
//                finish();
            }
        });
    }

    public boolean isNameChanges() {
        if (!nameUser.equals(editName.getText().toString())){
            reference.child(nameUser).child("name").setValue(editName.getText().toString());
            nameUser = editName.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmailChanges() {
        if (!emailUser.equals(editEmail.getText().toString())){
            reference.child(emailUser).child("email").setValue(editEmail.getText().toString());
            emailUser = editEmail.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isPhoneChanges() {
        if (!phoneUser.equals(editPhone.getText().toString())){
            reference.child(phoneUser).child("phone").setValue(editPhone.getText().toString());
            phoneUser = editPhone.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isPasswordChanges() {
        if (!passwordUser.equals(editPassword.getText().toString())){
            reference.child(passwordUser).child("password").setValue(editPassword.getText().toString());
            passwordUser = editPassword.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public boolean isAddressChanges() {
        if (!addressUser.equals(editAddress.getText().toString())){
            reference.child(addressUser).child("address").setValue(editAddress.getText().toString());
            addressUser = editAddress.getText().toString();
            return true;
        } else {
            return false;
        }
    }

    public void showData(){
        Intent intent = getIntent();

        nameUser = intent.getStringExtra("name");
        emailUser = intent.getStringExtra("email");
        phoneUser = intent.getStringExtra("phone");
        passwordUser = intent.getStringExtra("password");
        addressUser = intent.getStringExtra("address");

        editName.setText(nameUser);
        editEmail.setText(emailUser);
        editPhone.setText(phoneUser);
        editPassword.setText(passwordUser);
        editAddress.setText(addressUser);
    }
}