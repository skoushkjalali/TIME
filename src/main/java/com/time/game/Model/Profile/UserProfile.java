package com.time.game.Model.Profile;
import java.util.HashMap;
import java.util.HashSet;

public class UserProfile {

    private static String username;
    private static int lastLevelScore;

    private static HashMap<Integer,Integer> levelScores = new HashMap<>();

    private static HashSet<Integer> levelsCompleted = new HashSet<>();

    public static void setUsername(String name){
        username = name;
    }

    public static int getLastLevelScore(){
        return lastLevelScore;
    }

    public static void setLastLevelScore(double score) {
        UserProfile.lastLevelScore = (int) score * 100;
    }

    public static void updateUserLevelScores(int level, double score){
        levelScores.put(level,(int)score*100);
    }

    public static void updateLevelsCompleted(int level){
        levelsCompleted.add(level);
    }

    public static String getUsername() {
        return username;
    }

    public static int getLevelScore(int level){
        if(levelScores.containsKey(level)) {
            return levelScores.get(level);
        }
        return 0;
    }

    public static double getAverageScore(){
        int sum = 0;
        double divisor = levelsCompleted.size();
        for(var level : levelsCompleted){
            sum += levelScores.get(level);
        }
        return sum / divisor;
    }

}
