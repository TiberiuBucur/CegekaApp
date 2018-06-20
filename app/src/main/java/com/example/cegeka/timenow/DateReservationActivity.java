package com.example.cegeka.timenow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class DateReservationActivity extends AppCompatActivity {

    TextView dateTV;
    ListView ReservationsLV;
    ArrayList<Reservation> arrayList;
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
        arrayList = new ArrayList<>();
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("reservations");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot ds:dataSnapshot.getChildren())
              {
                 if(ds.child("yes").getValue(Boolean.class)!=null)
                     if(ds.child("yes").getValue(Boolean.class)==true) {
                     Date d = ds.child("data").getValue(Date.class);

                     if (d.getDate() == day && d.getMonth() == month && d.getYear()+1900 == year) {
                         Reservation r = new Reservation();
                         r.client = ds.child("nume").getValue(String.class);
                         r.nr_pers = ds.child("pers").getValue(int.class);
                         r.start_time=d.getMinutes()+d.getHours()*60;
                         arrayList.add(r);
                     }

                 }
                 }
                CustomAdapter adapter = new CustomAdapter(DateReservationActivity.this, arrayList);
                adapter.Sort();
                ReservationsLV.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
