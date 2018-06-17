package com.example.cegeka.timenow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.sql.Ref;
import java.util.Arrays;
import java.util.List;


public class FirstMenuActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private static final int RC_MENU = 10;
    private Intent firstintent;
    List<AuthUI.IdpConfig> providers;
    Button mBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitivity_firstmenu);
        providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build());
       mBut=findViewById(R.id.SignInBtn);

       if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            mBut.setEnabled(false);
           FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
           DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid());
           ref.addListenerForSingleValueEvent(new ValueEventListener() {

               @Override
               public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   if (dataSnapshot.child("firstTime").getValue(String.class) == null)
                       startActivity(new Intent(FirstMenuActivity.this, FirstLoginActivity.class));

                   else
                   {
                       if(dataSnapshot.child("type").getValue(boolean.class))
                           startActivity(new Intent(FirstMenuActivity.this,companyScreenActivity.class));
                       else
                           startActivity(new Intent(FirstMenuActivity.this, MenuActivity.class));
                   }
                   mBut.setEnabled(true);
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {
               mBut.setEnabled(true);
               }
           });


       }
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mBut.setEnabled(false);
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users/"+user.getUid());

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("firstTime").getValue(String.class)==null)
                                startActivity(new Intent(FirstMenuActivity.this, FirstLoginActivity.class));
                            else
                            {
                                if(dataSnapshot.child("type").getValue(boolean.class))
                                    startActivity(new Intent(FirstMenuActivity.this,companyScreenActivity.class));
                                else
                                    startActivity(new Intent(FirstMenuActivity.this, MenuActivity.class));
                            }
                            mBut.setEnabled(true);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        mBut.setEnabled(true);
                        }
                    });


                }
            }
    }

    public void Sign(View view) {

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);

    }
}



