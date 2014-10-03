/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;
import data.AccessDB;
import data.Login;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import utils.*;

/**
 * @author McNamara
 */
@Named(value = "newPasswordBean")
@RequestScoped
public class CreateNewPasswordBean {
    private String userName;
    private String response;
    private String password;
    private String newPassword;
    private String confPassword;
    private AccessDB db = AccessDB.getInstance();
    private List<String> dbPW;
    
    
    /**
     * Creates a new instance of CreateNewPasswordBean
     */
    public CreateNewPasswordBean() {
        
    }
      public void checkPassAgainstDB(){

            if(Login.login(userName, password)&& newPassword.equals(confPassword)){
                String newPW = ShaPWEncryption.encrypt(newPassword);
                db.update("update user SET password='"+newPW+"' "
                        + "where username= '"+userName+"'" );
                response="Password Reset!";
            }else
                response="Invalid User Information";

        }


    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the confPassword
     */
    public String getConfPassword() {
        return confPassword;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param confPassword the confPassword to set
     */
    public void setConfPassword(String confPassword) {
        this.confPassword = confPassword;
    }
    
}
