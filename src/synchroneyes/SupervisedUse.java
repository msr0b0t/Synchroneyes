package synchroneyes;

import java.util.Objects;

/**
 * This class compares the user's answers with the correct ones and computes the results
 *
 * @author      Maria Kouvela
 * @version     1.1
 * @since       14-Jan-16
 */

public class SupervisedUse {
    private String[] userGivenAnswers = new String[24]; // array that contains the answer that user gave to each plate
    private String[] correctResults = new String[24]; //array that contains the answers to the plates of users with no colour vision
    private String[] wrongAnswers = new String[24]; //array that contains which plates user answered wrong

    /**
     * @param userAnswers is an array that contains the string answer that user gave for each plate
     * @param results is a 2d array that contains all the string  options for each plate as created in class Window
     */
    public SupervisedUse(String[] userAnswers,String[][] results){
        //initialising the arrays
        System.arraycopy(userAnswers, 0, userGivenAnswers, 0, 24);
        for(int i = 0 ;i < 24 ;i++ ){
            correctResults[i] = results[i][0];
        }
        //calling computeResults method that compares the user's answers with the correct ones and assigns the result to totalTestResults
        int totalTestResults = computeResults(24);
        //calling computeResults method this time for the first 15 plates of the test
        int fifteenFirstPlatesResult = computeResults(15);
        //calling redGreenColourBlindness that determines if user has red or green colour blindness and assigns the result
        String redGreenColourBlind = redGreenColourBlindness(userGivenAnswers, results);
        //creating object to display the results
        SupervisedUseResults sur = new SupervisedUseResults(totalTestResults, fifteenFirstPlatesResult,redGreenColourBlind,wrongAnswers, userGivenAnswers, correctResults);
    }

    /**
     * computes the number of correct answers given an int
     * @param y int number of answers to plates that are compared
     * @return the number of correct answers
     */
    public int computeResults(int y){ //method that compares user's answers with the correct ones
        int correctAnswers = 0;
        for(int i = 0; i < y; i++){
            if(Objects.equals(userGivenAnswers[i], correctResults[i])){
                correctAnswers++;
            }else{
                wrongAnswers[i] = String.valueOf(i+1);
            }
        }
        return correctAnswers; //returns the number of corrects
    }

    /**
     * determines if user is either red colour blind or green color blind by comparing specific answers to plates
     * @param userGivenAnswers is an array that contains the string answer that user gave for each plate
     * @param results is an array that contains the answers to the plates of users with no colour vision
     * @return string red/green/ok
     */
    public String redGreenColourBlindness(String[] userGivenAnswers, String[][] results){ //method that determines colour blindness from 3 specific plates
        int red = 0;
        int green = 0;
        if (userGivenAnswers[15].equals(results[15][1])){
            red++;
        }
        if (userGivenAnswers[15].equals(results[15][2])){
            green++;
        }
        if (userGivenAnswers[16].equals(results[16][1])){
            red++;
        }
        if (userGivenAnswers[16].equals(results[16][2])){
            green++;
        }
        if (userGivenAnswers[17].equals(results[17][1])){
            red++;
        }
        if (userGivenAnswers[17].equals(results[17][2])){
            green++;
        }
        //returns result
        if(red >= 2){
            return "red";
        }else if(green >= 2){
            return "green";
        }else{
            return "ok";
        }
    }
}
