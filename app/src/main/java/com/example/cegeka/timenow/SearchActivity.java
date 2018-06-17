package com.example.cegeka.timenow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    ListView CmpLv;
    EditText SearchEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchEt = (EditText) findViewById(R.id.SearchET);
        CmpLv = (ListView) findViewById(R.id.CompLv);
    }
}
