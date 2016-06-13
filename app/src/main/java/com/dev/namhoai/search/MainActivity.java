package com.dev.namhoai.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.autoCompleteTextView) AutoCompleteTextView autoCompleteTextView;
    @BindView(R.id.listView) ListView listView;

    List<String> myList;
    MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initData();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myList);

        adapter = new MyArrayAdapter(this, R.layout.item_list_view, myList);
        listView.setAdapter(adapter);

        autoCompleteTextView.setAdapter(adapter);
    }

    private void initData() {
        myList = new ArrayList<>();
        myList.add("Việt Nam");
        myList.add("Pháp");
        myList.add("Lào");
        myList.add("Trung Quốc");
        myList.add("Mỹ");
        myList.add("Anh");
        myList.add("Ý");
        myList.add("Campuchia");
        myList.add("Đức");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.mnSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                Log.d(TAG, "onQueryTextChange: " + newText);
                return true;
            }
        });

        return true;
    }

}
