package com.vijaygaike.basicbanking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    Button show_history, show_users, create_trans;
    db dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show_users = findViewById(R.id.show_ll_users);
        create_trans = findViewById(R.id.create_transaction);
        show_history = findViewById(R.id.show_history);

        dbHandler = new db(MainActivity.this);

        show_users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, show_all_users.class));
            }
        });


        create_trans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, create_transaction.class));
            }
        });

        show_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor t = dbHandler.show_history();
                if(t.getCount()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setMessage("No Transaction History Present!");
                    builder.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while (t.moveToNext()) {
                        buffer.append("Sender: " + t.getString(0) + "\n");
                        buffer.append("Receiver: " + t.getString(1) + "\n");
                        buffer.append("Amount: Rs. " + t.getString(2) + "\n\n\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Transaction History");
                    builder.setMessage(String.valueOf(buffer));
                    builder.setNeutralButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.show();
                }

            }
        });
    }
}