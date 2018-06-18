package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class ReservationConfirm extends AppCompatActivity {

    TextView MessageTV;
    Button YesBtn, NoBtn;
    String id,idu,nume,pers;
    Date d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirm);
        MessageTV = (TextView) findViewById(R.id.MessageTv);
        YesBtn = (Button) findViewById(R.id.YesBtn);
        NoBtn = (Button) findViewById(R.id.NoBtn);
        Intent intent= getIntent();
        intent.getStringExtra("ID");

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("reservations").child(id);
ref.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
         d=dataSnapshot.child("data").getValue(Date.class);
        idu=dataSnapshot.child("id").getValue(String.class);
        nume=dataSnapshot.child("nume").getValue(String.class);
        pers=dataSnapshot.child("pers").getValue(String.class);




    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

    }
    public void Yes(View view)
    {




        setResult(RESULT_OK);
    }




    public void No(View view)
    {
        setResult(RESULT_OK);
    }
}
