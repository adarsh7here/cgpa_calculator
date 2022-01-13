package com.adarsh.adroidlab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sharan.adroidlab.utils.sqlitehelper;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText first,last,email,phone,address,dob,age,percentage,dsa,python,ct,npdm,pythonlab,dsalab,laps,rmipr,roll;
    DatePickerDialog.OnDateSetListener date;
    Calendar myCalendar;
    SharedPreferences sharedpreferences;
    Cursor cur;
    sqlitehelper sql;
    public static int cur_u_id,cur_u_i_id;
    Button update,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sql=new sqlitehelper(this, "ceg.sqlite", null, 1);
        first=findViewById(R.id.first);
        last=findViewById(R.id.last);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        address=findViewById(R.id.address);
        dob=findViewById(R.id.dob);
        logout=findViewById(R.id.logout);
        age=findViewById(R.id.age);
        dsa=findViewById(R.id.dsa);
        python=findViewById(R.id.python);
        ct=findViewById(R.id.ct);
        npdm=findViewById(R.id.npdm);
        pythonlab=findViewById(R.id.pythonlab);
        dsalab=findViewById(R.id.dsalab);
        laps=findViewById(R.id.laps);
        rmipr=findViewById(R.id.rmipr);
        roll=findViewById(R.id.roll);
        update=findViewById(R.id.update);
        percentage=findViewById(R.id.percentage);
        myCalendar = Calendar.getInstance();
        allset();

        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dsa.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    markcal();
            }
        });
        python.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    markcal();
            }
        });
        npdm.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    markcal();
            }
        });
        ct.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    markcal();
            }
        });
        pythonlab.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    markcal();
            }
        });
        dsalab.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    markcal();
            }
        });
        laps.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    markcal();
            }
        });
        rmipr.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0)
                    markcal();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,login.class);
                startActivity(i);
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(sdf.format(myCalendar.getTime()));

        Date date = myCalendar.getTime();
        long millis = date.getTime();
        int re=calculateAge(millis);
        age.setText(re+"");

    }

    int calculateAge(long date){
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }

    public void markcal()
    {

        double re=((gradeget(dsa.getText().toString())*3)+
                (gradeget(python.getText().toString())*3)+
                (gradeget(npdm.getText().toString())*3)+
                (gradeget(ct.getText().toString())*3)+
                (gradeget(pythonlab.getText().toString())*3)+
                (gradeget(dsalab.getText().toString())*3)+
                (gradeget(laps.getText().toString())*3)+
                (gradeget(rmipr.getText().toString())*3));

        double per=re/24;
        percentage.setText(per+"");

    }

    public void allset()
    {
        sharedpreferences = getSharedPreferences("adarsh", Context.MODE_PRIVATE);
        first.setText(sharedpreferences.getString("fname", "adarsh "));
        last.setText(sharedpreferences.getString("lname", "kumar"));
        email.setText(sharedpreferences.getString("email", "adarshkumarsingh210@gmail.com"));
        phone.setText(sharedpreferences.getString("phone", "8340704304"));
        roll.setText(sharedpreferences.getString("roll", "2020179047"));
        cur=sql.getData("select * from userinformation where user_id="+sharedpreferences.getString("user_id", "1")+"");
        cur.moveToFirst();
        address.setText(cur.getString(cur.getColumnIndex("address")));
        age.setText(cur.getString(cur.getColumnIndex("age")));
        dob.setText(cur.getString(cur.getColumnIndex("dob")));
        dsa.setText(cur.getString(cur.getColumnIndex("dsa")));
        python.setText(cur.getString(cur.getColumnIndex("python")));
        npdm.setText(cur.getString(cur.getColumnIndex("npdm")));
        ct.setText(cur.getString(cur.getColumnIndex("ct")));
        pythonlab.setText(cur.getString(cur.getColumnIndex("pythonlab")));
        dsalab.setText(cur.getString(cur.getColumnIndex("dsalab")));
        laps.setText(cur.getString(cur.getColumnIndex("laps")));
        rmipr.setText(cur.getString(cur.getColumnIndex("rmipr")));
        percentage.setText(cur.getString(cur.getColumnIndex("percentage")));
        cur_u_i_id=Integer.parseInt(cur.getString(cur.getColumnIndex("Id")));
        cur_u_id=Integer.parseInt(sharedpreferences.getString("user_id", "1"));
    }


    public void update()
    {
        if(first.getText().toString().equals("")||last.getText().toString().equals("")||email.getText().toString().equals("")||phone.getText().toString().equals("")||address.getText().toString().equals("")||dob.getText().toString().equals("")||dsa.getText().toString().equals("")||python.getText().toString().equals("")||npdm.getText().toString().equals("")||ct.getText().toString().equals("")||pythonlab.getText().toString().equals("")||dsalab.getText().toString().equals("")||laps.getText().toString().equals("")||rmipr.getText().toString().equals("")||roll.getText().toString().equals(""))
        {
            Toast.makeText(this, "Please Fill The All Fileds", Toast.LENGTH_SHORT).show();
        }
        else
        {
            sql.queryData("update user set firstname='"+first.getText().toString()+"',lastname='"+last.getText().toString()+"',roll='"+roll.getText().toString()+"' where Id='"+cur_u_id+"'");
            sql.queryData("update userinformation set address='"+address.getText().toString()+"',dob='"+dob.getText().toString()+"',age='"+age.getText().toString()+"',dsa='"+dsa.getText().toString()+"',python='"+python.getText().toString()+"',npdm='"+npdm.getText().toString()+"',ct='"+ct.getText().toString()+"',pythonlab='"+pythonlab.getText().toString()+"',dsalab='"+dsalab.getText().toString()+"',laps='"+laps.getText().toString()+"',rmipr='"+rmipr.getText().toString()+"',percentage='"+percentage.getText().toString()+"' where Id='"+cur_u_i_id+"'");
            Toast.makeText(this, "Updated Successfully...", Toast.LENGTH_SHORT).show();
            sharedpreferences = getSharedPreferences("adarsh", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("fname",first.getText().toString());
            editor.putString("lname",last.getText().toString());
            editor.commit();
            allset();
        }
    }

    public int gradeget(String g)
    {
        int re=0;
        switch (g)
        {
            case "O":
                re=10;
                break;
            case "A+":
                re=9;
                break;
            case "A":
                re=8;
                break;
            case "B+":
                re=7;
                break;
            case "B":
                re=6;
                break;
            case "RA":
                re=0;
                break;
            default:
                re=0;
        }
        return re;
    }
}