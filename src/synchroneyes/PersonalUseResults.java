package synchroneyes;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the graphics for the result frame of personal use
 *
 * @author      Maria Kouvela
 * @version     1.1
 * @since       14-Jan-16
 */

public class PersonalUseResults {

    /**
     * @param result total number of correct answers out of 24 plates
     * @param fifteenFirstPlatesResult number of correct answers out of the first 15 plates
     * @param redGreenColourBlind string that states if user is red-green colour blind (red/green/ok)
     * @param userGivenAnswers array that contains the answer that user gave for each plate
     * @param correctResults array that contains the answers to the plates of users with no colour vision
     */
    public PersonalUseResults(int result,int fifteenFirstPlatesResult, String redGreenColourBlind, String[] userGivenAnswers, String[] correctResults){

        //creating the results frame
        JFrame results = new JFrame("Synchroneyes");
        results.setIconImage(new ImageIcon("synchroneye.jpg").getImage());
        results.getContentPane().setBackground(Color.white); //setting the color of the background
        results.setLocation(600,200);
        results.setSize(800,600);
        results.setResizable(true);
        results.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        results.setVisible(true);

        //creating jlabel for picture
        JLabel resultPicture = new JLabel(new ImageIcon("synchroneye.jpg"));
        resultPicture.setPreferredSize(new Dimension(281, 276)); //standard size

        //initialising strings for displaying them later
        String resultText;
        resultText = "Congratulations, you completed the test. You gave " + String.valueOf(result) + "/24 correct answers.";
        String resultText1;
        if(fifteenFirstPlatesResult >= 13){
            resultText1=" Your colour vision is considered as adequate.";
        }else if(fifteenFirstPlatesResult >= 10){
            resultText1=" Your colour vision is considered as normal.";
        }else{
            resultText1=" Your colour vision is considered as deficient.";
        }
        String resultText2;
        if(redGreenColourBlind.equals("red")){
            resultText2=" You might be red-colour blind.";
        }else if(redGreenColourBlind.equals("green")){
            resultText2=" You might be green-colour blind.";
        }else {
            resultText2="";
        }
        String resultText3;
        resultText3=" If you have any doubts or questions you should get advice from an ophthalmologist.";

        //creating jlabels for the strings
        JLabel textLabel = new JLabel(resultText);
        JLabel textLabel1 = new JLabel(resultText1);
        JLabel textLabel2 = new JLabel(resultText2);
        JLabel textLabel3 = new JLabel(resultText3);

        //enlarging the font of text
        textLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        textLabel1.setFont(new Font("Serif", Font.PLAIN, 20));
        textLabel2.setFont(new Font("Serif", Font.PLAIN, 20));
        textLabel3.setFont(new Font("Serif", Font.PLAIN, 20));

        //centering the jlabels
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel1.setVerticalAlignment(SwingConstants.CENTER);
        textLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel2.setVerticalAlignment(SwingConstants.CENTER);
        textLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel3.setVerticalAlignment(SwingConstants.CENTER);

        //creating panel to group the jlabels
        JPanel labels = new JPanel();
        labels.setLayout(new GridLayout(0, 1));
        labels.add(textLabel);
        labels.add(textLabel1);
        labels.add(textLabel2);
        labels.add(textLabel3);

        labels.setBackground(Color.white); //setting background colour white

        //adding all the components to the layout and setting  up the frame
        results.setLayout(new BorderLayout());
        results.add(resultPicture, BorderLayout.NORTH);
        results.add(labels, BorderLayout.CENTER);

    }
}
