package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
                    ref=FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/firstTime");
                    ref.setValue("yes");
                    ref=FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/name");
                    ref.setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
                    intent = new Intent(FirstLoginActivity.this,CompanyMenuActivity.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    intent = new Intent(FirstLoginActivity.this, MenuActivity.class);
                    ref=FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid()+"/firstTime");
                    ref.setValue("yes");
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

}
