//package com.core.logic.Rhythm;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertArrayEquals;
//
//class RhythmTest {
//
//    // r1, r2, r3 are absolute representations of rhythms. The first index is the number of equal length segments
//    // in the rhythm; the remaining indexes are the onset locations within the total segments
//    static int[] rhythmData1 = {8,0,3,4,6,7};
//    static int[] rhythmData2 = {16,0,4,7,8,10,12};
//    static int[] rhythmData3 = {24,0,2,4,6,9,12,14,16,18};
//
//    static int[] rhythmData4 = {96,0,24,30,36,42,48,56,64,72,84,87,90};
//
//
//    /*
//        testRhythmOnsets1/2/3 test for correct input of onsets into member variable onsets, which excludes the first index
//        This is accessed through getter method Rhythm.getRelativeRhythm()
//     */
//    @Test
//    public void testRhythmOnsets1(){
//        Rhythm r = new Rhythm(rhythmData1);
//        assertArrayEquals(new int[] {0,3,4,6,7}, r.getRelativeRhythm());
//
//    }
//
//    @Test
//    public void testRhythmOnsets2(){
//        Rhythm r = new Rhythm(rhythmData2);
//        assertArrayEquals(new int[] {0,4,7,8,10,12}, r.getRelativeRhythm());
//    }
//
//    @Test
//    public void testRhythmOnsets3(){
//        Rhythm r = new Rhythm(rhythmData3);
//        assertArrayEquals(new int[] {0,2,4,6,9,12,14,16,18}, r.getRelativeRhythm());
//    }
//
//    /*
//        testRhythmSegments1/2/3 test for correct input of the number of segments into member variable
//        segments. This is accessed through getter method Rhythm.getSegments()
//     */
//    @Test
//    public void testRhythmSegments1(){
//        Rhythm r = new Rhythm(rhythmData1);
//        assertEquals(8, r.getSegments());
//    }
//
//    @Test
//    public void testRhythmSegments2(){
//        Rhythm r = new Rhythm(rhythmData2);
//        assertEquals(16, r.getSegments());
//    }
//
//    @Test
//    public void testRhythmSegments3(){
//        Rhythm r = new Rhythm(rhythmData3);
//        assertEquals(24, r.getSegments());
//    }
//
//    /*
//        testGetAbsoluteRhythm1/2/3/4 test for the correct conversion of the relative representation of the rhythm in the
//        held in int[] onsets, to the scaled, absolute version, that uses @param bpm to calculate the onset locations in
//        milliseconds from an epoch of 0. These 4 tests hold bpm at 100.
//     */
//
//    @Test
//    public void testGetAbsoluteRhythm1(){
//        Rhythm r = new Rhythm(rhythmData1);
//        assertArrayEquals(new double[] {0.0, 900.0, 1200.0, 1800.0, 2100.0}, r.getAbsoluteRhythm(100));
//    }
//
//    @Test
//    public void testGetAbsoluteRhythm2(){
//        Rhythm r = new Rhythm(rhythmData2);
//        assertArrayEquals(new double[] {0.0, 600.0, 1050.0, 1200.0, 1500.0, 1800.0}, r.getAbsoluteRhythm(100));
//    }
//
//    //     static int[] rhythmData3 = {24,0,2,4,6,9,12,14,16,18};
//    @Test
//    public void testGetAbsoluteRhythm3(){
//        Rhythm r = new Rhythm(rhythmData3);
//        assertArrayEquals(new double[] {0.0, 200.0, 400.0, 600.0, 900.0, 1200.0, 1400.0, 1600.0, 1800.0}, r.getAbsoluteRhythm(100));
//    }
//
//    @Test
//    public void testGetAbsoluteRhythm4(){
//        Rhythm r = new Rhythm(rhythmData4);
//        assertArrayEquals(new double[] {0, 600, 750, 900, 1050, 1200, 1400, 1600, 1800, 2100, 2175, 2250}, r.getAbsoluteRhythm(100));
//    }
//
//    /*
//        testGetAbsoluteRhythm5/6/7/8 test for the correct conversion of the relative representation of the rhythm in the
//        held in int[] onsets, to the scaled, absolute version, that uses @param bpm to calculate the onset locations in
//        milliseconds from an epoch of 0. These 4 tests vary bpm through a reasonable range of 40 -> 200
//     */
//
//    @Test
//    public void testGetAbsoluteRhythm5(){
//        Rhythm r = new Rhythm(rhythmData1);
//        assertArrayEquals(new double[] {0.0, 2250.0, 3000.0, 4500.0, 5250.0}, r.getAbsoluteRhythm(40));
//    }
//
//    @Test
//    public void testGetAbsoluteRhythm6(){
//        Rhythm r = new Rhythm(rhythmData2);
//        assertArrayEquals(new double[] {0.0, 750.0, 1312.5, 1500.0, 1875.0, 2250.0}, r.getAbsoluteRhythm(80));
//    }
//
//    @Test
//    public void testGetAbsoluteRhythm7(){
//        Rhythm r = new Rhythm(rhythmData3);
//        assertArrayEquals(new double[] {0.0, 125.0, 250.0, 375.0, 562.5, 750.0, 875.0, 1000.0, 1125.0}, r.getAbsoluteRhythm(160));
//    }
//
//    @Test
//    public void testGetAbsoluteRhythm8(){
//        Rhythm r = new Rhythm(rhythmData4);
//        assertArrayEquals(new double[] {0.0, 300.0, 375.0, 450.0, 525.0, 600.0, 700.0, 800.0, 900.0, 1050.0, 1087.5, 1125.0}, r.getAbsoluteRhythm(200));
//    }
//
//
//
//
//
//}