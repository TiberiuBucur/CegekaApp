package com.example.cegeka.timenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class companyScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_screen);

        TextView text1 = (TextView)findViewById(R.id.textView);
        text1.setText("Hello");
    }
}
