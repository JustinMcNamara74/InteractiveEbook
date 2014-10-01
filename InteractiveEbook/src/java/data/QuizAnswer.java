/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author James
 */
public class QuizAnswer {
    @JsonIgnore
    private int chapter;
    @JsonIgnore
    private int number;
    
    
    private int answer;
    private String answerText;
    private boolean isCorrect;

    
    public QuizAnswer(String answerData) {
        
        String[] data = answerData.split(AccessDB.DELIMETER);
        
        this.chapter = Integer.parseInt(data[0]);
        this.number = Integer.parseInt(data[1]);
        this.answer = Integer.parseInt(data[2]);
        
        // make question text javascript friendly
        this.answerText = data[3];
        
        this.isCorrect = data[4].equals("1");
        
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
     * @return the answer
     */
    public int getAnswer() {
        return answer;
    }

    /**
     * @param answer the answer to set
     */
    public void setAnswer(int answer) {
        this.answer = answer;
    }

    /**
     * @return the answerText
     */
    public String getAnswerText() {
        return answerText;
    }

    /**
     * @param answerText the answerText to set
     */
    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    /**
     * @return the isCorrect
     */
    public boolean isIsCorrect() {
        return isCorrect;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    
    
}
