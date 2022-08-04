package com.time.game.Model.Profile;
import com.time.game.Model.Rhythm.RhythmFactory;
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

public class UserProfile {

    private final String username;

    /*
        Holds user scores for all attempts of all levels as a key of 1-25 (all rhythms) and either an empty arraylist
        or all the attempts of that level as its value.
     */
    private final HashMap<Integer, ArrayList<Integer>> levelScores = new HashMap<>();


    public UserProfile(String username){
        this.username = username;
        this.setupBlankScoreData();
    }

    public HashMap<Integer, ArrayList<Integer>> getLevelScores(){
        return this.levelScores;
    }

    /*
        Adds @param score into the ArrayList held in levelScores.get(level).
        If no scores have been added yet (the ArrayList has not been instantiated), it instantiates the ArrayLIst object
        and then places the score in it.
     */
    public void updateUserScores(int level, int score){
        if(this.levelScores.get(level) == null){
            ArrayList<Integer> firstScore = new ArrayList<>();
            firstScore.add(score);
            this.levelScores.put(level, firstScore);
        }
        else {
            this.levelScores.get(level).add(score);
        }
    }

    public String getUsername() {
        return this.username;
    }

    /*
        Method returns -1 if no scores are present for the level passed as @param level, else returns
        the highest score recorded for that level.
     */
    public int getHighestLevelScore(int level){
        Optional<Integer> score = levelScores.get(level).stream().reduce(Integer::max);
        return score.orElse(-1);
    }

    /*
        returns all the attempts of a given level. If none have been made will return null.
     */
    public ArrayList<Integer> getLevelScoreAttempts(int level){
        return this.levelScores.get(level);
    }

    /*
        Sets all values of levelScores to null.
     */
    public void setupBlankScoreData(){
        for(int i = 1; i <= RhythmFactory.getLastPossibleRhythmNumber(); i++){
            levelScores.put(i, null);
        }
    }

    public int getNumLevelsCompleted(){
        int numLevelsCompleted = 0;
        for(int i = 1; i <= levelScores.size(); i++){
            if(levelScores.get(i) != null){numLevelsCompleted +=1;}
        }
        return numLevelsCompleted;
    }


}
