package com.example.iyengara18.leagueofstatistics;


public class ChampMasteryInfo {

    private String mName;
    private String mEpithet;
    private int mMastery;
    private int mPointsLeft;

    public ChampMasteryInfo(String name, String epithet, int mastery, int pointsLeft){
        mName = name;
        mEpithet = epithet;
        mMastery = mastery;
        mPointsLeft = pointsLeft;
    }

    public ChampMasteryInfo(String[] values){
        mName = values[0];
        mEpithet = values[1];
        mMastery = Integer.parseInt(values[2]);
        mPointsLeft = Integer.parseInt(values[3]);
    }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName = name;
    }

    public String getEpithet(){
        return mEpithet;
    }

    public void setEpithet(String epithet){
        mEpithet = epithet;
    }

    public int getMastery(){
        return mMastery;
    }

    public void setMastery(int mastery){
        mMastery = mastery;
    }

    public int getPoints(){
        return mPointsLeft;
    }

    public void setPoints(int pointsLeft){
        mPointsLeft = pointsLeft;
    }
}