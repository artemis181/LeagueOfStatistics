package com.example.iyengara18.leagueofstatistics;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class ChampionMasteries extends Fragment {


    public ChampionMasteries() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_champion_masteries, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateUI();

        return rootView;
    }

    private void updateUI(){
        List<MatchHistoryInfo> matches = new ArrayList<>();
        ArrayList itemsInNumber = new ArrayList();
        itemsInNumber.add(3040);
        itemsInNumber.add(3030);
        itemsInNumber.add(3020);
        itemsInNumber.add(3146);
        itemsInNumber.add(3157);
        itemsInNumber.add(null);
        itemsInNumber.add(null);
        String[] itemsName = new String[6];
        itemsName[0]=("Zhonya's Hourglass");
        itemsName[1]=("Luden's Echo");
        itemsName[2]=("Sorcerer Boots");
        itemsName[3]=("Rod of Ages");
        itemsName[4]=("None");
        itemsName[5]=("None");
        matches.add(new MatchHistoryInfo("Victory", "Fiddlesticks", itemsName, 12));
        matches.add(new MatchHistoryInfo("Defeat", "Malphite",  itemsName, 3));
        matches.add(new MatchHistoryInfo("Defeat", "Darius", itemsName, 9));
        matchAdapter = new MatchHistory.MatchAdapter(matches);
        mRecyclerView.setAdapter(matchAdapter);
    }

    private class MatchHolder extends RecyclerView.ViewHolder{
        private TextView mMatchResultTextView;
        private TextView mChampionUsedTextView;
        private TextView mKillsTextView;
        private TextView itemOne;
        private TextView itemTwo;
        private TextView itemThree;
        private TextView itemFour;
        private TextView itemFive;
        private TextView itemSix;
        private MatchHistoryInfo mMatchHistory;

        public MatchHolder(View itemView){
            super(itemView);
            mMatchResultTextView = itemView.findViewById(R.id.matchResultView);
            mChampionUsedTextView = itemView.findViewById(R.id.championView);
            itemOne = itemView.findViewById((R.id.itemOneView));
            itemTwo = itemView.findViewById((R.id.itemTwoView));
            itemThree = itemView.findViewById((R.id.itemThreeView));
            itemFour = itemView.findViewById((R.id.itemFourView));
            itemFive = itemView.findViewById((R.id.itemFiveView));
            itemSix = itemView.findViewById((R.id.itemSixView));
            mKillsTextView = itemView.findViewById(R.id.killsView);
        }

        public void bindMatch(MatchHistoryInfo matchHistory){
            mMatchHistory = matchHistory;
            String[] itemsName = mMatchHistory.getItems();
            mMatchResultTextView.setText(mMatchHistory.getMatchResult());
            mChampionUsedTextView.setText(mMatchHistory.getChampUsed());
            mKillsTextView.setText(mMatchHistory.getKills()+"");
            itemOne.setText(itemsName[0]);
            itemTwo.setText(itemsName[1]);
            itemThree.setText(itemsName[2]);
            itemFour.setText(itemsName[3]);
            itemFive.setText(itemsName[4]);
            itemSix.setText(itemsName[5]);
        }
    }

    private class MatchAdapter extends RecyclerView.Adapter<MatchHistory.MatchHolder>{
        private List<MatchHistoryInfo> mMatches;

        public MatchAdapter(List<MatchHistoryInfo> matches){
            mMatches = matches;
        }

        @Override
        public MatchHistory.MatchHolder onCreateViewHolder(ViewGroup parent, int viewTyper){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.list_item_matches, parent, false);
            return new MatchHistory.MatchHolder(view);
        }

        @Override
        public void onBindViewHolder(MatchHistory.MatchHolder holder, int position){
            MatchHistoryInfo matchHistoryInfo = mMatches.get(position);
            holder.bindMatch(matchHistoryInfo);
        }

        @Override
        public int getItemCount(){
            return mMatches.size();
        }
    }

}
