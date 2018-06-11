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
    private static final String API_KEY = "RGAPI-2c7a7470-00e3-4e9f-88ac-936d052526d4";
    private static String REQUEST_SUMMONER_ID_URL = "https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/";
            private static String REQUEST_SUMMONER_ID_TWO = "?api_key=";

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
        QueryUtils.fetchSummonerId(REQUEST_SUMMONER_ID_URL+name+REQUEST_SUMMONER_ID_TWO+API_KEY);
        Intent i = Info_Tab_Layout.newIntent(MainActivity.this, name);
        startActivity(i);
    }
}
