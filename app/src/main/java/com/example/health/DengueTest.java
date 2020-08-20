package com.example.health;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DengueTest extends AppCompatActivity {
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dengue_test);
        editText=findViewById(R.id.platelet);
        button=findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Long count = Long.parseLong(editText.getText().toString());
                    if(count>=200000)
                    {
                        ShowMessage("NORMAL","NO NEED TO CONSULT DOCTOR");

                    }
                    else if(60000<=count && count<200000)
                    {
                        ShowMessage("DANGER","NEED TO CONSULT DOCTOR");
                    }
                    else
                    {
                        ShowMessage("HIGHLY SUSPECTED","YOU ARE IN EMERGENCY CONDITION");
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),e+" error ocurred",Toast.LENGTH_LONG).show();
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
