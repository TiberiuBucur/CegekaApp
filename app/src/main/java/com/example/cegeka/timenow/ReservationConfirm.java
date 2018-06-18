package com.example.cegeka.timenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReservationConfirm extends AppCompatActivity {

    TextView MessageTV;
    Button YesBtn, NoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirm);
        MessageTV = (TextView) findViewById(R.id.MessageTv);
        YesBtn = (Button) findViewById(R.id.YesBtn);
        NoBtn = (Button) findViewById(R.id.NoBtn);
    }
    public void Yes(View view)
    {
        setResult(RESULT_OK);
    }
    public void No(View view)
    {
        setResult(RESULT_OK);
    }
}
