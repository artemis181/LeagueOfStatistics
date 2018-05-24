package com.example.iyengara18.leagueofstatistics;

import android.content.Intent;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText sumName;
    Button begin;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumName = findViewById(R.id.enterNameView);
        begin = findViewById(R.id.startBut);
        begin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        name = sumName.getText().toString();
        Intent i = Info_Tab_Layout.newIntent(MainActivity.this, name);
        startActivity(i);
    }
}
