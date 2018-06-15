package com.example.cegeka.timenow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;
import java.util.List;


public class FirstMenuActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    private static final int RC_MENU = 10;
    List<AuthUI.IdpConfig> providers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitivity_firstmenu);
        providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build());

       if (FirebaseAuth.getInstance().getCurrentUser() != null) {

           Class activity;
           if(false)//functie database
               activity = MenuActivity.class;
           else
               activity = FirstLoginActivity.class;
           Intent intent = new Intent(FirstMenuActivity.this, activity);
           startActivity(intent);
           finish();
       }
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (requestCode == RC_SIGN_IN && resultCode == RESULT_OK) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    Class activity;
                    if(false)//functie database
                        activity = MenuActivity.class;
                    else
                        activity = FirstLoginActivity.class;
                    Intent intent = new Intent(FirstMenuActivity.this, activity);
                    startActivity(intent);
                    finish();
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



