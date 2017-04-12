package com.example.anjuvt.microproject1;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String MY_PREFS_NAME = "aaaa";
    SQLiteDatabase dbh;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        dbh=openOrCreateDatabase("classdb2",MODE_PRIVATE,null);
      //  dbh.execSQL("DROP TABLE table1");
        dbh.execSQL("CREATE TABLE IF NOT EXISTS students1 (id Integer primary key,name varchar,phno varchar, address varchar, dob varchar);");


        Cursor c= dbh.rawQuery("select * from students1",null);
       // insert();
       // view();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent my = new Intent(MainActivity.this,Main2Activity.class);

                startActivity(my);
            }
        });
    }




    public void insert() {

        dbh.execSQL("insert into students1 values(1,'Anju','9876756443','Vayalilkarottu','30-11-1995')");
        dbh.execSQL("insert into students1 values(2,'Alphonse','9747806880','Mundakal','18-7-1995')");
        dbh.execSQL("insert into students1 values(3,'Jasna','9895752062','Challil','26-8-1995')");
        dbh.execSQL("insert into students1 values(4,'Geethu','9857645984','Madukkakuzhi','05-1-1995')");
        dbh.execSQL("insert into students1 values(5,'Aneena','9876543567','Vattothe','08-11-1995')");
        dbh.execSQL("insert into students1 values(6,'Jyothi','9876756443','Akkare','10-11-1995')");
        dbh.execSQL("insert into students1 values(7,'Anaga','9876756443','Vattakuzhi','23-11-1995')");
        dbh.execSQL("insert into students1 values(8,'Athira','9876756443','Vayalil','04-11-1995')");
        Toast.makeText(this, "inserted", Toast.LENGTH_SHORT).show();

    }

//    public void setFirstBoot() {
//        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//
//        editor.putInt("check", 1);
//        editor.commit();
//    }
//
//    public int getFirstBoot() {
//        int id=0;
//        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//        String restoredText = prefs.getString("text", null);
//        if (restoredText != null) {
//
//            id = prefs.getInt("check", 0); //0 is the default value.
public void view() {

    Cursor c = dbh.query("students1", null, null, null, null, null, null);

    if (c.getCount() == 0) {
        showMessage("Error", "No records found");
        return;
    }
    StringBuffer buffer = new StringBuffer();
    while (c.moveToNext()) {

        buffer.append("id: " + c.getString(0) + "\n");
        buffer.append("name: " + c.getString(1) + "\n");
        buffer.append("phno: " + c.getString(2) + "\n\n");
        buffer.append("address: " + c.getString(3) + "\n\n");
        buffer.append("dob: " + c.getString(4) + "\n\n");
        showMessage("students details ", buffer.toString());

    }
}

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

