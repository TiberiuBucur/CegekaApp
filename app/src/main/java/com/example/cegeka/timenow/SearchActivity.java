package com.example.cegeka.timenow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {

    String[] Names, IDs;
    ListView CmpLv;
    EditText SearchEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchEt = (EditText) findViewById(R.id.SearchET);
        CmpLv = (ListView) findViewById(R.id.CompLv);
        CmpLv.setAdapter(new CustomAdapter());
    }
    public class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return Names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.company_button,null);
            Button CompBtn = (Button) findViewById(R.id.CmpBtn);
            CompBtn.setText(Names[i]);
            final int copy = i;
            CompBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchActivity.this,ProfileCompanyActivity.class);
                    intent.putExtra("ID_COMPANIE", IDs[copy]);
                    startActivity(intent);
                }
            });
            return view;
        }
    }
}
