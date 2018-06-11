package com.example.iyengara18.leagueofstatistics;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class SummonerIdLoader extends AsyncTaskLoader<Integer> {

    private String mUrl;

    public SummonerIdLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading(){
        forceLoad();
    }
    @Override
    public Integer loadInBackground(){
        QueryUtils.fetchSummonerId(mUrl);
        return 1;
    }
}
