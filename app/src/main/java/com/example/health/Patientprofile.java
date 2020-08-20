package com.example.health;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.widget.TextView;

public class Patientprofile extends AppCompatActivity {
    TextView name, age, email, password;
    RegisterPatientDatabase db;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientprofile);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        db = new RegisterPatientDatabase(this);
        password = findViewById(R.id.password);
        Intent i = getIntent();
        String string_email = i.getStringExtra("keyemail");
        String string_password = i.getStringExtra("keypassword");
        Cursor res = db.retrivedataofperson(string_email, string_password);
        if (res.getCount() > 0)
        {
            res.moveToNext();
        name.setText(res.getString(0).toString());
        age.setText(res.getString(1).toString());
        email.setText(res.getString(2).toString());
        password.setText(res.getString(3).toString());
    }
        else

    {
        name.setText("null");
        age.setText("null");
        email.setText("null");
        password.setText("null");
    }
    }
}
