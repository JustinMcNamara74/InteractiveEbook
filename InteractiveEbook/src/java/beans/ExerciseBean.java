/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.net.URL;
import java.util.Scanner;
import javax.inject.Inject;


@Named(value = "exerciseBean")
@SessionScoped
public class ExerciseBean implements Serializable {

    @Inject
    private UserBean userBean;
    
    private static final String CORRECT = "The submission to this question is correct";
    private static final String INCORRECT = "The submission to this question is incorrect";
    private static final String NOT_SUBMITTED = "The exercise has not been submitted";
    
    /**
     * Creates a new instance of ExerciseBean
     */
    public ExerciseBean() {
    }
    
    public String exerciseStatusImage(String exercise) {
        try {
            URL url = new URL("http://liang.armstrong.edu:8080/selftest/GetExerciseStatus?username="+userBean.getUserName()+"&exerciseName="+exercise);
            Scanner urlIn = new Scanner(url.openStream());
            
            switch(urlIn.nextLine().trim()) {
                case CORRECT:
                    return "images/success-icon.png";
                case INCORRECT:
                    return "images/incorrect.png";
                default:
                    return "images/not-submitted.png";
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        
        return "images/not-submitted.png";
    }
}
