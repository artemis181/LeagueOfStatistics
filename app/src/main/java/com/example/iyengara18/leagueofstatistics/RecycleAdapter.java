package com.example.iyengara18.leagueofstatistics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MatchAdapter matchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_match_history);

        mRecyclerView = findViewById(R.id.recycle);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
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
        public TextView mMatchResultTextView;

        public MatchHolder(View itemView){
            super(itemView);
            mMatchResultTextView = (TextView) itemView;
        }
    }

    private class MatchAdapter extends RecyclerView.Adapter<MatchHolder>{
        private List<MatchHistoryInfo> mMatches;

        public MatchAdapter(List<MatchHistoryInfo> matches){
            mMatches = matches;
        }

        @Override
        public MatchHolder onCreateViewHolder(ViewGroup parent, int viewTyper){
            LayoutInflater layoutInflater = LayoutInflater.from(RecycleAdapter.this);
            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MatchHolder(view);
        }

        @Override
        public void onBindViewHolder(MatchHolder holder,  int position){
            MatchHistoryInfo matchHistoryInfo = mMatches.get(position);
            holder.mMatchResultTextView.setText(matchHistoryInfo.getMatchResult());
        }

        @Override
        public int getItemCount(){
            return mMatches.size();
        }
    }
}
