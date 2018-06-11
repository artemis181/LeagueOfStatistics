package com.example.iyengara18.leagueofstatistics;

import android.content.Context;
import android.content.Intent;
import android.icu.text.IDNA;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Info_Tab_Layout extends AppCompatActivity {
    ViewPager vPager;
    TabLayout tabLayout;
    SampleFragmentPagerAdapter fragAdapt;
    TextView name;
    private static final String EXTRA_SUMNAME = "com.example.iyengara18.leagueofstatistics";
    String summonerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_tab_layout);
        vPager = findViewById(R.id.viewPager);
        fragAdapt = new SampleFragmentPagerAdapter(getSupportFragmentManager());
        vPager.setAdapter(fragAdapt);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vPager);
        name = findViewById(R.id.nameView);
        summonerId=getIntent().getStringExtra(EXTRA_SUMNAME);
        name.setText(getIntent().getStringExtra(EXTRA_SUMNAME));
    }

    public static Intent newIntent(Context packageContext,String summonerName){
        Intent i = new Intent(packageContext, Info_Tab_Layout.class);
        i.putExtra(EXTRA_SUMNAME, summonerName);
        return i;
    }
}
