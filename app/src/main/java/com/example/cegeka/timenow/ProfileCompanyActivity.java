package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileCompanyActivity extends AppCompatActivity {

    TextView PhoneTv, AdressTv, NameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_company);
        PhoneTv = (TextView) findViewById(R.id.PhoneTV);
        AdressTv = (TextView) findViewById(R.id.AdressTV);
        NameTv = (TextView) findViewById(R.id.CmpNameTV);
        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID_COMPANIE");
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users/"+ID);



        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               NameTv.setText(dataSnapshot.child("name").getValue(String.class));
                PhoneTv.setText(dataSnapshot.child("phone").getValue(String.class));
                AdressTv.setText(dataSnapshot.child("adress").getValue(String.class));




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
