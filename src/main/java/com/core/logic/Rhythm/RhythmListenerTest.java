//package com.core.logic.Rhythm;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.testng.AssertJUnit.assertEquals;
//
//class RhythmListenerTest {
//
//    RhythmListener listener = new RhythmListener();
//
//
//    @Test
//    void getShiftedUserInput0() {
//        RhythmListener.userInput.clear();
//        RhythmListener.userInput.add(4800);
//        RhythmListener.userInput.add(7200);
//        RhythmListener.userInput.add(9600);
//        ArrayList<Integer> result = listener.getShiftedUserInput(100, 2);
//        assertEquals(0, result.get(0));
//    }
//
//    @Test
//    void getShiftedUserInput1() {
//        RhythmListener.userInput.clear();
//        RhythmListener.userInput.add(4800);
//        RhythmListener.userInput.add(7200);
//        RhythmListener.userInput.add(9600);
//        ArrayList<Integer> result = listener.getShiftedUserInput(100, 2);
//        assertEquals(2400, result.get(1));
//    }
//
//    @Test
//    void getShiftedUserInput2() {
//        RhythmListener.userInput.clear();
//        RhythmListener.userInput.add(4800);
//        RhythmListener.userInput.add(7200);
//        RhythmListener.userInput.add(9600);
//        ArrayList<Integer> result = listener.getShiftedUserInput(100, 2);
//        assertEquals(4800, result.get(2));
//    }
//
//    @Test
//    void getShiftedUserInput3() {
//        RhythmListener.userInput.clear();
//        RhythmListener.userInput.add(7000);
//        RhythmListener.userInput.add(20000);
//        RhythmListener.userInput.add(30000);
//        ArrayList<Integer> result = listener.getShiftedUserInput(80, 3);
//        assertEquals(-2000, result.get(0));
//    }
//
//    @Test
//    void getShiftedUserInput4() {
//        RhythmListener.userInput.clear();
//        RhythmListener.userInput.add(10000);
//        RhythmListener.userInput.add(20000);
//        RhythmListener.userInput.add(30000);
//        ArrayList<Integer> result = listener.getShiftedUserInput(80, 3);
//        assertEquals(11000, result.get(1));
//    }
//
//    @Test
//    void getShiftedUserInput5() {
//        RhythmListener.userInput.clear();
//        RhythmListener.userInput.add(10000);
//        RhythmListener.userInput.add(20000);
//        RhythmListener.userInput.add(30000);
//        ArrayList<Integer> result = listener.getShiftedUserInput(80, 3);
//        assertEquals(21000, result.get(2));
//    }
//
//
//}