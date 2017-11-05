package synchroneyes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the graphics for both the start frame and the frame for the tests
 *
 * @author      Maria Kouvela
 * @version     1.1
 * @since       14-Jan-16
 */

public class Window{
    private String choice; //user's choice (Personal use / Supervised use)
    private String[] userAnswers = new String[24]; //array that contains the answers of the user
    private JButton next = new JButton("Next");
    private int i; //counter for each test
    private boolean testIsOver = false; //flag is used to inform main when test is over
    private final String[][] resultArray = new String[24][3]; //array that contains the results of the test
    //first column contains the correct answer
    //second column contains the expected answer if user is red-green color blind
    //or for some plates if user is red color blind
    //third column contains the expected answer if user is total color blind
    //or for some if user is green color blind

    public Window() {
        //calling method for initialising the result array
        setResultArray();


        //creating the start window first
        JFrame startFrame = new JFrame("Synchroneyes");
        startFrame.setIconImage(new ImageIcon("synchroneye.jpg").getImage());
        startFrame.getContentPane().setBackground(Color.white); //setting the color of the background
        startFrame.setLocation(600,200);
        startFrame.setSize(800,600);
        startFrame.setResizable(true);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);


        //creating the test frame
        JFrame frame = new JFrame("Synchroneyes");
        frame.setIconImage(new ImageIcon("synchroneye.jpg").getImage());
        frame.getContentPane().setBackground(Color.white); // setting the color of the background(best to be white)
        frame.setLocation(600,200);
        frame.setSize(800,600);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        setI(0); //for the first test

        //creating jlabel for image
        JLabel picture = new JLabel(new ImageIcon(String.valueOf(i).concat(".jpg")));
        picture.setPreferredSize(new Dimension(281, 276)); //standard size

        //creating radio buttons of the test frame for later use
        JRadioButton firstButton = new JRadioButton(resultArray[getI()][0]);
        firstButton.setActionCommand(resultArray[getI()][0]);
        firstButton.setBackground(Color.white);
        JRadioButton secondButton = new JRadioButton(resultArray[getI()][1]);
        secondButton.setActionCommand(resultArray[getI()][1]);
        secondButton.setBackground(Color.white);
        JRadioButton thirdButton = new JRadioButton(resultArray[getI()][2]);
        thirdButton.setActionCommand(resultArray[getI()][2]);
        thirdButton.setBackground(Color.white);

