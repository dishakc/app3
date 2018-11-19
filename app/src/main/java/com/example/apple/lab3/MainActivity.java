package com.example.apple.lab3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;


public class MainActivity extends AppCompatActivity {

    DatabaseHealper mDatabaseHealper;
    private Button btnAdd, btnViewData;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText= (EditText) findViewById(R.id.editText);
        btnAdd= (Button) findViewById(R.id.addbut);
        btnViewData= (Button) findViewById(R.id.savebut);
        mDatabaseHealper=new DatabaseHealper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry=editText.getText().toString();
                if(editText.length() !=0){
                    AddData(newEntry);
                    editText.setText("");
                }else{
                    toastMessage("Enter Data");
                }
            }
        });


        btnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ListDataActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AddData(String newEntry){
        boolean insertData=mDatabaseHealper.addData(newEntry);
        if(insertData){
            toastMessage("Data Succesfully Inserted");
        }else{     toastMessage("Something Went Wrong"); }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
