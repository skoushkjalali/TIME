package Rhythm;

/*
    This class plays a metronome.
 */
public class Metronome {

    public static void playMetronome(double bpm) throws InterruptedException {
        long durationOfBeat = (long) ( (4 / bpm) * 60 * 1000) / 4;
        for(int i = 0; i<4; i++){
            BeepFactory.getBeep();
            Thread.sleep(durationOfBeat);

        }
    }
}
