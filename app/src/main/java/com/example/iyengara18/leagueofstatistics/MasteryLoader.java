package com.example.iyengara18.leagueofstatistics;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

public class MasteryLoader extends AsyncTaskLoader<List<ChampMasteryInfo>> {

    private String mUrl;

    public MasteryLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }
    @Override
    public List<ChampMasteryInfo> loadInBackground(){
        if(mUrl == null){
            return null;
        }
        List<ChampMasteryInfo> masteries = QueryUtils.fetchMasteryData(mUrl);
        return masteries;
    }
}
