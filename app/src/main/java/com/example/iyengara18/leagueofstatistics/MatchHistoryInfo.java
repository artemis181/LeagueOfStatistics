package com.example.iyengara18.leagueofstatistics;

public class MatchHistoryInfo {

    private String mMatchResult;
    private String mChampUsed;
    private int mKills;
    private String[] mItems;

    public MatchHistoryInfo(String matchResult, String champUsed, String[] items, int kills){
        mMatchResult = matchResult;
        mChampUsed = champUsed;
        mKills = kills;
        mItems = items;
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

    public String[] getItems() {
        return mItems;
    }

    public void setmItems(String[] items){
        mItems = items;
    }
}