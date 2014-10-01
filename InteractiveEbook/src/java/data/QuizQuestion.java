/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author James
 */
public class QuizQuestion {
    
    public static final int UNANSWERED = 0;
    public static final int CORRECT = 1;
    public static final int INCORRECT = 2;
    
    
    private int chapter;
    private int section;
    private int number;
    private String questionText;
    private boolean multipleChoice;

    private List<QuizAnswer> answers;
    
    private int userStatus;
    private List<String> userLastAnswers;
    
    /**
     * Constructor that will handle parsing of response from AccessDB query.
     * @param questionData 
     */
    public QuizQuestion(String questionData) {
        
        String[] data = questionData.split(AccessDB.DELIMETER);
        
        this.chapter = Integer.parseInt(data[0]);
        this.section = Integer.parseInt(data[1]);
        this.number = Integer.parseInt(data[2]);
        
        // make question text javascript friendly
        this.questionText = data[3];
        
        this.multipleChoice = data[4].equals("1");

    }
    
    public QuizQuestion(int chapter, int section, int number, String questionText, boolean multipleChoice) {
        this.chapter = chapter;
        this.section = section;
        this.number = number;
        this.questionText = questionText;
        this.multipleChoice = multipleChoice;
    }
    
    /**
     * @return the chapter
     */
    public int getChapter() {
        return chapter;
    }

    /**
     * @param chapter the chapter to set
     */
    public void setChapter(int chapter) {
        this.chapter = chapter;
    }

    /**
     * @return the section
     */
    public int getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(int section) {
        this.section = section;
    }

    /**
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the questionText
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * @param questionText the questionText to set
     */
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    /**
     * @return the multipleChoice
     */
    public boolean isMultipleChoice() {
        return multipleChoice;
    }

    /**
     * @param multipleChoice the multipleChoice to set
     */
    public void setMultipleChoice(boolean multipleChoice) {
        this.multipleChoice = multipleChoice;
    }

    /**
     * @return the answers
     */
    public List<QuizAnswer> getAnswers() {
        return answers;
    }

    /**
     * @param answers the answers to set
     */
    public void setAnswers(List<String> answersData) {
        answers = new ArrayList<QuizAnswer>();
        
        for(String ad : answersData) {
            answers.add(new QuizAnswer(ad));
        }
    }
    
    
    public static String statusToString(int answerStatus) {
        switch(answerStatus) {
            case INCORRECT:
                return "INCORRECT";
            case CORRECT:
                return "CORRECT";
            default:
                return "UNANSWERED";
        }
    }

    /**
     * @return the userStatus
     */
    public int getUserStatus() {
        return userStatus;
    }

    /**
     * @param userStatus the userStatus to set
     */
    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * @return the userLastAnswers
     */
    public List<String> getUserLastAnswers() {
        return userLastAnswers;
    }

    /**
     * @param userLastAnswers the userLastAnswers to set
     */
    public void setUserLastAnswers(List<String> userLastAnswers) {
        this.userLastAnswers = userLastAnswers;
    }
 
    
    
}
