package Driver;


import Rhythm.*;



import java.util.ArrayList;

public class Driver {

    private static long bpm = 100;

    public void setBpm(long bpm) {
        Driver.bpm = bpm;
    }

    public static void main(String[] args) throws InterruptedException {


        RhythmPlayer rhythmPlayer = new RhythmPlayer(bpm);
        RhythmListener listener = new RhythmListener();

        Rhythm r = RhythmFactory.getRhythm(2);
        Metronome.playMetronome(bpm);
        rhythmPlayer.playRhythm(r);
        listener.userTaps();
        Thread.sleep(2500); // 4 beats of user input

        System.out.println(RhythmListener.userInput);







    }
}
