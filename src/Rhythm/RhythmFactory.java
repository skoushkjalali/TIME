package Rhythm;

import java.util.ArrayList;

/*
    This is a mock class to allow RhythmListener and RhythmPlayer to bew built and tested.
    This class will be replaced with a scalable solution using a database.
    rhythmData objects represent the total number of equal length segments, or bits in a rhythm as the first
    index, and onset locations.
 */
public class RhythmFactory {

    // example rhythm 1
    static int[] rhythmData1 = {8,0,3,4,6,7};

    // example rhythm 2
    static int[] rhythmData2 = {16,0,4,7,8,10,12};

    // example rhythm 3
    static int[] rhythmData3 = {24,0,2,4,6,9,12,14,16,18};

    public static Rhythm getRhythm(int rhythmNumber){

        switch (rhythmNumber){
            case 1 -> {return new Rhythm(rhythmData1);}
            case 2 -> {return new Rhythm(rhythmData2);}
            default -> {return new Rhythm(rhythmData3);}
        }

    }





    







}
