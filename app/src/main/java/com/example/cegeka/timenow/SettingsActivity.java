package com.example.cegeka.timenow;

import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity {

TextView mparola,memail,mnumar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
memail=findViewById(R.id.EmailText);
mnumar=findViewById(R.id.NumberText);
mparola=findViewById(R.id.TextParola);


    }

    public void ChangePassword(View view){
        FirebaseAuth.getInstance().sendPasswordResetEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful())
                    Toast.makeText(SettingsActivity.this,"Email sent for password change" , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ChangeEmail(View view) {



        FirebaseAuth.getInstance().signInWithEmailAndPassword(FirebaseAuth.getInstance().getCurrentUser().getEmail(),mparola.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseAuth.getInstance().getCurrentUser().updateEmail(memail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SettingsActivity.this,"Email schimbat",1).show();
                    }
                });


            }
        });




    }

    public void ChangeNumber(View view){

    }

    public void DoneButt(View view){
        this.finish();
    }
}
