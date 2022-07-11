package com.core.logic.Rhythm;


import com.core.logic.GUI.MockGUI;

/*
    This class is responsible for selecting the implementation that allows capture of user taps from the
    keyboard.
 */
public class UserTapFactory {

    /*
        This method calls a concrete implementation that provides functionality to capture user taps from the keyboard.
     */
    public static void getUserTaps(){
        MockGUI.getUserTaps();
    }


}
