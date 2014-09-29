package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;


/**
 *
 * @author McNamara
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

    /**
     * Creates a new instance of User
     */
    private String userName;
    private String password;
    
    // Used for registration
    private String confirmedPassword;
    private int accessCode;
       
    private String firstName;
    private String lastName;
    private String middle;
    private String email;
    private String phone;
    
    private boolean loggedIn;

     
    public UserBean(){
   
    }

    
    public boolean logout(){
        FacesContext.getCurrentInstance().
                getExternalContext().invalidateSession();
        
        return true;
    }
    
    
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middle
     */
    public String getMiddle() {
        return middle;
    }

    /**
     * @param middle the middle to set
     */
    public void setMiddle(String middle) {
        this.middle = middle;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }  

    /**
     * @return the isLoggedIn
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * @param isLoggedIn the isLoggedIn to set
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
    
    
}
