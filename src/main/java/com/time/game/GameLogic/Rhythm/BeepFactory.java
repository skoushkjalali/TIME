package com.time.game.GameLogic.Rhythm;


/*
    This class generates a beep sound. As development continues, the option to change the type of sound, and the
    process by which it is generated can be done here.
 */
public class BeepFactory {

    /*
        This method generates a beep sound.
     */
    public static void getBeep(){
        java.awt.Toolkit.getDefaultToolkit().beep();
    }


}
