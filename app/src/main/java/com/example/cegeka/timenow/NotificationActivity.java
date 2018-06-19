package com.example.cegeka.timenow;

import android.app.Notification;
import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    ListView NoteLv;
    Button deleteBtn;
    ArrayList<String> arrayliststr = new ArrayList<>();
    ArrayList<String> arraylistpos = new ArrayList<>();
    ArrayList<Boolean> arrayListState=new ArrayList<>();

    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        deleteBtn = (Button) findViewById(R.id.DeleteBtn2);
        NoteLv = (ListView) findViewById(R.id.NotificationLV);
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("notifications");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    arrayliststr.add(ds.getValue(String.class));
                    arraylistpos.add(ds.getKey());
                    arrayListState.add(false);
                }
                adapter = new CustomAdapter(NotificationActivity.this, arrayliststr);
                NoteLv.setAdapter(adapter);
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.DeleteSelected();
                        adapter = new CustomAdapter(NotificationActivity.this, arrayliststr);
                        NoteLv.setAdapter(adapter);
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public class CustomAdapter extends BaseAdapter
    {

        String value;
        Context mContext;
        List<CheckedTextView> List = new ArrayList<>();
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
                view = inflater.inflate(R.layout.note_msg, null);
            }
            final CheckedTextView Notification = (CheckedTextView) view.findViewById(R.id.NoteCheckedTV);
            Notification.setHint(arraylistpos.get(position));
            Notification.setText(arrayList.get(position));

            Notification.setOnClickListener(new View.OnClickListener() {


                                                @Override
                                                public void onClick(View v) {

                                                    if(arrayListState.get(position)) {
                                                        Notification.setChecked(false);
                                                        Notification.setCheckMarkDrawable(null);
                                                       arrayListState.set(position,false);
                                                    }
                                                    else {
                                                        Notification.setChecked(true);
                                                        Notification.setCheckMarkDrawable(R.drawable.fui_done_check_mark);
                                                        arrayListState.set(position,true);
                                                    }

                                                }
                                            }
                    );
            List.add(Notification);
            return view;
        }

        public void DeleteSelected()
        {
            for(CheckedTextView a : List)
                if(a.isChecked())
                {
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("notifications").child(a.getHint().toString()).removeValue();

                }
        finish();
        }
    }
}
