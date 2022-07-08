package Rhythm;

import java.util.ArrayList;

/*
    This is a mock class to allow RhythmListener and RhythmPlayer to bew built and tested.
    This class will be replaced with a scalable solution using a database.
    rhythmData objects represent the total number of equal length segments, or bits in a rhythm as the first
    index, and onset locations.
 */
public class RhythmFactory {

    // just 4 beats
    static int[] rhythmData0 = {4,0,1,2,3};

    // example rhythm 1
    static int[] rhythmData1 = {8,0,3,4,6,7};

    // example rhythm 2
    static int[] rhythmData2 = {16,0,4,7,8,10,12};

    // example rhythm 3
    static int[] rhythmData3 = {24,0,2,4,6,9,12,14,16,18};

    // Complex Rhythm Example 1
    static int[] rhythmData4 = {96,0,24,30,36,42,48,56,64,72,84,87,90};


    public static Rhythm getRhythm(int rhythmNumber){

        switch (rhythmNumber){
            case 0 -> {return new Rhythm(rhythmData0);}
            case 1 -> {return new Rhythm(rhythmData1);}
            case 2 -> {return new Rhythm(rhythmData2);}
            case 3 -> {return new Rhythm(rhythmData3);}
            default -> {return new Rhythm(rhythmData4);}
        }
    }
}
