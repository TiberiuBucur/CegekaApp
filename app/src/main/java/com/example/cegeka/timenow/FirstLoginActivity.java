package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstLoginActivity extends AppCompatActivity {

    RadioButton TypeBtn;
    boolean iscompany;
    EditText AdressET, PhoneEt;
    Button SubmitBtn;
    DatabaseReference ref;
    boolean alreadyCheked;

    //commit gol
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
        TypeBtn = (RadioButton) findViewById(R.id.TypeBtn);
        AdressET = (EditText) findViewById(R.id.AdressET);
        PhoneEt = (EditText) findViewById(R.id.PhoneET);
        SubmitBtn = (Button) findViewById(R.id.SubmitBtn);
        AdressET.setEnabled(false);
        alreadyCheked = false;
        TypeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alreadyCheked) {
                    TypeBtn.setChecked(false);
                    alreadyCheked = false;
                } else
                    alreadyCheked = true;
                if (TypeBtn.isChecked()) {
                    AdressET.setEnabled(true);
                    iscompany = true;
                } else {
                    iscompany = false;
                    AdressET.setEnabled(false);
                }
            }
        });
        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String x, y;
                x = PhoneEt.getText().toString();
                if (x.length() == 10) {

                    ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/type");
                    ref.setValue(iscompany);
                    ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/name");
                    ref.setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/email");
                    ref.setValue(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                    Intent intent;
                    if (iscompany) {
                        y = AdressET.getText().toString();
                        if (x.equals("") || y.equals(""))
                            Toast.makeText(FirstLoginActivity.this, "Toate campurile sunt obligatorii", Toast.LENGTH_LONG).show();
                        else {
                            ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/phone");
                            ref.setValue(x);
                            ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/adress");
                            ref.setValue(y);
                            ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/firstTime");
                            ref.setValue("yes");

                            intent = new Intent(FirstLoginActivity.this, CompanyMenuActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        if (x.equals(""))
                            Toast.makeText(FirstLoginActivity.this, "Campul telefon este obligatoriu", Toast.LENGTH_LONG).show();
                        else {
                            ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/phone");
                            ref.setValue(x);
                            intent = new Intent(FirstLoginActivity.this, MenuActivity.class);
                            ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/firstTime");
                            ref.setValue("yes");
                            startActivity(intent);
                            finish();
                        }
                    }
                }
                else{
                    PhoneEt.setText("Introduceti un numar de telefon valid");
                }

            }
        });
    }

}