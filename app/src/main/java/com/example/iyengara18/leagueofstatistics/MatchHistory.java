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
public class MatchHistory extends Fragment {
    private RecyclerView mRecyclerView;
    private MatchAdapter matchAdapter;

    public MatchHistory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_match_history, container, false);
        mRecyclerView = rootView.findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        updateUI();

        return rootView;
    }


    private void updateUI(){
        List<MatchHistoryInfo> matches = new ArrayList<>();
        matches.add(new MatchHistoryInfo("Victory", "Fiddlesticks", 9));
        matches.add(new MatchHistoryInfo("Defeat", "Malphite", 3));
        matches.add(new MatchHistoryInfo("Victory", "Darius", 12));
        matchAdapter = new MatchAdapter(matches);
        mRecyclerView.setAdapter(matchAdapter);
    }

    private class MatchHolder extends RecyclerView.ViewHolder{
        private TextView mMatchResultTextView;
        private TextView mChampionUsedTextView;
        private TextView mKillsTextView;
        private MatchHistoryInfo mMatchHistory;

        public MatchHolder(View itemView){
            super(itemView);
            mMatchResultTextView = itemView.findViewById(R.id.matchResultView);
            mChampionUsedTextView = itemView.findViewById(R.id.championView);
            mKillsTextView = itemView.findViewById(R.id.killsView);
        }

        public void bindMatch(MatchHistoryInfo matchHistory){
            mMatchHistory = matchHistory;
            mMatchResultTextView.setText(mMatchHistory.getMatchResult());
            mChampionUsedTextView.setText(mMatchHistory.getChampUsed());
            mKillsTextView.setText(mMatchHistory.getKills()+"");
        }
    }

    private class MatchAdapter extends RecyclerView.Adapter<MatchHolder>{
        private List<MatchHistoryInfo> mMatches;

        public MatchAdapter(List<MatchHistoryInfo> matches){
            mMatches = matches;
        }

        @Override
        public MatchHolder onCreateViewHolder(ViewGroup parent, int viewTyper){
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            View view = layoutInflater.inflate(R.layout.list_item_matches, parent, false);
            return new MatchHolder(view);
        }

        @Override
        public void onBindViewHolder(MatchHolder holder, int position){
            MatchHistoryInfo matchHistoryInfo = mMatches.get(position);
            holder.bindMatch(matchHistoryInfo);
        }

        @Override
        public int getItemCount(){
            return mMatches.size();
        }
    }

}
