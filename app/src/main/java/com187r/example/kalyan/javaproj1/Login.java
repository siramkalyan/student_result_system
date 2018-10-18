package com187r.example.kalyan.javaproj1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    EditText t1,t2;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        t1=(EditText)findViewById(R.id.username);
        t2=(EditText)findViewById(R.id.editText2);

        b1=(Button) findViewById(R.id.button);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if((t1.getText().toString()).equals("a")&&(t2.getText().toString()).equals("a"))
                {

                    showMessage("Message", "Login SuccessFul");
                    Intent intent=new Intent(Login.this,internal.class);
                    startActivity(intent);

                }
                else
                    showMessage("Error", "Please Login Again");
                clearText();


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
        t1.setText("");
        t2.setText("");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_main, menu);
        return true;
    }

}
