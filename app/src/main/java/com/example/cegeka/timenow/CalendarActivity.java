package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {

    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = (CalendarView) findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent (CalendarActivity.this, DateReservationActivity.class);
                Date d=new Date(year-1900,month,dayOfMonth);

                if(d.getTime()>System.currentTimeMillis()-3*24*60*60*1000)
                {intent.putExtra("ZI", dayOfMonth);
                intent.putExtra("LUNA", month);
                intent.putExtra("AN", year);
                startActivity(intent);}
                else
                {
                    Toast.makeText(CalendarActivity.this,"De prea mult timp",1).show();
                }

            }
        });
    }
}
