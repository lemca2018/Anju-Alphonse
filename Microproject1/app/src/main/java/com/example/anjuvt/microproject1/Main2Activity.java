package com.example.anjuvt.microproject1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    SQLiteDatabase db;
    ListView lv;
    String nm;
    Context mcontext;
    ArrayAdapter<String> ada;
    ArrayList<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mcontext = this;
        lv = (ListView) findViewById(R.id.listView);
        db = openOrCreateDatabase("classdb2", Context.MODE_PRIVATE, null);
        if (db != null) {
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        }
        //db.execSQL("CREATE TABLE IF NOT EXISTS students (busno Integer primary key,time varchar,name varchar, source varchar, destination varchar,route varchar);");
        Cursor cur = db.rawQuery("select * from students1", null);
        if (cur.getCount() != 0) {
            while(cur.moveToNext()){
                nm = cur.getString((cur.getColumnIndex("name")));
                Toast.makeText(this, nm, Toast.LENGTH_SHORT).show();
                list.add(nm);
            }
            ada = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
            lv.setAdapter(ada);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent in = new Intent(Main2Activity.this, Main3Activity.class);
                    in.putExtra("name", (String) parent.getItemAtPosition(position));
                    startActivity(in);
                }
            });

        }
    }
}