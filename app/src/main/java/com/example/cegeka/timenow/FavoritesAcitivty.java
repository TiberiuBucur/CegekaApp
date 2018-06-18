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
import android.widget.ListView;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FavoritesAcitivty extends AppCompatActivity {

    ListView CmpLv;
    SearchView editsearch;
    FavoritesAcitivty.CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editsearch = (SearchView) findViewById(R.id.search);
        CmpLv = (ListView) findViewById(R.id.CompLv);
        final List<Company> arraylist = new ArrayList<>();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for( DataSnapshot kido : dataSnapshot.getChildren())
                {
                    if(kido.child("type").getValue(boolean.class)&&kido.child("favorite").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).getValue(boolean.class)!=null)
                    {
                        Company c=new Company(kido.child("name").getValue(String.class),kido.getKey());
                        arraylist.add(c);

                    }
                }
                adapter = new FavoritesAcitivty.CustomAdapter(FavoritesAcitivty.this, arraylist);
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
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    public class CustomAdapter extends BaseAdapter {

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
                    Intent intent = new Intent(FavoritesAcitivty.this,ProfileCompanyActivity.class);
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
