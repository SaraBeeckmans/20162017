package com.example.sara.a20162017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView SimpleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



         SimpleList = (ListView) findViewById(R.id.List);
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("2 player reactor");
        arrayList.add("4 player reactor");
        arrayList.add("Click the button when you see the word");
        arrayList.add("Tap the button twice quickly");
        arrayList.add("Tap the button as often as possible within 5 seconds");

        ArrayAdapter adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, arrayList);
        SimpleList.setAdapter(adapter);


        SimpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Object o = SimpleList.getItemAtPosition(position);
                String str=(String)o;//As you are using Default String Adapter
                Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();

                if (position == 0){
                    Intent naarProgress = new Intent(ListActivity.this, ProgressActivity.class);
                    startActivity(naarProgress);
                }
            }
        });
    }
}
