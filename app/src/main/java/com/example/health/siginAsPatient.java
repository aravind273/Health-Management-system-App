package com.example.health;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class siginAsPatient extends AppCompatActivity {
    Button login;
    Button register;
    EditText email;
    EditText password;
    RegisterPatientDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_as_patient);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        db=new RegisterPatientDatabase(this);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.checkEmailAndPassword(email.getText().toString(),password.getText().toString())==true)
                {

                    Intent intent=new Intent(getApplicationContext(),PatientHome.class);
                    intent.putExtra("keyemail",email.getText().toString());
                    intent.putExtra("keypassword",password.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    ShowMessage("ERROR","INVALID EMAIL AND PASSWORD");
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),RegisterPatient.class);
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
