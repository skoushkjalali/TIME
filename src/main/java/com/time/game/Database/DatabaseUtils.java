package com.time.game.Database;
import java.sql.*;


import java.util.ArrayList;
import java.util.Objects;

public class DatabaseUtils {

    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TIME",
                    "root", "guest");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        System.out.println(validateExistingUser("SashaKJ", "admin"));

    }


    public static boolean validateExistingUser(String userUsername, String userPassword) throws SQLException {
        boolean validated = false;

        String sql = "SELECT * FROM USER " +
                "WHERE USERNAME = '" +userUsername+ "' AND PASSWORD = '"+ userPassword+"'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        try {
            rs.next();
            if(Objects.equals(rs.getString("USERNAME"), userUsername)){
                validated = true;
            }
        }
        catch (SQLException ignored){
        }

        return validated;
    }


    /*
        Method converts integer values held in an arraylist to a single string. The string comprises a string of codes.
        A digit corresponding to the number of digits in the next score, and then the score. This pattern is repeated.
        i.e., 18 = 1 digit, score = 8; 210 = 2 digits, score = 10; 3100 = 3 digits, score = 100.
        This would result in a string of "18210103100"
     */
    public static String compressUserLevelScores(ArrayList<Integer> userLevelScores){
        if(userLevelScores == null){
            return "";
        }
        StringBuilder compressedScores = new StringBuilder();
        String scoreStr;
        int length;

        for(int score : userLevelScores){
            scoreStr = Integer.toString(score);
            length = scoreStr.length();
            compressedScores.append(length).append(scoreStr);
        }
        return compressedScores.toString();
    }

    /*
        Method converts a string of integer scores to an arrayList of those scores.
        The string uses the same compression method as above.
        "10" = 1 digit, score = 0; "283" = 2 digits, score = 83.
        Therefore, a string of "10256310016" = ArrayList of {0, 56, 100, 6}
     */
    public static ArrayList<Integer> decompressUserLevelScores(String compressedUserLevelScores){
        ArrayList<Integer> userLevelScores = new ArrayList<>();
        int numberOfDigits;
        int score;
        String scoreStr = "";
        for(int i = 0; i < compressedUserLevelScores.length();){
            numberOfDigits = Integer.parseInt(Character.toString(compressedUserLevelScores.charAt(i)));
            for(int j = 1; j <= numberOfDigits; j++) {
                scoreStr += compressedUserLevelScores.charAt(i + j);
            }
            score = Integer.parseInt(scoreStr);
            userLevelScores.add(score);
            scoreStr = "";
            i+= numberOfDigits + 1;
        }
        return userLevelScores;
    }
}
