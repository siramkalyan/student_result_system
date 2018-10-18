package com187r.example.kalyan.javaproj1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class S11 extends AppCompatActivity {
    EditText t1,t2,t3;
    Button add,view;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s11);
        t1=(EditText)findViewById(R.id.editText6);
        t2=(EditText)findViewById(R.id.editText7);
        t3=(EditText)findViewById(R.id.editText8);
        add=(Button)findViewById(R.id.button10);
        view=(Button)findViewById(R.id.button11);
        db=openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student1(no VARCHAR,na VARCHAR,mar INTEGER);");
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(t1.getText().toString().trim().length()==0||
                        t2.getText().toString().trim().length()==0||

                        t3.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO student1 VALUES('"+t1.getText()+"','"+t2.getText()+"','"+t3.getText()+"');");
                showMessage("Success", "Record added successfully");
                clearText();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(t1.getText().toString().trim().length()==0 && t2.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno or Sem");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student1 WHERE no='"+t1.getText()+"' AND na='"+t2.getText()+"'", null);
                if(c.moveToFirst())
                {

                    t3.setText(c.getString(2));
                }
                else
                {
                    showMessage("Error", "Invalid Rollno or Sem");
                    clearText();
                }
            }
        });


    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {

        t2.setText("");
        t3.setText("");
        t1.requestFocus();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_main, menu);
        return true;
    }
}
