/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Slash
 */
@Named(value = "frontEndInterface")
@ViewScoped
public class FrontEndInterface{
    private int acode;
    private String user;
    private String pword;

    /**
     * Creates a new instance of FrontEndInterface
     */
    public FrontEndInterface() {
    }
    
    public void addAccessCode(){
        AddAccessCode.add();
    }
    //was getting funky navigation error messages when the following two methods
    //returned a boolean
    public void register(){
        //the method in register returns a boolean indicating success
        Register.register(acode, user, pword);
    }
    
    public void login(){
        //the method in login returns a boolean indicating success
        Login.login(user, pword);
    }

    /**
     * @return the acode
     */
    public int getAcode() {
        return acode;
    }

    /**
     * @param acode the acode to set
     */
    public void setAcode(int acode) {
        this.acode = acode;
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the pword
     */
    public String getPword() {
        return pword;
    }

    /**
     * @param pword the pword to set
     */
    public void setPword(String pword) {
        this.pword = pword;
    }
    
}
