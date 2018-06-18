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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        FirebaseAuth.getInstance().sendPasswordResetEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SettingsActivity.this,"Email sent" , Toast.LENGTH_SHORT).show();
                }
            }
        }
        );
    }

    public void ChangeEmail(View view) {

        try{

        EditText newEmail = findViewById(R.id.EmailText);
     //   FirebaseAuth.getInstance().getCurrentUser().updateEmail(newEmail.getText().toString());

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/email");
        ref.setValue(newEmail.getText().toString());

        } catch (Exception ex){
            Toast.makeText(this, ex.getMessage().toString(),Toast.LENGTH_SHORT);
        }

    }

    public void ChangeNumber(View view){
        try{

        EditText newNumber = findViewById(R.id.NumberText);
      //  FirebaseAuth.getInstance().getCurrentUser().updatePhoneNumber((PhoneAuthCredential)newNumber.getText());

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/phone");
        ref.setValue(newNumber.getText().toString());

    } catch (Exception ex){
        Toast.makeText(this, ex.getMessage().toString(),Toast.LENGTH_SHORT);
    }
    }

    public void DoneButt(View view){
        this.finish();
    }
}
