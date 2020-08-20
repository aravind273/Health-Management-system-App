package com.example.health;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StaffInformation extends AppCompatActivity {
    EditText id;
    EditText name;
    EditText age;
    EditText address;
    EditText phone;
    EditText category;
    Button add;
    Button display;
    Button update;
    Button delete;
    StaffDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_information);
        db=new StaffDataBase(this);
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.mobile);
        category=findViewById(R.id.category);
        add=findViewById(R.id.adddata);
        display=findViewById(R.id.showdata);
        id=findViewById(R.id.id);
        update=findViewById(R.id.UpdateData);
        delete=findViewById(R.id.delete);
        adddata();
        getAllData();
        updateData();
        deleteData();
    }
    public void adddata()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res =db.insertdata(id.getText().toString(),name.getText().toString(),age.getText().toString(),address.getText().toString(),phone.getText().toString(),category.getText().toString());
                if(res==true)
                {
                    Toast.makeText(getApplicationContext(),"Inserted Succesfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Inserted unsuccess",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void getAllData() {
        display.setOnClickListener(new View.OnClickListener() {
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
                    stringBuffer.append("Staff Member Id :"+res.getString(0)+"\n");
                    stringBuffer.append("Staff Member Name  :"+res.getString(1)+"\n");
                    stringBuffer.append("Staff Member Age :"+res.getString(2)+"\n");
                    stringBuffer.append("Staff Member Address  :"+res.getString(3)+"\n");
                    stringBuffer.append("Staff Member number :"+res.getString(4)+"\n");
                    stringBuffer.append("Staff Member category :"+res.getString(5)+"\n\n");
                }
                ShowMessage("Data",stringBuffer.toString());
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
    public void updateData()
    {
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res=db.Update(id.getText().toString(),name.getText().toString(),age.getText().toString(),address.getText().toString(),phone.getText().toString(),category.getText().toString());
                if(res==true)
                {
                    Toast.makeText(getApplicationContext(),"Updated Succesfully",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error While Updating",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void deleteData()
    {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),StaffDelete.class);
                startActivity(intent);
            }
        });

    }
}
