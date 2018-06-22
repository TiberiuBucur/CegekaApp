package com.example.cegeka.timenow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MenuActivity extends AppCompatActivity {
    TextView mUserTV;
    ImageButton BellBtn;
    Button SearchBtn;
    RatingBar rtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mUserTV=findViewById(R.id.UserTV);
        rtb=findViewById(R.id.ratingBar2);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        updateUI(user);
        if(!user.isEmailVerified())
        {
         startActivity(new Intent(MenuActivity.this,NoVerficationActivity.class));
         finish();
        }
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid()).child("notifications");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount()>0)
                    BellBtn.setImageResource(R.drawable.bell2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



         ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid());

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("rating").child("rate").getValue(Float.class) != null)
                    rtb.setRating(dataSnapshot.child("rating").child("rate").getValue(float.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        SearchBtn = (Button) findViewById(R.id.SearchBtn);
        BellBtn = (ImageButton) findViewById(R.id.BellBtn);
        BellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, NotificationActivity.class);
                startActivity(intent);
                BellBtn.setImageResource(R.drawable.bell);
            }
        });
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
     private void updateUI(FirebaseUser user)
    {
        if(user!=null)
            mUserTV.setText(user.getDisplayName());
    }

    public void SignOff(View view)
    {
        setResult(RESULT_OK);
        FirebaseAuth.getInstance().signOut();
        finish();
    }
    public void goToSettings(View view)
    {
        Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
    public void goToFavorites(View view)
    {
        Intent intent = new Intent(MenuActivity.this, FavoritesActivity.class);
        startActivity(intent);
    }

}
