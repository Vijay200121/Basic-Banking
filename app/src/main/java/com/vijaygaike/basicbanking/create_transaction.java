package com.vijaygaike.basicbanking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class create_transaction extends AppCompatActivity {

    String[] listAllUsers = {"Vijay Gaike",
            "Nitika Swamy",
            "Dhiraj Mutti",
            "Rehman Barad",
            "Jasmin Sengupta",
            "Padama Pillay",
            "Drishti Sankar",
            "Gunjan Kaur",
            "Pranay Som",
            "Jayshree Merchant" };

    int[] id_s = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    TextView s, r;
    EditText a;
    Button send;
    db DBhandler;
    int id_sender=0, id_receiver=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_transaction);

        s = findViewById(R.id.sender);
        r = findViewById(R.id.receiver);
        a = findViewById(R.id.amount_input);
        send = findViewById(R.id.send);

        DBhandler = new db(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("sender");
        for (int i=0; i<10; i++){
            if(Objects.equals(name, listAllUsers[i])){
                id_sender = id_s[i];
                break;
            }
        }
        s.setText(name);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(create_transaction.this);
                mBuilder.setTitle("Select Sender");
                mBuilder.setSingleChoiceItems(listAllUsers, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        s.setText(listAllUsers[i]);
                        id_sender = id_s[i];
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(create_transaction.this);
                mBuilder.setTitle("Select Receiver");
                mBuilder.setSingleChoiceItems(listAllUsers, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        r.setText(listAllUsers[i]);
                        id_receiver = id_s[i];
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = a.getText().toString();
                if(amount.equals("")){
                    Toast.makeText(create_transaction.this, "Please enter a Amount", Toast.LENGTH_SHORT).show();
                }
                else {
                    int am_entered = Integer.parseInt(amount);
                    Cursor temp = DBhandler.checkBalance(id_sender);
                    temp.moveToFirst();
                    String t = temp.getString(0);
                    temp.close();
                    int balance = Integer.parseInt(t);
                    if(am_entered>balance){
                        Toast.makeText(create_transaction.this, "Please Enter Valid Amount", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        boolean successful = sendMoney(am_entered, balance);
                        if(successful){
                            DBhandler.add_history(s.getText().toString(), r.getText().toString(), am_entered);
                            Toast.makeText(create_transaction.this, "transaction successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(create_transaction.this, on_success.class));
                            finish();
                        }
                        if(!successful){
                            Toast.makeText(create_transaction.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(create_transaction.this, show_all_users.class));
                            finish();
                        }
                    }
                }
            }
        });

    }

    private boolean sendMoney(int am_entered, int balance) {
        int money_left = balance-am_entered;
        int money_updated = receiver_bal()+am_entered;

        boolean x = DBhandler.update_sender(id_sender, s.getText().toString(), money_left);
        boolean y = DBhandler.update_receiver(id_receiver, r.getText().toString(), money_updated);

        if(x && y){
            return true;
        }
        else{
            return false;
        }
    }

    private int receiver_bal() {
        int balance = 0;
        Cursor temp = DBhandler.checkBalance(id_receiver);
        temp.moveToFirst();
        String t = temp.getString(0);
        temp.close();
        balance = Integer.parseInt(t);
        return balance;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(create_transaction.this, MainActivity.class));
    }
}