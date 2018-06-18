package com.example.cegeka.timenow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.lang.System.currentTimeMillis;

public class NewReservationActivity extends AppCompatActivity {
    TextView CmpTv;
    Button MakeResBtn;
    EditText HourEt, PersonEt, DateEt;
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
        DateEt = (EditText) findViewById(R.id.DateET);
        MakeResBtn = (Button) findViewById(R.id.SendBtn);

    }
    public void Send(View view)
    {
        String day = DateEt.getText().toString();
        String hour = HourEt.getText().toString();
        String nr_pers_string = PersonEt.getText().toString();
        if(day.equals("") || hour.equals("") || nr_pers_string.equals(""))
            Toast.makeText(NewReservationActivity.this, "Toate campurile sunt obligatorii", Toast.LENGTH_LONG).show();
        else
        {
            int nr_pers = Integer.parseInt(nr_pers_string);
            //functie de trimis rezervarea, cu campurile de data, ora nrpersoane, Id companie si Id client
            finish();
        }
    }
}