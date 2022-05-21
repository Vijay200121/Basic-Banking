package com.vijaygaike.basicbanking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class show_all_users extends AppCompatActivity {

    CardView u1, u2, u3, u4, u5, u6, u7, u8, u9, u10;
    TextView n1, n2, n3, n4, n5, n6, n7, n8, n9, n10;
    TextView b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    String id;
    db DBHandler;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_users);

        u1 = findViewById(R.id.user1);
        u2 = findViewById(R.id.user2);
        u3 = findViewById(R.id.user3);
        u4 = findViewById(R.id.user4);
        u5 = findViewById(R.id.user5);
        u6 = findViewById(R.id.user6);
        u7 = findViewById(R.id.user7);
        u8 = findViewById(R.id.user8);
        u9 = findViewById(R.id.user9);
        u10 = findViewById(R.id.user10);

        n1 = findViewById(R.id.name1);
        n2 = findViewById(R.id.name2);
        n3 = findViewById(R.id.name3);
        n4 = findViewById(R.id.name4);
        n5 = findViewById(R.id.name5);
        n6 = findViewById(R.id.name6);
        n7 = findViewById(R.id.name7);
        n8 = findViewById(R.id.name8);
        n9 = findViewById(R.id.name9);
        n10 = findViewById(R.id.name10);

        b1 = findViewById(R.id.bal1);
        b2 = findViewById(R.id.bal2);
        b3 = findViewById(R.id.bal3);
        b4 = findViewById(R.id.bal4);
        b5 = findViewById(R.id.bal5);
        b6 = findViewById(R.id.bal6);
        b7 = findViewById(R.id.bal7);
        b8 = findViewById(R.id.bal8);
        b9 = findViewById(R.id.bal9);
        b10 = findViewById(R.id.bal10);

        DBHandler = new db(this);

        for (int i=0; i<10; i++){
            int id = id_s[i];

            update_balance(id, i+1);
        }

        u1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="1";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n1.getText().toString());
                intent.putExtra("b", b1.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="2";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n2.getText().toString());
                intent.putExtra("b", b2.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="3";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n3.getText().toString());
                intent.putExtra("b", b3.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="4";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n4.getText().toString());
                intent.putExtra("b", b4.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="5";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n5.getText().toString());
                intent.putExtra("b", b5.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="6";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n6.getText().toString());
                intent.putExtra("b", b6.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="7";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n7.getText().toString());
                intent.putExtra("b", b7.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="8";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n8.getText().toString());
                intent.putExtra("b", b8.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="9";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n9.getText().toString());
                intent.putExtra("b", b9.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });

        u10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id="10";
                Intent intent = new Intent(show_all_users.this, show_profile.class);
                intent.putExtra("n", n10.getText().toString());
                intent.putExtra("b", b10.getText().toString());
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void update_balance(int id, int j) {
        int balance = 0;
        Cursor temp = DBHandler.checkBalance(id);
        temp.moveToFirst();
        String t = temp.getString(0);
        temp.close();

        if (j==1) b1.setText("Rs. " + t);
        else if(j==2) b2.setText("Rs. " + t);
        else if(j==3) b3.setText("Rs. " + t);
        else if(j==4) b4.setText("Rs. " + t);
        else if(j==5) b5.setText("Rs. " + t);
        else if(j==6) b6.setText("Rs. " + t);
        else if(j==7) b7.setText("Rs. " + t);
        else if(j==8) b8.setText("Rs. " + t);
        else if(j==9) b9.setText("Rs. " + t);
        else if(j==10) b10.setText("Rs. " + t);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(show_all_users.this, MainActivity.class));
    }
}