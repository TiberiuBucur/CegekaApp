package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileCompanyActivity extends AppCompatActivity {

    TextView PhoneTv, AdressTv, NameTv;
    Button ReserveBtn,RateBtn;
    String ID;
    RatingBar rtb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_company);
        PhoneTv = (TextView) findViewById(R.id.PhoneTV);
        AdressTv = (TextView) findViewById(R.id.AdressTV);
        NameTv = (TextView) findViewById(R.id.CmpNameTV);
        ReserveBtn = (Button) findViewById(R.id.ReservationBtn);
        RateBtn=findViewById(R.id.rateBtn);
        rtb= findViewById(R.id.ratingBar);
        ReserveBtn.setEnabled(false);
        Intent intent = getIntent();
        ID = intent.getStringExtra("ID_COMPANIE");
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users/"+ID);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               NameTv.setText(dataSnapshot.child("name").getValue(String.class));
                PhoneTv.setText(dataSnapshot.child("phone").getValue(String.class));
                AdressTv.setText(dataSnapshot.child("adress").getValue(String.class));
                if(dataSnapshot.child("rating").child("rate").getValue(float.class)!=null)
                rtb.setRating(dataSnapshot.child("rating").child("rate").getValue(float.class));
                ReserveBtn.setEnabled(true);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void goToReservation(View view)
    {
        Intent intent = new Intent(ProfileCompanyActivity.this, NewReservationActivity.class);
        intent.putExtra("ID_COMPANY",ID);
        intent.putExtra("NAME_COMPANY",NameTv.getText().toString());
        startActivity(intent);
        finish();
    }

public void Rate(View view)
{
    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users").child(ID).child("rating");
    ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(rtb.getRating());
    ref.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            float rating=3*10;
            int nratings=10;
            for(DataSnapshot ds:dataSnapshot.getChildren())
            {if(ds.getKey()!="rate") {
                rating += ds.getValue(float.class);
                nratings++;
                }

                dataSnapshot.child("rate").getRef().setValue(rating/nratings);

            }

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });



}


public void Favourite(View view)
{
    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users").child(ID).child("favorite").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
    ref.setValue(true);
}
    public void Scoate(View view)
    {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users").child(ID).child("favorite").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.removeValue();
    }

}
