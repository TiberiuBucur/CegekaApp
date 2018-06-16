package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class FirstLoginActivity extends AppCompatActivity {

    RadioButton TypeBtn;
    boolean iscompany;
    EditText AdressET, PhoneEt;
    Button SubmitBtn;
    String Adress;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        ref=FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/firstTime");
ref.setValue("yes");

        TypeBtn = (RadioButton) findViewById(R.id.TypeBtn);
        AdressET = (EditText) findViewById(R.id.AdressET);
        PhoneEt = (EditText) findViewById(R.id.PhoneET);
        SubmitBtn = (Button) findViewById(R.id.SubmitBtn);
        AdressET.setEnabled(false);
        TypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TypeBtn.isChecked())
                {
                    AdressET.setEnabled(true);
                    iscompany = true;
                    Adress = AdressET.getText().toString();
                }
                else
                    iscompany = false;
            }
        });

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x;
                x = PhoneEt.getText().toString();
                ref=FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/phone");
                ref.setValue(x);
                ref=FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/type");
                ref.setValue(iscompany);
                Intent intent;
                if(iscompany)
                {
                    ref=FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/adress");
                    ref.setValue(Adress);
                }
                else
                {
                    intent = new Intent(FirstLoginActivity.this, MenuActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

}
