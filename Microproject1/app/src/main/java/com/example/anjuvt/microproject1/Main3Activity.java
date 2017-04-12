package com.example.anjuvt.microproject1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    TextView e1, e2, e3,e4;
    SQLiteDatabase db;
    Button call;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent in = getIntent();
        String tim = in.getStringExtra("name");

        e1 = (TextView) findViewById(R.id.e_name);
        e2 = (TextView) findViewById(R.id.e_phno);
        e3 = (TextView) findViewById(R.id.e_dob);
        e4 = (TextView) findViewById(R.id.e_address);
        db = openOrCreateDatabase("classdb2", Context.MODE_PRIVATE, null);
        if (db != null) {
            Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();
        }
        // db.execSQL("CREATE TABLE IF NOT EXISTS table1 (busno Integer primary key,time varchar,name varchar, source varchar, destination varchar,route varchar);");
        Cursor cur = db.rawQuery("select * from students1 where name='" + tim + "'", null);
        if (cur.getCount() != 0) {
            cur.moveToFirst();
            e1.setText(cur.getString((cur.getColumnIndex("name"))));
            e2.setText(cur.getString((cur.getColumnIndex("phno"))));
            e3.setText(cur.getString((cur.getColumnIndex("dob"))));
            e4.setText(cur.getString((cur.getColumnIndex("address"))));
        } else {
            Toast.makeText(this, "No dat found", Toast.LENGTH_SHORT).show();
        }
        call = (Button) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String s= e2.getText().toString();


                Intent in = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+e2));

                startActivity(in);
            }
        });
    }
}