        makeStartFrame(startFrame, frame, picture, firstButton, secondButton, thirdButton);

    }

    /**
     * @param frame Jframe to be refreshed
     * @param picture Jlabel is a picture to be refreshed
     * @param firstButton a radio button that contains one of the 3 choices from resultArray, to be refreshed
     * @param secondButton a radio button that contains one of the 3 choices from resultArray, to be refreshed
     * @param thirdButton a radio button that contains one of the 3 choices from resultArray, to be refreshed
     */
    public void makeFrame( JFrame frame, JLabel picture, JRadioButton firstButton, JRadioButton secondButton, JRadioButton thirdButton){

        frame.setVisible(true);
        //refreshing image
        picture.removeAll();
        picture.setIcon(new ImageIcon(String.valueOf(getI()).concat(".jpg")));

        //refreshing the radio buttons
        firstButton.setText(resultArray[getI()][0]);
        firstButton.setActionCommand(resultArray[getI()][0]);
        firstButton.setBackground(Color.white);
        firstButton.revalidate();

        secondButton.setText(resultArray[getI()][1]);
        secondButton.setActionCommand(resultArray[getI()][1]);
        secondButton.setBackground(Color.white);
        secondButton.revalidate();

        thirdButton.setText(resultArray[getI()][2]);
        thirdButton.setActionCommand(resultArray[getI()][2]);
        thirdButton.setBackground(Color.white);
        thirdButton.revalidate();


        next.setBackground(Color.lightGray);
        next.setEnabled(false); //next button is not enabled until user selects one of the radio buttons

        //grouping the radio buttons so that only one can be selected each time
        ButtonGroup group = new ButtonGroup();
        group.add(firstButton);
        group.add(secondButton);
        group.add(thirdButton);
        group.clearSelection(); //in each test none of the radio buttons should be selected before user selects one

        //adding the radio buttons to a panel
        JPanel jplRadio = new JPanel();
        jplRadio.setLayout(new GridLayout(0, 1));
        jplRadio.add(firstButton);
        jplRadio.add(secondButton);
        jplRadio.add(thirdButton);

        //adding all the components to the layout and setting  up the frame
        frame.setLayout(new BorderLayout());
        frame.add(picture, BorderLayout.NORTH);
        frame.add(jplRadio, BorderLayout.CENTER);
        frame.add(next,BorderLayout.SOUTH);


        //adding action listeners to the radio buttons
        //filling userAnswers array with the choice of the radio button that was clicked
        firstButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                userAnswers[getI()] =resultArray[getI()][0];
                next.setEnabled(true); //next button is enabled
            }
        });

        secondButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                userAnswers[getI()] =resultArray[getI()][1];
                next.setEnabled(true); //next button is enabled
            }
        });

        thirdButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                userAnswers[getI()] =resultArray[getI()][2];
                next.setEnabled(true); //next button is enabled
            }
        });

        //adding action listener to the "next" button
        if (next.getActionListeners().length < 1){ //checking if listener is already enabled
            next.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    setI(getI() + 1); //increasing i
                    if(getI() <= 23) { //if i<=23 then refresh frame
                        makeFrame( frame, picture, firstButton, secondButton, thirdButton);
                    }
                    if(getI() == 24){
                        frame.setVisible(false);//when the test ends the frame must disappear
                        setTestIsOver(true);//informs main that the test has ended
                    }
                }

            });
        }


    }

    /**
     * @param startFrame Jframe for the start window
     * @param frame Jframe of the tests
     * @param picture Jlabel is a picture to be refreshed
     * @param firstButton a radio button that contains one of the 3 choices from resultArray for the first test frame
     * @param secondButton a radio button that contains one of the 3 choices from resultArray for the first test frame
     * @param thirdButton a radio button that contains one of the 3 choices from resultArray for the first test frame
     */
    public void makeStartFrame(JFrame startFrame, JFrame frame, JLabel picture, JRadioButton firstButton, JRadioButton secondButton, JRadioButton thirdButton){
        //creating jlabel for picture
        JLabel startPicture = new JLabel(new ImageIcon("synchroneye.jpg"));
        startPicture.setPreferredSize(new Dimension(281, 276)); //standard size

        //creating jradio buttons for the user's choice (Personal use/ Supervised use)
        JRadioButton firstOption = new JRadioButton("Personal use");
        firstOption.setActionCommand("Personal use");
        firstOption.setBackground(Color.white);
        JRadioButton secondOption = new JRadioButton("Supervised use");
        secondOption.setActionCommand("Supervised use");
        secondOption.setBackground(Color.white);

        //grouping the radio buttons so that only one can be selected each time
        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(firstOption);
        optionGroup.add(secondOption);

        //adding the radio buttons to a panel
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new GridLayout(0, 1));
        optionPanel.add(firstOption);
        optionPanel.add(secondOption);

        //making "ok" button
        JButton ok = new JButton("Ok");
        ok.setBackground(Color.lightGray);
        ok.setEnabled(false); // ok button is not enabled until user selects one of the two options

        //adding all the components to the start frame
        startFrame.setLayout(new BorderLayout());
        startFrame.add(startPicture, BorderLayout.NORTH);
        startFrame.add(optionPanel, BorderLayout.CENTER);
        startFrame.add(ok,BorderLayout.SOUTH);

        //adding action listeners to the radio buttons
        //filling choice with the option of the radio button that was clicked
        firstOption.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                setChoice("Personal use");
                ok.setEnabled(true); //ok button is enabled
            }
        });

        secondOption.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                setChoice("Supervised use");
                ok.setEnabled(true); //ok button is enabled
            }
        });

        //adding action listener to the "ok" button
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                makeFrame( frame, picture, firstButton, secondButton, thirdButton);
                startFrame.setVisible(false); //when user clicks "ok" button, the start frame must disappear
            }

        });

        startFrame.revalidate();
        startFrame.repaint();

    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String[] getUserAnswers() {
        return userAnswers;
    }


    public boolean isTestIsOver() {
        return testIsOver;
    }

    public void setTestIsOver(boolean testIsOver) {
        this.testIsOver = testIsOver;
    }



    public void setUserAnswers(String[] userAnswers) {
        this.userAnswers = userAnswers;
    }

    public String[][] getResultArray() {
        return resultArray;
    }

    /**
     * initialises resultArray with the instructed options
     */
    public void setResultArray(){
        for(int y = 0; y < 15; y++){
            resultArray[y][2]="nothing / other";
        }
        resultArray[0][0]="12";
        resultArray[0][1]="nothing";

        resultArray[1][0]="8";
        resultArray[1][1]="3";

        resultArray[2][0]="29";
        resultArray[2][1]="70";

        resultArray[3][0]="5";
        resultArray[3][1]="2";

        resultArray[4][0]="3";
        resultArray[4][1]="5";

        resultArray[5][0]="15";
        resultArray[5][1]="17";

        resultArray[6][0]="74";
        resultArray[6][1]="21";

        for(int y = 7; y < 13; y++){
            resultArray[y][1]="nothing";
        }

        resultArray[7][0]="6";

        resultArray[8][0]="45";

        resultArray[9][0]="5";

        resultArray[10][0]="7";

        resultArray[11][0]="16";

        resultArray[12][0]="73";

        resultArray[13][0]="nothing";
        resultArray[13][1]="5";

        resultArray[14][0]="nothing";
        resultArray[14][1]="45";

        resultArray[15][0]="26";
        resultArray[15][1]="6 and possibly 2"; //red color-blind
        resultArray[15][2]="2 and possibly 6"; //green color-blind

        resultArray[16][0]="42";
        resultArray[16][1]="2 and possibly 4"; //red color-blind
        resultArray[16][2]="4 and possibly 2"; //green color-blind

        resultArray[17][0]="purple and red line";
        resultArray[17][1]="purple line"; //red color-blind
        resultArray[17][2]="red line"; //green color-blind

        resultArray[18][0]="nothing";
        resultArray[18][1]="wiggly line"; //red-green color-blind
        resultArray[18][2]="nothing / other";

        resultArray[19][0]="green wiggly line";
        resultArray[19][1]="other"; //red-green color-blind
        resultArray[19][2]="nothing";

        resultArray[20][0]="orange wiggly line";
        resultArray[20][1]="other"; //red-green color-blind
        resultArray[20][2]="nothing";

        resultArray[21][0]="blue-green / yellow-green wiggly line";
        resultArray[21][1]="blue-green and red line"; //red-green color-blind
        resultArray[21][2]="nothing / other";

        resultArray[22][0]="red-orange wiggly line";
        resultArray[22][1]="red and blue-green wiggly line"; //red-green color-blind
        resultArray[22][2]="nothing / other";

        resultArray[23][0]="orange wiggly line";
        resultArray[23][1]="other"; //red-green color-blind
        resultArray[23][2]="nothing";
    }


}
