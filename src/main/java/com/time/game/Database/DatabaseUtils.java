package com.time.game.Database;
import com.time.game.Model.Profile.UserProfile;
import com.time.game.Model.Rhythm.RhythmFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class DatabaseUtils {
    public static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TIME",
                    "root", "guest");
        } catch (SQLException ignored) {}
    }

    public static boolean validateExistingUser(String username, String password) throws SQLException {
        boolean validated = false;

        String sql = "SELECT * FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet rs = preparedStatement.executeQuery();

        try {
            rs.next();
            if(Objects.equals(rs.getString("USERNAME"), username)){
                validated = true;
            }
        }
        catch (SQLException ignored){
        }
        return validated;
    }

    public static boolean checkNewUsernameIsAvailable(String newUsername) throws SQLException {

        boolean usernameAvailable = true;

        String sql = "SELECT USERNAME FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,newUsername);
        ResultSet rs = preparedStatement.executeQuery();

        try {
            rs.next();
            if(Objects.equals(rs.getString("USERNAME"), newUsername)){
                usernameAvailable = false;
            }
        }
        catch (SQLException ignored){
        }
        return usernameAvailable;
    }

    public static void createNewUserProfile(String username, String password) throws SQLException {
        String sql = "INSERT INTO USER VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
    }

    public static void addNewUserToScoresTable(String username) throws SQLException {
        String sql = "INSERT INTO USER_SCORES (USERNAME) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.executeUpdate();
    }

    /*
        Method converts integer values held in an arraylist to a single string. The string comprises a string of codes.
        A digit corresponding to the number of digits in the next score, and then the score. This pattern is repeated.
        i.e., 18 = 1 digit, score = 8; 210 = 2 digits, score = 10; 3100 = 3 digits, score = 100.
        This would result in a string of "18210103100"
     */
    public static String compressUserLevelScores(ArrayList<Integer> userLevelScores){
        if(userLevelScores == null){
            return null;
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
        // user has not played this level
        if(compressedUserLevelScores == null){
            return null;
        }
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

    /*
        Compresses all user scores per level as one string, and returns these strings in an arraylist.
     */
    public static ArrayList<String> getUserProfileScoreDataAsStrings(UserProfile userProfile){
        ArrayList<String> allUserScores = new ArrayList<>();
        for(int i = 1; i <= RhythmFactory.getLastPossibleRhythmNumber(); i++){
            String compressedLevelScores = compressUserLevelScores(userProfile.getLevelScoreAttempts(i));
            allUserScores.add(compressedLevelScores);
        }
        return allUserScores;
    }

    /*
        Updates all scores of all levels on the database for the user specified. If a level hasn't been played i.e.,
        its ArrayList of scores is null, no update statement is run.
     */
    public static void updateAllUserData(UserProfile userProfile) throws SQLException {
        ArrayList<String> userLevelData = getUserProfileScoreDataAsStrings(userProfile);
        // count all non-null objects in userLevelData
        int numLevelsToUpdate = (int) userLevelData.stream().filter(Objects::nonNull).count();
        // no update needed
        if(numLevelsToUpdate == 0){
            return;
        }
        // build one query string for all updates
        StringBuilder sql = new StringBuilder("UPDATE USER_SCORES SET ");
        for (int i = 0; i < userLevelData.size(); i++) {
            String colName;
            String data;
            if (userLevelData.get(i) != null) {
                colName = "Rhythm_" + (i + 1);
                data = userLevelData.get(i);
                sql.append(colName)
                        .append(" = ")
                        .append("'")
                        .append(data)
                        .append("'");
                // add a comma for the next data element if it exists
                if(numLevelsToUpdate > 1){
                    sql.append(", ");
                    numLevelsToUpdate -=1;
                }
            }
        }
        sql.append(" WHERE USERNAME = ?");
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        preparedStatement.setString(1, userProfile.getUsername());
        preparedStatement.executeUpdate();
    }

    public static void loadUserDataToLocalProfile(UserProfile userProfile) throws SQLException {
        String sql = "SELECT * FROM USER_SCORES WHERE USERNAME = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, userProfile.getUsername());
        ResultSet rs = preparedStatement.executeQuery();

        // move to the username column, so that the next call to rs.next()
        // returns the first rhythm column, col2
        rs.next();

        // iterate through each rhythm column and place an uncompressed
        // arrayList of scores in the local userProfile object
        for(int columnNumber = 2; columnNumber <= RhythmFactory.getLastPossibleRhythmNumber()+1; columnNumber++) {
            String compressedLevelData = rs.getString(columnNumber);
            ArrayList<Integer> uncompressedLevelData = DatabaseUtils.decompressUserLevelScores(compressedLevelData);
            userProfile.getLevelScores().put(columnNumber-1,uncompressedLevelData);
        }
    }

}
