package com.example.cegeka.timenow;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientProfileActivity extends AppCompatActivity {
String id;
TextView mPhoneTV,mNameTV,mEmailTV;
RatingBar mRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        mPhoneTV=findViewById(R.id.PhoneNoTv);
        mNameTV=findViewById(R.id.ClientNameTv);
        mEmailTV=findViewById(R.id.E_mailTv);
           mRating=findViewById(R.id.ClientRating);

        id=getIntent().getStringExtra("ID");
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users").child(id);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               mNameTV.setText(dataSnapshot.child("name").getValue(String.class));
               mPhoneTV.setText(dataSnapshot.child("phone").getValue(String.class));
               mEmailTV.setText(dataSnapshot.child("email").getValue(String.class));
               Float f=dataSnapshot.child("rating").child("rate").getValue(Float.class);
               if(f!=null)
                   mRating.setRating(f);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
