package com187r.example.kalyan.javaproj1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class internal extends AppCompatActivity {

    EditText ename,eroll_no,emarks,sem;
    Button add,view,viewall,Show1,delete,modify,b2;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);
        ename=(EditText)findViewById(R.id.editText3);
        eroll_no=(EditText)findViewById(R.id.editText);
        sem=(EditText)findViewById(R.id.editText4);
        emarks=(EditText)findViewById(R.id.editText5);
        add=(Button)findViewById(R.id.button2);
        view=(Button)findViewById(R.id.button5);
        viewall=(Button)findViewById(R.id.button6);
        delete=(Button)findViewById(R.id.button3);
        Show1=(Button)findViewById(R.id.button7);
        modify=(Button)findViewById(R.id.button4);
        b2=(Button)findViewById(R.id.button8);
        db=openOrCreateDatabase("Student_manage", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(rollno INTEGER,name VARCHAR,sem VARCHAR,marks INTEGER);");


        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(eroll_no.getText().toString().trim().length()==0||
                        sem.getText().toString().trim().length()==0||
                        ename.getText().toString().trim().length()==0||
                        emarks.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter all values");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('"+eroll_no.getText()+"','"+ename.getText()+
                        "','"+sem.getText()+"','"+emarks.getText()+"');");
                showMessage("Success", "Record added successfully");
                clearText();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(eroll_no.getText().toString().trim().length()==0 && sem.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno and Sem");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+eroll_no.getText()+"' AND sem='"+sem.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE rollno='"+eroll_no.getText()+"'AND sem='"+sem.getText()+"'");
                    showMessage("Success", "Record Deleted");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno OR Sem");
                }
                clearText();
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(eroll_no.getText().toString().trim().length()==0 && sem.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno and Sem");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+eroll_no.getText()+"' AND sem='"+sem.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("UPDATE student SET name='"+ename.getText()+"',marks='"+emarks.getText()+
                            "' WHERE rollno='"+eroll_no.getText()+"' AND sem'"+sem.getText()+"'");
                    showMessage("Success", "Record Modified");
                }
                else
                {
                    showMessage("Error", "Invalid Rollno or Sem");
                }
                clearText();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(eroll_no.getText().toString().trim().length()==0 && sem.getText().toString().trim().length()==0)
                {
                    showMessage("Error", "Please enter Rollno or Sem");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE rollno='"+eroll_no.getText()+"' AND sem='"+sem.getText()+"'", null);
                if(c.moveToFirst())
                {
                    ename.setText(c.getString(1));
                    emarks.setText(c.getString(3));
                }
                else
                {
                    showMessage("Error", "Invalid Rollno or Sem");
                    clearText();
                }
            }
        });
        viewall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cursor c=db.rawQuery("SELECT * FROM student", null);
                if(c.getCount()==0)
                {
                    showMessage("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Rollno: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("SEM:"+c.getString(2)+"\n");
                    buffer.append("Marks: "+c.getString(3)+"\n\n");

                }
                showMessage("Student Details", buffer.toString());
            }
        });
        Show1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showMessage("SEMESTER","Going To Semester Page");
                Intent intent=new Intent(internal.this,S11.class);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        eroll_no.setText("");
        ename.setText("");
        emarks.setText("");
        sem.setText("");
        eroll_no.requestFocus();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_main, menu);
        return true;
    }

}
