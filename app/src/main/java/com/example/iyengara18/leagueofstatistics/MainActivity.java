package com.example.iyengara18.leagueofstatistics;

import android.content.Intent;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Integer>{
    EditText sumName;
    Button begin;
    String name;
    private static final int SUMMONER_ID=1;
    private static final String API_KEY = "RGAPI-47cbc5f0-7cbb-43ea-927b-f41a433ea0c6";
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
        getSupportLoaderManager().initLoader(SUMMONER_ID, null,this);
        Intent i = Info_Tab_Layout.newIntent(MainActivity.this, name);
        startActivity(i);
    }

    @Override
    public Loader<Integer> onCreateLoader(int i, Bundle bundle) {
        return new SummonerIdLoader(this, REQUEST_SUMMONER_ID_URL+name+REQUEST_SUMMONER_ID_TWO+API_KEY);
    }

    @Override
    public void onLoadFinished(Loader<Integer> loader, Integer x){
    }

    @Override
    public void onLoaderReset(Loader<Integer> loader){
    }
}
