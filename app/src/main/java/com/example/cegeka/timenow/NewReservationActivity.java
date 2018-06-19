package com.example.cegeka.timenow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static java.lang.System.currentTimeMillis;

public class NewReservationActivity extends AppCompatActivity {
    TextView CmpTv;
    Button MakeResBtn;
    EditText HourEt, PersonEt, DateEt;
    String ID,NAME,IdUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        Intent intent = getIntent();
         ID = intent.getStringExtra("ID_COMPANY");
         NAME = intent.getStringExtra("NAME_COMPANY");
        IdUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        CmpTv = (TextView) findViewById(R.id.CompanyResTv);
        CmpTv.setText("Rezervare la " + NAME);
        HourEt = (EditText) findViewById(R.id.HourET);
        PersonEt = (EditText) findViewById(R.id.PersonNoET);
        DateEt = (EditText) findViewById(R.id.DateET);
        MakeResBtn = (Button) findViewById(R.id.SendBtn);


    }
    public void Send(View view)
    {
        MakeResBtn.setEnabled(false);
        String day = DateEt.getText().toString();
        String hour = HourEt.getText().toString();
        String nr_pers_string = PersonEt.getText().toString();
        if(day.equals("") || hour.equals("") || nr_pers_string.equals(""))
            Toast.makeText(NewReservationActivity.this, "Toate campurile sunt obligatorii", Toast.LENGTH_LONG).show();
        else
        {
            int nr_pers = Integer.parseInt(nr_pers_string);
            String x;
            x=HourEt.getText().toString()+" "+DateEt.getText().toString();

            SimpleDateFormat sdf=new SimpleDateFormat("H:mm dd.MM.yyyy");
            try {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
              Date d= sdf.parse(x);
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users").child(ID).child("reservations");
                Random r=new Random();
                for (int i=1;i<90000000;i++);
                Random m=new Random(System.currentTimeMillis()+  r.nextInt());
                String seed=String.valueOf(System.currentTimeMillis())+String.valueOf(user.getUid())+String.valueOf(m.nextLong());

                ref =ref.child(seed);

                ref.child("nume").setValue(user.getDisplayName());
                ref.child("id").setValue(user.getUid());
                ref.child("pers").setValue(Integer.parseInt(PersonEt.getText().toString()));
                ref.child("data").setValue(d);

                finish();

            } catch (ParseException e) {
                Toast.makeText(NewReservationActivity.this,"Format incorect \nFormatul corect este h:mm dd.MM.yyyy ",1).show();
                }
                MakeResBtn.setEnabled(true);
            //functie de trimis rezervarea, cu campurile de data, ora nrpersoane, Id companie si Id client

        }
    }
}