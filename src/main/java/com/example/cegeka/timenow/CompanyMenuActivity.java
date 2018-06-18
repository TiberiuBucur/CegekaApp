package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CompanyMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_menu);

        TextView textComp = (TextView)findViewById(R.id.textView);
        textComp.setText("Hello " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
    }

    public void SettingsAct(View view) {
        startActivity(new Intent(CompanyMenuActivity.this,SettingsActivity.class));
    }
}
