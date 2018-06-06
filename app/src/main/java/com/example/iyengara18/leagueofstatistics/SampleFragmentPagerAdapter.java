package com.example.iyengara18.leagueofstatistics;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    public SampleFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        if(position == 0){
            return new ChampionMasteries();
        }else if(position == 1){
            return new MatchHistory();
        }else{
            return new Profile();
        }
    }

    @Override
    public int getCount(){
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position == 0){
            return "Champion Masteries";
        }else if(position == 1){
            return "Match History";
        }else{
            return "Profile";
        }
    }
}