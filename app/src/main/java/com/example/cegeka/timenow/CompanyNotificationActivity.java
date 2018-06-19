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
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CompanyNotificationActivity extends AppCompatActivity {

    ListView NoteLv;
    ArrayList<String> arrayliststr = new ArrayList<>();
    ArrayList<String> arraylistid = new ArrayList<>();
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_notification);
        NoteLv = (ListView) findViewById(R.id.NotificationLV);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("reservations");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("yes").getValue(boolean.class) == null) {

                        arrayliststr.add(ds.child("nume").getValue(String.class));
                        arraylistid.add(ds.getKey());
                    }
                    else if(!ds.child("yes").getValue(boolean.class))
                        ds.getRef().removeValue();

                }

                adapter = new CustomAdapter(CompanyNotificationActivity.this, arrayliststr);
                NoteLv.setAdapter(adapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public class CustomAdapter extends BaseAdapter
    {
        Context mContext;
        ArrayList<Button> List = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        public CustomAdapter(Context context, ArrayList<String> List)
        {
            mContext = context;
            this.arrayList = List;
        }
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.company_button, null);
            }
            Button ReservationBtn = (Button) view.findViewById(R.id.CmpBtn);
            ReservationBtn.setText("Rezervare de la "+ arrayList.get(position));
            ReservationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(CompanyNotificationActivity.this, ReservationConfirm.class);
                    intent.putExtra("ID",arraylistid.get(position));
                    startActivity(intent);
                    finish();
                }
            });
            return view;
        }
    }
}
