package com.example.iyengara18.leagueofstatistics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChampionMasteries extends Fragment implements LoaderManager.LoaderCallbacks<List<ChampMasteryInfo>>{
    private RecyclerView mRecyclerView;
    private MasteryAdapter masteryAdapter;
    private static final String API_KEY="RGAPI-47cbc5f0-7cbb-43ea-927b-f41a433ea0c6";
    private static final int CHAMP_MASTERY_ID = 1;
    private static String REQUEST_MASTERY_URL="https://na1.api.riotgames.com/lol/champion-mastery/v3/champion-masteries/by-summoner/"+QueryUtils.summonerId+"?api_key="+API_KEY;


    public ChampionMasteries() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_champion_masteries, container, false);
        mRecyclerView = rootView.findViewById(R.id.recyclered);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateUI();

        return rootView;
    }

    private void updateUI(){
        masteryAdapter = new MasteryAdapter(new ArrayList<ChampMasteryInfo>());
        mRecyclerView.setAdapter(masteryAdapter);

        getLoaderManager().initLoader(CHAMP_MASTERY_ID, null, this);
    }

    @Override
    public Loader<List<ChampMasteryInfo>> onCreateLoader(int i, Bundle bundle) {
        return new MasteryLoader(this.getContext(), REQUEST_MASTERY_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<ChampMasteryInfo>> loader, List<ChampMasteryInfo> masteries){
        if(masteries != null && !masteries.isEmpty()){
            masteryAdapter.setData(masteries);
            masteryAdapter.notifyDataSetChanged();
        }else{
            //mRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<ChampMasteryInfo>> loader){
        masteryAdapter.setData(new ArrayList<ChampMasteryInfo>());
    }

    private class MasteryHolder extends RecyclerView.ViewHolder{
        private TextView mChampName;
        private TextView mEpithet;
        private TextView mMasteryScore;
        private TextView mPoints;
        private ChampMasteryInfo mChampMastery;

        public MasteryHolder(View itemView){
            super(itemView);
            mChampName = itemView.findViewById(R.id.champNameView);
            mEpithet = itemView.findViewById(R.id.champEpithetView);
            mMasteryScore = itemView.findViewById((R.id.champMasteryView));
            mPoints = itemView.findViewById(R.id.champPointsLeftView);
        }

        public void bindMastery(ChampMasteryInfo champMastery){
            mChampMastery = champMastery;
            mChampName.setText(mChampMastery.getName());
            mEpithet.setText(mChampMastery.getEpithet());
            mMasteryScore.setText(mChampMastery.getMastery()+"");
            mPoints.setText(mChampMastery.getPoints()+" points to next level");
        }
    }

    private class MasteryAdapter extends RecyclerView.Adapter<ChampionMasteries.MasteryHolder>{
        private List<ChampMasteryInfo> mMasteries;

        public MasteryAdapter(List<ChampMasteryInfo> masteries){
            mMasteries = masteries;
        }

        public void setData(List<ChampMasteryInfo> masteries){
            mMasteries = masteries;
        }

        @Override
        public MasteryHolder onCreateViewHolder(ViewGroup parent, int viewTyper){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.item_list_masteries, parent, false);
            return new MasteryHolder(view);
        }

        @Override
        public void onBindViewHolder(MasteryHolder holder, int position){
            ChampMasteryInfo champMasteryInfo = mMasteries.get(position);
            holder.bindMastery(champMasteryInfo);
        }

        @Override
        public int getItemCount(){
            return mMasteries.size();
        }
    }

}
