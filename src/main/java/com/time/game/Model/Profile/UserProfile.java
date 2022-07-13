package com.time.game.Model.Profile;

import java.util.ArrayList;
import java.util.HashMap;

public class UserProfile {

    private final String name;
    private int lastLevelScore;

    private HashMap<Integer,Integer> levelScores;

    public UserProfile(String name){
        this.name = name;
        this.levelScores = new HashMap<>();
    }

    public int getLastLevelScore(){
        return lastLevelScore;
    }

    public void setLastLevelScore(double score) {
        this.lastLevelScore = (int) score * 100;
    }

    public void updateUserLevelScores(double score, int level){
        levelScores.put(level,(int)score*100);
    }

    public int getLevelScore(int level){
        if(levelScores.containsKey(level)) {
            return levelScores.get(level);
        }
        return 0;
    }

}
