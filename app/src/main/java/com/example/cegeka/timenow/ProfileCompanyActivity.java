package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileCompanyActivity extends AppCompatActivity {

    TextView PhoneTv, AdressTv, NameTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_company);
        PhoneTv = (TextView) findViewById(R.id.PhoneTV);
        AdressTv = (TextView) findViewById(R.id.AdressTV);
        NameTv = (TextView) findViewById(R.id.CmpNameTV);
        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID_COMPANIE");

    }
}
