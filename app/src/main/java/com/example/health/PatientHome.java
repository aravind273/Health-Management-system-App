package com.example.health;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PatientHome extends AppCompatActivity {
    Button profile;
    Button denguetest;
    Button bookAppointement;
    Button viewAppointment;
    Button deleteAppointment;
    BookAppointmentDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        profile=findViewById(R.id.profile);
        denguetest=findViewById(R.id.denguecheck);
        bookAppointement=findViewById(R.id.book);
        viewAppointment=findViewById(R.id.viewAppointement);
        deleteAppointment=findViewById(R.id.deleteAppointment);
        db=new BookAppointmentDatabase(this);
        getAllData();
        deleteData();

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),Patientprofile.class);
                String email=getIntent().getStringExtra("keyemail");
                i.putExtra("keyemail",email);
                String password=getIntent().getStringExtra("keypassword");
                i.putExtra("keypassword",password);
                startActivity(i);
            }
        });
        bookAppointement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),BookAppointment.class);
                startActivity(intent);

            }
        });
        denguetest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),DengueTest.class);
                startActivity(intent);
            }
        });
        deleteAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),BookAppointementDelete.class);
                startActivity(intent);
            }
        });

    }

    public void getAllData() {
        viewAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res= db.getAllData();
                if(res.getCount()==0)
                {
                    ShowMessage("Error","No Data Found");

                }
                StringBuffer stringBuffer=new StringBuffer();
                while(res.moveToNext())
                {
                    stringBuffer.append("Patient Id :"+res.getString(0)+"\n");
                    stringBuffer.append("patient Name  :"+res.getString(1)+"\n");
                    stringBuffer.append("Doctor selected :"+res.getString(2)+"\n");
                    stringBuffer.append("patient Age  :"+res.getString(3)+"\n");
                    stringBuffer.append("patient sex :"+res.getString(4)+"\n");
                    stringBuffer.append("Appointment date :"+res.getString(5)+"\n");
                    stringBuffer.append("Appointment time :"+res.getString(6)+"\n\n");
                }
                ShowMessage("APPOINTMENT DETAILS",stringBuffer.toString());
            }

        });
    }

    public void deleteData()
    {
        deleteAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),CovidDelete.class);
                startActivity(intent);


            }
        });

    }



    public void ShowMessage(String title,String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
