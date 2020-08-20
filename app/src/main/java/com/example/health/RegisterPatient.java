package com.example.health;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPatient extends AppCompatActivity {
    Button register;
    EditText name;
    EditText age,email,password,confirmpassword;
    RegisterPatientDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);
        register=findViewById(R.id.register);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        email=findViewById(R.id.email);
        db=new RegisterPatientDatabase(this);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.confirmpassword);
        register();

    }
    public void register()
    {
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!password.getText().toString().equals("") ) {
                    if (password.getText().toString().equals(confirmpassword.getText().toString())) {
                        if (db.checkEmail(email.getText().toString())) {
                            boolean res = db.insertdata(name.getText().toString(), age.getText().toString(), email.getText().toString(), password.getText().toString());
                            if (res == true) {
                                Intent intent = new Intent(getApplicationContext(), siginAsPatient.class);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registered unsucessfull", Toast.LENGTH_LONG).show();

                            }

                        } else {
                            ShowMessage("WARNING", "EMAIL ALREADY REGISTERED");

                        }

                    }
                    else
                    {
                        ShowMessage("ERROR","password and confirm password doesnot matches");
                    }
                }
                else
                {
                    ShowMessage("ERROR","Fill all the details");
                }

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
