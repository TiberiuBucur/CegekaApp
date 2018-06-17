package com.example.cegeka.timenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    ListView NoteListView = null;
    List<String> adapterList;
    static int hourms = 3600000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ListView NoteListView = (ListView) findViewById(R.id.NoteListView);
        adapterList = new ArrayList<>();
        ArrayList<Notification> lista = null;

        long crt_timestamp = System.currentTimeMillis();
        if(lista != null)
            for(Notification item : lista)
                if(item.timestamp + hourms>crt_timestamp)
                    adapterList.add(item.Message);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(NotificationActivity.this,
                R.layout.note_msg,R.id.NoteTV,adapterList);
        NoteListView.setAdapter(adapter);
    }
}
