package com.example.cegeka.timenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class NoVerficationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_verfication);



    }

public void Verify(View view)
{
    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();

}

}


