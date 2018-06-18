package com.example.cegeka.timenow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    ListView NoteLv = (ListView) findViewById(R.id.NoteListView);
    Button deleteBtn;
    ArrayList<String> arrayliststr = new ArrayList<>();
    ArrayList<String> arraylistpos = new ArrayList<>();

    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        deleteBtn = (Button) findViewById(R.id.DeleteBtn);

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("notifications");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                      arrayliststr.add(ds.getValue(String.class));
                      arraylistpos.add(ds.getKey());
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.note_msg, null);
            }
            CheckedTextView Notification = (CheckedTextView) view.findViewById(R.id.NoteCheckedTV);
            Notification.setHint(arraylistpos.get(position));
            Notification.setText(arrayList.get(position));
            List.add(Notification);
            return view;
        }

        public void DeleteSelected()
        {
            for(CheckedTextView a : List)
                if(a.isChecked())
                {
                    FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("notifications").child(a.getHint().toString()).removeValue();
                    arrayliststr.remove(a.getText().toString());
                    arraylistpos.remove(a.getHint().toString());
                    List.remove(a);
                }
        }
    }
}
