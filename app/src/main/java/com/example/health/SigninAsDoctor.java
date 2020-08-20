package com.example.health;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SigninAsDoctor extends AppCompatActivity {
    EditText admin_secret_code;
    Button admin_login;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_as_doctor);
        admin_secret_code=findViewById(R.id.admin_secret_code);
        admin_login=findViewById(R.id.admin_login);
        admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!admin_secret_code.getText().toString().equals(""))
                {
                    Intent intent=new Intent(getApplicationContext(),Home.class);
                    startActivity(intent);

                }
                else
                {
                    ShowMessage("ERROR","please enter the secret code");
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
