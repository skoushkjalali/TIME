package com.time.game.Model.Rhythm;

import com.time.game.Model.Rhythm.Rhythm;

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

    static int[][] rhythmData =
            {       // crotchets and quavers
                    // each beat = 2 segments
                    {8,0,2,3,4,6},
                    {8,0,1,2,4,5,6},
                    {8,0,1,3,4,6},
                    {8,0,3,4,5,7},
                    {8,2,3,5,7},
                    {8,1,3,5,6},

                    // add semiquavers
                    // each beat = 4 segments
                    {16,0,4,7,8,12,14},
                    {16,0,2,4,5,6,7,8,11,12},
                    {16,0,2,3,4,6,8,9,10,11,12,15},
                    {16,2,3,4,7,10,12,13},
                    {16,1,4,6,7,8,9,11,14},
                    {16,1,3,6,7,8,11,13},

                    // add triplets
                    {48,0,6,12,16,20,24,33,36},
                    {48,0,3,9,12,18,21,24,28,32,42},
                    {48,0,9,12,18,21,24,28,36,40,44},
                    {48,5,12,15,18,21,24,28,32,40},
                    {48,0,12,18,24,28,32,36,39,42,45},
                    {48,0,6,12,14,16,18,24,28,36,39},

                    // add quintuplets
                    {240,0,30,60,80,100,120,135,150,165,180,192,204,216,228},
                    {240,0,30,45,60,84,108,132,156,180,200,220},
                    {240,0,10,20,30,60,80,100,165,192,204,216,228},
                    {5,0,1,2,3,4},
                    {240,0,12,36,48,72,84,108,120,150,165,200,220},
                    {240,0,30,90,100,110,120,140,150,160,180,210,216,222,228,234},
                    {240,0,15,30,40,50,80,100,110,120,144,168,180,192,216,228}
            };

    public static Rhythm getRhythm(int n){
        if(n<0 || n > rhythmData.length){
            n = 1;
        }
        return new Rhythm(rhythmData[n-1]);
    }




}

