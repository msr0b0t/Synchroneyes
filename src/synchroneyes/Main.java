package synchroneyes;

import java.util.Objects;

/**
 * Main class
 *
 * @author      Maria Kouvela
 * @version     1.1
 * @since       14-Jan-16
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Window myWindow = new Window(); //creating object for the frame
        while(myWindow.getChoice() == null){
            Thread.sleep(5); //main waits until user selects one of the two options of the frame
        }
        if (Objects.equals(myWindow.getChoice(), "Personal use")){
            while(!myWindow.isTestIsOver()){
                Thread.sleep(5); //main waits until test is over to extract the results
            }
            PersonalUse test = new PersonalUse(myWindow.getUserAnswers(),myWindow.getResultArray()); //creating object
        }else{
            while(!myWindow.isTestIsOver()){
            Thread.sleep(5); //main waits until test is over to extract the results
            }
            SupervisedUse test = new SupervisedUse(myWindow.getUserAnswers(),myWindow.getResultArray()); //creating object
        }
    }
}
