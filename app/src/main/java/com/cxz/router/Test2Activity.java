package com.cxz.router;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        TextView textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String key1 = intent.getStringExtra("key1");
        int key2 = intent.getIntExtra("key2", 0);

        textView.setText("key1："+key1 + "，   key2：" + key2);

    }
}
