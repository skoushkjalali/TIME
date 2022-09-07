package com.time.game.Model.Rhythm;

import java.util.Arrays;

/*
    This class represents a rhythm in its relative form, as an int array of onsets, and the corresponding number of
    segments in the rhythm.
    segments = rhythmData[0] is the number of equal time segments in the rhythm.
 */
public class Rhythm {
    // the relative representation of all onset locations in arbitrary segments.
    private final int[] onsets;
    // the number of segments in the relative representation.
    private final int segments;

    /*
        Constructor for Rhythm object.
        The first entry in rhythmData is always the number of segments in the rhythm representation.
        The remaining data are the onsets.
     */
    public Rhythm(int[] rhythmData){
        this.segments = rhythmData[0];
        this.onsets = Arrays.copyOfRange(rhythmData,1, rhythmData.length);
    }

    public int getSegments() {
        return segments;
    }

    public int[] getRelativeRhythm() {
        return onsets;
    }

    /*
        This method returns a double array of the bpm scaled, absolute onset location of the relative rhythm held in
        onsets.
        Each value is the number of milliseconds from 0 that the onset occurs, at the bpm that is passed as @param bpm.
     */
    public double[] getAbsoluteRhythm(double bpm){
        // total duration of rhythm in milliseconds
        double totalDurationOfRhythm = (4 / bpm) * 60 * 1000;
        // total duration of each bit (or segment) in milliseconds
        double durationOfEachBit = totalDurationOfRhythm / this.segments;
        // array holds the temporal location of each onset from a zero point in time.
        return Arrays.stream(onsets).mapToDouble(o -> o*durationOfEachBit).toArray();
    }

}














