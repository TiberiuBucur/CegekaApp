package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class NewReservationActivity extends AppCompatActivity {

    TextView CmpTv;
    EditText HourEt, PersonEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID_COMPANY");
        String IdUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        CmpTv = (TextView) findViewById(R.id.CompanyResTv);
        CmpTv.setText("Rezervare la" + IdUser);
        HourEt = (EditText) findViewById(R.id.HourET);
        PersonEt = (EditText) findViewById(R.id.PersonNoET);

    }

}
