package com.example.cegeka.timenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;

public class SettingsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView email = findViewById(R.id.CmpNameTV);
        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail().toString());

        TextView nrTel = findViewById(R.id.PhoneTV);

        //nrTel.setText(FirebaseAuth.getInstance().getCurrentUser().getNrTel().toString());
    }

    public void ChangePassword(View view){
        EditText oldPass = findViewById(R.id.editText);
        EditText newPass = findViewById(R.id.editText1);

   //     if(oldPass.getText().toString() == FirebaseAuth.getInstance().getCurrentUser().reauthenticate().toString()){
            FirebaseAuth.getInstance().getCurrentUser().updatePassword(newPass.getText().toString());
        //}
    }

    public void ChangeEmail(View view){
        EditText newEmail = findViewById(R.id.EmailText);
        FirebaseAuth.getInstance().getCurrentUser().updateEmail(newEmail.getText().toString());
    }

    public void ChangeNumber(View view){
        EditText newNumber = findViewById(R.id.NumberText);
        FirebaseAuth.getInstance().getCurrentUser().updatePhoneNumber((PhoneAuthCredential)newNumber.getText());
    }

    public void DoneButt(View view){
        this.finish();
    }
}
