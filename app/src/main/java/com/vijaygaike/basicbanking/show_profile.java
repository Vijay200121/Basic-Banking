package com.vijaygaike.basicbanking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class show_profile extends AppCompatActivity {

    TextView u_name, u_bal;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile);

        u_name = findViewById(R.id.user_name);
        u_bal = findViewById(R.id.user_bal);
        send = findViewById(R.id.send_money);

        Intent intent = getIntent();
        String name = intent.getStringExtra("n");
        String bal = intent.getStringExtra("b");
        String id = intent.getStringExtra("id");

        u_name.setText(name);
        u_bal.setText(bal);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(show_profile.this, create_transaction.class);
                intent1.putExtra("sender", name);
                startActivity(intent1);
            }
        });
    }
}