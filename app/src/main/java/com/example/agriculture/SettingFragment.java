package com.example.agriculture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingFragment extends Fragment {



    public SettingFragment() {
        // Required empty public constructor
    }

    CircleImageView profileImg;
    TextView profileName, profileEmail, profilePhone, profileAddress, profilePassword;
    TextView titleName;
    Button editProfile;

    Button updateImg;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        profileImg = view.findViewById(R.id.profile_img);
        profileName = view.findViewById(R.id.profileName);
        profileEmail = view.findViewById(R.id.profileEmail);
        profilePassword = view.findViewById(R.id.profilePassword);
        profileAddress = view.findViewById(R.id.profileAddress);
        profilePhone = view.findViewById(R.id.profilePhone);
        editProfile = view.findViewById(R.id.editButton);
        updateImg = view.findViewById(R.id.btnUpdateImg);

        Intent intent = getActivity().getIntent();
        String userEmail = intent.getStringExtra("profileEmail"); ;
        String userPassword = intent.getStringExtra("profilePassword");

        profileEmail.setText(userEmail);
        profilePassword.setText(userPassword);

//        showUserData();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                passUserData();
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                String name = profileName.getText().toString();
                String email = profileEmail.getText().toString();
                String phone = profilePhone.getText().toString();
                String password = profilePassword.getText().toString();
                String address = profileAddress.getText().toString();
                intent.putExtra("name", name);
                intent.putExtra("email",email);
                intent.putExtra("phone", phone);
                intent.putExtra("password", password);
                intent.putExtra("address", address);

                startActivity(intent);


            }
        });

        profileImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 33);
            }
        });
        updateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();
            }


        });

        return  view;
    }

//    private void showUserData() {
//        Intent intent = getActivity().getIntent();
//        String userEmail = intent.getStringExtra("profileEmail"); ;
//        String userPassword = intent.getStringExtra("profilePassword");

//        String userName = intent.getStringExtra("name");
//        String userPhone = intent.getStringExtra("phone");
//        String userAddress = intent.getStringExtra("address");

//        titleName.setText(userName);
//        profileName.setText(userName);
//        profilePhone.setText(userPhone);
//        profileAddress.setText(userAddress);
//        profileEmail.setText(userEmail);
//        profilePassword.setText(userPassword);

//    }

//    public void passUserData() {
//        String userName = profileEmail.getText().toString().trim();
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUserDatabase = reference.orderByChild("username").equalTo(userName);
//
//        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    String nameFromDB = snapshot.child(userName).child("name").getValue(String.class);
//                    String emailFromDB = snapshot.child(userName).child("email").getValue(String.class);
//                    String phoneFromDB = snapshot.child(userName).child("phone").getValue(String.class);
//                    String passwordFromDB = snapshot.child(userName).child("password").getValue(String.class);
//                    String addressFromDB = snapshot.child(userName).child("address").getValue(String.class);
//
//                    Intent intent = new Intent(getActivity(), EditProfileActivity.class);
//
//                    intent.putExtra("name", nameFromDB);
//                    intent.putExtra("email", emailFromDB);
//                    intent.putExtra("phone", phoneFromDB);
//                    intent.putExtra("password", passwordFromDB);
//                    intent.putExtra("address", addressFromDB);
//
//                    startActivity(intent);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private void updateUserProfile() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data.getData() != null){
            Uri profileUri = data.getData();
            profileImg.setImageURI(profileUri);

            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                    .child("profileImg").setValue(uri.toString());
                            Toast.makeText(getContext(), "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}