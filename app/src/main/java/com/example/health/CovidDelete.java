package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CovidDelete extends AppCompatActivity {
    Covid19Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_delete);
        db=new Covid19Database(this);
        final EditText delete_edittext=findViewById(R.id.ii);
        Button button_delete=findViewById(R.id.dd);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer value=db.deleteData(delete_edittext.getText().toString());
                if(value>0)
                {
                    Toast.makeText(getApplicationContext(),"Deleted Succesfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Deleted  unSuccesfull",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}
