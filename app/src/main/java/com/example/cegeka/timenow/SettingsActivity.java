package com.example.cegeka.timenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView email = findViewById(R.id.textView2);
        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());

        TextView nrTel = findViewById(R.id.textView3);

        //nrTel.setText(FirebaseAuth.getInstance().getCurrentUser().getNrTel().toString());
    }
}
