package com.example.agriculture;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.agriculture.model.ProductItem;
import com.example.agriculture.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingFragment extends Fragment {



    public SettingFragment() {
        // Required empty public constructor
    }
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
    CircleImageView profileImg;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    TextView titleName, profileName, profileEmail, profilePhone, profileAddress;
    String fullName, email, phone, address;
    Button editProfile;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        profileImg = view.findViewById(R.id.profile_img);
        titleName = view.findViewById(R.id.titleName);
        profileName = view.findViewById(R.id.profileName);
        profileEmail = view.findViewById(R.id.profileEmail);
        profileAddress = view.findViewById(R.id.profileAddress);
        profilePhone = view.findViewById(R.id.profilePhone);
        editProfile = view.findViewById(R.id.editButton);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UploadProfilePicActivity.class);
                startActivity(intent);
            }
        });
        showUserProfile();

        return view;
    }

    private void showUserProfile() {
        String userID = auth.getCurrentUser().getUid();

        //Extracting User Reference from Database for "Registered Users"
        User userProfile = new User();
        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("users");
        referenceProfile.addValueEventListener(new ValueEventListener() {
            String name, email, address, phone, image;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot keyId: snapshot.getChildren()){
                    if(keyId.child("id").getValue().equals(userID)){
                        name = keyId.child("name").getValue(String.class);
                        email = keyId.child("email").getValue(String.class);
                        address = keyId.child("address").getValue(String.class);
                        phone = keyId.child("phone").getValue(String.class);
                        image = keyId.child("dataImage").getValue(String.class);
                        break;
                    }
                }

                String hello = "Hello, " + name + "!";
                Glide.with(getContext()).load(image).into(profileImg);
                profileName.setText(name);
                profileEmail.setText(email);
                profilePhone.setText(phone);
                profileAddress.setText(address);
                titleName.setText(hello);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        Toast.makeText(getContext(), userID, Toast.LENGTH_SHORT).show();
//        referenceProfile.child(userID).child("name").getKey();
//        titleName.setText("Welcome, " + fullName + "!");
//        profileName.setText(referenceProfile.child(userID).child("name").getValue().toString());
//        profileEmail.setText(email);
//        profilePhone.setText(phone);
//        profileAddress.setText(address);

    }
}