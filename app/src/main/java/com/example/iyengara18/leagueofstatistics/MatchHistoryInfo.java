package com.example.iyengara18.leagueofstatistics;

import java.util.ArrayList;

public class MatchHistoryInfo {

    private String mMatchResult;
    private String mChampUsed;
    private int mKills;
    private ArrayList mItems = new ArrayList();

    public MatchHistoryInfo(String matchResult, String champUsed, int kills){
        mMatchResult = matchResult;
        mChampUsed = champUsed;
        mKills = kills;
    }

    public String getMatchResult(){
        return mMatchResult;
    }

    public void setMatchResult(String courseTitle){
        mMatchResult = courseTitle;
    }

    public String getChampUsed(){
        return mChampUsed;
    }

    public void setChampUsed(String courseInstructor){
        mChampUsed = courseInstructor;
    }

    public int getKills(){
        return mKills;
    }

    public void setKills(int kills){
        mKills = kills;
    }

    public ArrayList getItems() {
        return mItems;
    }

    public void setmItems(ArrayList items){
        mItems = items;
    }
}