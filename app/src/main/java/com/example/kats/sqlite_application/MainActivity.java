package com.example.kats.sqlite_application;

import android.database.Cursor;
import android.location.Address;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    EditText ed1 , ed2 , ed3 , ed4;
    Button bt1,bt2,bt3,bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        //calls the constructor of DatabseHelper class
        //within the constructor we are creating the database

        ed1 = (EditText)findViewById(R.id.ed1);
        ed2 = (EditText)findViewById(R.id.ed2);
        ed3 = (EditText)findViewById(R.id.ed3);
        ed4 = (EditText)findViewById(R.id.ed4);

        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        bt3 = (Button) findViewById(R.id.bt3);
        bt4 = (Button) findViewById(R.id.bt4);

        AddData();
        viewData();
        UpdateData();
        DeleteData();

    }

    public void AddData()
    {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isInserted = myDb.insertData(ed1.getText().toString() , ed2.getText().toString() , ed3.getText().toString());

                if(isInserted == true)
                    Toast.makeText(MainActivity.this , "Data Is Inserted" , Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(MainActivity.this , "Data Is Not Inserted" , Toast.LENGTH_LONG).show();



            }
        });
    }

    public void UpdateData()
    {
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               boolean isupdated = myDb.updateData(ed4.getText().toString() , ed1.getText().toString() , ed2.getText().toString() , ed3.getText().toString());

               if(isupdated == true)
                   Toast.makeText(MainActivity.this , "Data Is Updated" , Toast.LENGTH_LONG).show();

               else
                   Toast.makeText(MainActivity.this , "Data Is Not Updated" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewData()
    {
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor res = myDb.getAllData();

                if(res.getCount() == 0)
                {
                    //show message that no data is available
                    showMessage("ERROR" , "NO DATA FOUND");
                }

                StringBuffer buffer = new StringBuffer();

                while(res.moveToNext())
                {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("NAME : " + res.getString(1) + "\n");
                    buffer.append("SURNAME : " + res.getString(2) + "\n");
                    buffer.append("MARKS : " + res.getString(3) + "\n \n \n");
                }

                //show data

                showMessage("DATA AVAILABLE" , buffer.toString());


            }
        });
    }

    public void DeleteData()
    {
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer deletedRows = myDb.deleteData(ed4.getText().toString());

                if(deletedRows > 0)
                    Toast.makeText(MainActivity.this , "Data Is Deleted" , Toast.LENGTH_LONG).show();

                else
                    Toast.makeText(MainActivity.this , "Data Is Not Deleted" , Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showMessage(String title , String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
