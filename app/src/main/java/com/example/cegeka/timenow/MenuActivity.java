package com.example.cegeka.timenow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MenuActivity extends AppCompatActivity {
    EditText mUserET;
    ImageButton BellBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mUserET=findViewById(R.id.UserET);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        updateUI(user);
        BellBtn = (ImageButton) findViewById(R.id.BellBtn);
        BellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, NotificationActivity.class);
                BellBtn.setBackgroundResource(R.drawable.bell2);
            }
        });
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
