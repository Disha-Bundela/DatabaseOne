package com.example.databaseone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText edname, edemail, edcno, edaddress;
    Button btnInsert, btnUpdate, btnDelete, btnView, btnSearch;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edname = findViewById(R.id.editName);
        edemail = findViewById(R.id.editEmail);
        edcno = findViewById(R.id.editcno);
        edaddress = findViewById(R.id.editaddress);

        btnInsert = findViewById(R.id.btn_insert);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnView = findViewById(R.id.btn_view);
        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

//        btnSearch = findViewById(R.id.btn_search);
        DB = new DBHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = edname.getText().toString();
                String emailTXT = edemail.getText().toString();
                String cnoTXT = edcno.getText().toString();
                String addressTXT = edaddress.getText().toString();
                String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                if( Pattern.compile(EMAIL_STRING).matcher(emailTXT).matches()){
                    Boolean checkinsertdata = DB.insertUser(nameTXT, emailTXT, cnoTXT,addressTXT);
                    if (checkinsertdata == true)
                        Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(MainActivity.this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = edname.getText().toString();
                String emailTXT = edemail.getText().toString();
                String cnoTXT = edcno.getText().toString();
                String addressTXT = edaddress.getText().toString();

                Boolean checkupdatedata = DB.updateUser(nameTXT, emailTXT,cnoTXT,addressTXT);
                if (checkupdatedata == true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = edname.getText().toString();
                Boolean checkudeletedata = DB.deleteUser(nameTXT);
                if (checkudeletedata == true)
                    Toast.makeText(MainActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Cursor res = DB.getdata();
//                if (res.getCount() == 0) {
//                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//                    return;
//                }
////                Intent i = new Intent(MainActivity.this, ViewActivity.class);
////                startActivity(i);
//                StringBuffer buffer = new StringBuffer();
//                while (res.moveToNext()) {
//                    buffer.append("Name :" + res.getString(0) + "\n");
//                    buffer.append("Email :" + res.getString(1) + "\n");
//                    buffer.append("Contact No :" + res.getString(2) + "\n");
//                    buffer.append("Address :" + res.getString(3) + "\n\n");
//                }
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("Customer Details");
//                builder.setMessage(buffer.toString());
//                builder.show();
                Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
                startActivity(intent);
            }
        });
    }
}
