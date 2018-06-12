package com.example.cegeka.timenow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MenuActivity extends AppCompatActivity {
    EditText mUserET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mUserET=findViewById(R.id.UserET);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

       updateUI(user);

       DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("message");

       myRef.setValue("Hello, World!");





    }



     private void updateUI(FirebaseUser user)
    {
        if(user!=null)
      mUserET.setText(user.getDisplayName());
    }


    public void SignOff(View view)
    {
        setResult(RESULT_OK);
      FirebaseAuth.getInstance().signOut();
       finish();


    }



}
