package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Login;
import data.Register;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Slash
 */
@Named(value = "frontEndInterface")
@SessionScoped
public class FrontEndInterface{
    @ManagedProperty(value="#{userBean}")
    private UserBean user;
    
    private int accessCode;
    private String username;
    private String password;
    private String confirmedPassword;
    
    /**
     * Creates a new instance of FrontEndInterface
     */
    public FrontEndInterface() {

    }

    //was getting funky navigation error messages when the following two methods
    //returned a boolean
    public void register(){
        
        //the method in register returns a boolean indicating success
        Register.register(user);
    }
    
    public void login(){
        //the method in login returns a boolean indicating success
        Login.login(username, password);
    }

    /**
     * @return the accessCode
     */
    public int getAccessCode() {
        return accessCode;
    }

    /**
     * @param accessCode the accessCode to set
     */
    public void setAccessCode(int accessCode) {
        this.accessCode = accessCode;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the confirmedPassword
     */
    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    /**
     * @param confirmedPassword the confirmedPassword to set
     */
    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    
    
}
