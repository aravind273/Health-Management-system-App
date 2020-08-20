package com.example.health;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    EditText age;
    EditText sex;
    EditText date;
    EditText time;
    Button  date_button;
    Button time_buton;
    Button book;
    Spinner spinner_doctor;
    String data_string="";
    String time_string="";
    String selectedDoctor="";
    private int mYear,mMonth,mDay,mHour, mMinute;
    String doctors[]={"Ramu","Raju","Naveen","Vamshi"};
    BookAppointmentDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        sex=findViewById(R.id.sex);
        date=findViewById(R.id.date_ed);
        time=findViewById(R.id.time_ed);
        date_button=findViewById(R.id.date_button);
        time_buton=findViewById(R.id.time_button);
        book=findViewById(R.id.book);
        spinner_doctor=findViewById(R.id.spinnerdoctor);
        date_button.setOnClickListener(this);
        time_buton.setOnClickListener(this);
        db=new BookAppointmentDatabase(this);
        adddata();
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,doctors);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_doctor.setAdapter(arrayAdapter);
        spinner_doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDoctor=doctors[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void adddata()
    {
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res =db.insertdata(name.getText().toString(),selectedDoctor,age.getText().toString(),sex.getText().toString(),data_string,time_string);
                if(res==true)
                {
                    Toast.makeText(getApplicationContext(),"Inserted Succesfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Inserted unsucces",Toast.LENGTH_LONG).show();
                }
            }
        });
    }





    @Override
    public void onClick(View view) {
        if(view==date_button )
        {
                try {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            data_string = data_string + i2 + "-" + (i1 + 1) + "-" + i;
                            date.setText(data_string);

                        }
                    }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
                catch (Exception e)
                {
                   BookAppointment b=new BookAppointment();
                   b.ShowMessage("ERROR",e+"");

                }

        }
        if(view==time_buton)
        {
            try {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);


                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                time_string = time_string + hourOfDay + ":" + minute;

                                time.setText(time_string);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
            catch (Exception e)
            {
                BookAppointment b=new BookAppointment();
                b.ShowMessage("ERROR",e+"");

            }

        }

        }
    public void ShowMessage(String title,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    }

