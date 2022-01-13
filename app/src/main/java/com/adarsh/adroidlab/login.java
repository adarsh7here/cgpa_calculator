package com.adarsh.adroidlab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sharan.adroidlab.utils.Cryputil;
import com.sharan.adroidlab.utils.sqlitehelper;


public class login extends AppCompatActivity {
    EditText username,password;
    TextView signup;
    Button login;
    Cursor cur;
    SharedPreferences sharedpreferences;
    sqlitehelper sql;
    public static String susername,spassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sql=new sqlitehelper(this, "ceg.sqlite", null, 1);
        sharedpreferences = getSharedPreferences("adarsh", Context.MODE_PRIVATE);
        hide();
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        signup=(TextView)findViewById(R.id.signup);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("")&&password.getText().toString().equals(""))
                {
                    username.setError("Please Enter The Phone No");
                    password.setError("Please Enter The Password");
                }
                else if(username.getText().toString().equals(""))
                {
                    username.setError("Please Enter The Phone No");
                }
                else if(password.getText().toString().equals(""))
                {
                    password.setError("Please Enter The Password");
                }
                else
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            susername=username.getText().toString();
                            try {
                                spassword= Cryputil.md5(password.getText().toString());
                            }
                            catch (Exception e)
                            {

                            }
                            cur=sql.getData("select id,firstname,lastname,email,phone,roll from user where phone='"+susername+"' and password='"+spassword+"'");

                            if(cur.getCount()<=0)
                            {
                                Toast.makeText(login.this, "LoginFailed...", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(cur.moveToFirst()) {
                                    SharedPreferences.Editor editor = sharedpreferences.edit();
                                    editor.putString("user_id",cur.getString(cur.getColumnIndex("Id")));
                                    editor.putString("fname",cur.getString(cur.getColumnIndex("firstname")));
                                    editor.putString("lname",cur.getString(cur.getColumnIndex("lastname")));
                                    editor.putString("email", cur.getString(cur.getColumnIndex("email")));
                                    editor.putString("phone", cur.getString(cur.getColumnIndex("phone")));
                                    editor.putString("roll", cur.getString(cur.getColumnIndex("roll")));
                                    editor.commit();
                                }
                                Toast.makeText(login.this, "LoginSucessfully...", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(login.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,signup.class);
                startActivity(i);
            }
        });
    }
    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }
}