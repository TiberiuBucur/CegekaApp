package com.example.cegeka.timenow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MenuActivity extends AppCompatActivity {
    TextView mUserTV;
    ImageButton BellBtn;
    Button SearchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mUserTV=findViewById(R.id.UserTV);
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        updateUI(user);
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

}
