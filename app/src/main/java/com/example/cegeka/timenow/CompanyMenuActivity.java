package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CompanyMenuActivity extends AppCompatActivity {


    RatingBar rtb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_menu);

        TextView textComp = (TextView) findViewById(R.id.textView);
        rtb = findViewById(R.id.ratingBar2);
        textComp.setText("Hello " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
        if(!FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
        {
            startActivity(new Intent(CompanyMenuActivity.this,NoVerficationActivity.class));
            finish();
        }


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("rating").child("rate").getValue(float.class) != null)
                    rtb.setRating(dataSnapshot.child("rating").child("rate").getValue(float.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void goToCalendar(View view)
    {
        startActivity(new Intent(CompanyMenuActivity.this, CalendarActivity.class));
    }
    public void goToNotification(View view)
    {
        startActivity(new Intent(CompanyMenuActivity.this, CompanyNotificationActivity.class));
    }
    public void SettingsAct(View view) {
        startActivity(new Intent(CompanyMenuActivity.this,SettingsActivity.class));
    }
    public void SignOff(View view)
    {
        setResult(RESULT_OK);
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}
