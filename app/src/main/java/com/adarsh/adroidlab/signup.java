package com.adarsh.adroidlab;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.sharan.adroidlab.utils.Cryputil;
import com.sharan.adroidlab.utils.sqlitehelper;
import com.sharan.adroidlab.utils.validator;

import java.util.Random;

public class signup extends AppCompatActivity {
    EditText firstname,lastname,email,phone,password,repassword;
    Button signup;
    sqlitehelper sql;
    TextView account;
    LinearLayout mainlayout;
    Random rand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        hide();
        sql=new sqlitehelper(this, "ceg.sqlite", null, 1);
        sql.queryData("CREATE TABLE IF NOT EXISTS user (Id INTEGER PRIMARY KEY AUTOINCREMENT, firstname VARCHAR, lastname VARCHAR,email VARCHAR,phone VARCHAR ,password VARCHAR,roll VARCHAR)");
        sql.queryData("CREATE TABLE IF NOT EXISTS userinformation (Id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER,address VARCHAR,dob VARCHAR, age INTEGER,dsa VARCHAR,python VARCHAR,npdm VARCHAR,ct VARCHAR,pythonlab VARCHAR,dsalab VARCHAR,laps VARCHAR,rmipr VARCHAR,percentage DOUBLE)");
        firstname=(EditText)findViewById(R.id.first);
        lastname=(EditText)findViewById(R.id.last);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        password=(EditText)findViewById(R.id.password);
        repassword=(EditText)findViewById(R.id.repassword);
        signup=(Button)findViewById(R.id.signup);
        account=(TextView)findViewById(R.id.account);
        mainlayout=(LinearLayout)findViewById(R.id.mainlayout);
        rand = new Random();
        final validator validator=new validator();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstname.getText().toString().equals("")&&lastname.getText().toString().equals("")&&email.getText().toString().equals("")&&phone.getText().toString().equals("")&&password.getText().toString().equals("")&&repassword.getText().toString().equals(""))
                {
                    firstname.setError("Please Enter The Firstname");
                    lastname.setError("Please Enter The Lastname");
                    email.setError("Please Enter The Email");
                    phone.setError("Please Enter The MobileNo");
                    password.setError("Please Enter The Password");
                    repassword.setError("Please Enter The Re-Password");
                }
                else if(firstname.getText().toString().equals(""))
                {
                    firstname.setError("Please Enter The Firstname");

                }
                else if(lastname.getText().toString().equals(""))
                {
                    lastname.setError("Please Enter The Lastname");
                }
                else if(email.getText().toString().equals("")|| !validator.isEmailValid(email.getText().toString()))
                {
                    email.setError("Please Enter Valid Email");
                }
                else if(phone.getText().toString().equals("")||validator.isValidMobile(phone.getText().toString()))
                {
                    phone.setError("Please Enter Valid MobileNo");
                }
                else if(password.getText().toString().equals(""))
                {
                    password.setError("Please Enter The Password");
                }
                else if(!validator.isValidPassword(password.getText().toString()))
                {
                    password.setError("Password must be at least 8 characters long and contain A-Z, a-z, numeric and special characters");
                }
                else if(repassword.getText().toString().equals(""))
                {
                    repassword.setError("Please Enter The Re-Password");
                }
                else if(!password.getText().toString().equals(repassword.getText().toString()))
                {
                    repassword.setError("Please Enter The Correct Password");
                }
                else
                {
                    if(sql.phemailcheck(phone.getText().toString(),email.getText().toString())) {
                        String pass="";
                        try {
                            pass= Cryputil.md5(password.getText().toString());
                        }
                        catch (Exception e)
                        {

                        }
                        sql.insertuserdata(firstname.getText().toString(), lastname.getText().toString(), email.getText().toString(), phone.getText().toString(),pass);
                        Toast.makeText(signup.this, "Signup Successfully...", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(signup.this, login.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(signup.this, "Email Or Phone No Alraedy Exists...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(signup.this,login.class);
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