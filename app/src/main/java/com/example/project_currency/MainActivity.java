package com.example.project_currency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    ListView lv;
    SearchView sv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.ll_1);
        sv=findViewById(R.id.searchView);
        String[] Crypt_names = getResources().getStringArray(R.array.list_items);
        String[] Crypt_amount = getResources().getStringArray(R.array.list_amount);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.textView3,Crypt_names);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = Crypt_names[position];
                Intent intent = new Intent(MainActivity.this, Crypt_name.class);
                intent.putExtra("selectedItem", selectedItem);
                startActivity(intent);
            }
        });

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                boolean isPresent = false;
                for (String item : Arrays.asList(Crypt_names)) {
                    if (item.equalsIgnoreCase(query)) {
                        isPresent = true;
                        break;
                    }
                }
                if (isPresent) {
                    Intent intent = new Intent(MainActivity.this, Crypt_name.class);
                    intent.putExtra("query", query);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Search element not found", Toast.LENGTH_SHORT).show();
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

    }
}