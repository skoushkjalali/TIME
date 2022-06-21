package Rhythm;


/*
    This class generates a beep sound. As development continues, the option to change the type of sound, and the
    process by which it is generated can be done here.
 */
public class BeepFactory {

    public static void getBeep(){
        java.awt.Toolkit.getDefaultToolkit().beep();
    }


}
