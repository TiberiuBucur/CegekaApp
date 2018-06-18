package com.example.cegeka.timenow;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CompanyNotificationActivity extends AppCompatActivity {

    ListView NoteLv = (ListView) findViewById(R.id.NotificationLV);
    ArrayList<String> arraylist = new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 13 && resultCode == RESULT_OK)
        {





        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_notification);

        CustomAdapter adapter = new CustomAdapter(CompanyNotificationActivity.this, arraylist);
        NoteLv.setAdapter(adapter);
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.note_msg, null);
            }
            Button ReservationBtn = (Button) view.findViewById(R.id.CmpBtn);
            ReservationBtn.setText(arrayList.get(position));
            ReservationBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CompanyNotificationActivity.this, ReservationConfirm.class);

                    startActivityForResult(intent, 13);
                }
            });

            return view;
        }
    }
}
