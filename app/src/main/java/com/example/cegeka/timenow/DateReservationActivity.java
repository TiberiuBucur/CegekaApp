package com.example.cegeka.timenow;

import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DateReservationActivity extends AppCompatActivity {

    TextView dateTV;
    ListView ReservationsLV;
    ArrayList<Reservation> arraylist;
    int day, month, year;
    final String[] months = {"Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie",
            "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_reservation);
        dateTV = (TextView) findViewById(R.id.DateTv);
        Intent intent = getIntent();
        day = intent.getIntExtra("ZI", -1);
        month = intent.getIntExtra("LUNA", -1);
        year = intent.getIntExtra("AN", -1);
        String date = String.valueOf(day) + " " + months[month] + " " + String.valueOf(year);
        dateTV.setText(date);
        ReservationsLV = (ListView) findViewById(R.id.DayReservationsLV);
        arraylist = new ArrayList<>();
        CustomAdapter adapter = new CustomAdapter(DateReservationActivity.this, arraylist);
        ReservationsLV.setAdapter(adapter);
    }
    public class CustomAdapter extends BaseAdapter
    {
        Context mContext;
        ArrayList<Reservation> arraylist = new ArrayList<>();

        public CustomAdapter(Context context, ArrayList<Reservation> List)
        {
            mContext = context;
            this.arraylist = List;
        }
        @Override
        public int getCount() {
            return arraylist.size();
        }

        @Override
        public Object getItem(int position) {
            return arraylist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View view = convertView;
            if(view == null)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.reservation, null);
            }
            TextView ResTV = (TextView) view.findViewById(R.id.ResDetailTv);
            Reservation res = arraylist.get(position);
            String hour = String.valueOf(res.start_time/60) + ":" + String.valueOf(res.start_time%60);
            ResTV.setText(hour + "\n" + res.client + "\n" + String.valueOf(res.nr_pers));
            ResTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DateReservationActivity.this, ClientProfileActivity.class);
                    startActivity(intent);
                }
            });
            return view;
        }
        public void Sort()
        {
            Collections.sort(arraylist, new Comparator<Reservation>() {
                @Override
                public int compare(Reservation a, Reservation b) {
                    Integer val1, val2;
                    val1 = a.start_time;
                    val2 = b.start_time;
                    return val1.compareTo(val2);
                }
            });
        }
    }
}
