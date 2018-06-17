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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    ListView CmpLv;
    SearchView editsearch;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editsearch = (SearchView) findViewById(R.id.search);
        CmpLv = (ListView) findViewById(R.id.CompLv);
        List<Company> arraylist = new ArrayList<>();
        for(int i=0;i<20;i++)
        {
            Company cmp = new Company("comp" + i, "Id" + i);
            arraylist.add(cmp);
        }
        adapter = new CustomAdapter(this, arraylist);
        editsearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;
                adapter.filter(text);
                return false;
            }
        });
        CmpLv.setAdapter(adapter);

    }
    public class CustomAdapter extends BaseAdapter{

        Context mContext;
        private List<Company> CmpNamesList = null;
        private ArrayList<Company> arraylist = new ArrayList<>();

        public CustomAdapter(Context context, List<Company> List) {
            mContext = context;
            this.CmpNamesList = List;
            this.arraylist.addAll(List);
        }
        @Override
        public int getCount() {
            return CmpNamesList.size();
        }

        @Override
        public Object getItem(int position) {
            return CmpNamesList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null)
            {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.company_button, null);
            }
            Button CompBtn = (Button) view.findViewById(R.id.CmpBtn);
            CompBtn.setText(CmpNamesList.get(i).Name);
            final int copy = i;
            CompBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SearchActivity.this,ProfileCompanyActivity.class);
                    intent.putExtra("ID_COMPANIE", CmpNamesList.get(copy).ID);
                    startActivity(intent);
                }
            });
            return view;
        }
        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            CmpNamesList.clear();
            if (charText.length() == 0) {
                CmpNamesList.addAll(arraylist);
            } else {
                for (Company wp : arraylist) {
                    if (wp.Name.toLowerCase(Locale.getDefault()).contains(charText)) {
                        CmpNamesList.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }
    }
}
