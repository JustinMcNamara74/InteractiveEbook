package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Login;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author James
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    @ManagedProperty(value="#{userBean}")
    private UserBean userBean;
    
    private String response;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        
    }

    public void login() {
        if(Login.login(userBean.getUserName(), userBean.getPassword())) {
            userBean.setLoggedIn(true);
        }
        else {
            response = "Invalid username and/or password :(";
        }
    }
    
    /**
     * @return the userBean
     */
    public UserBean getUserBean() {
        return userBean;
    }

    /**
     * @param userBean the userBean to set
     */
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    /**
     * @return the response
     */
    public String getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(String response) {
        this.response = response;
    }
    
    
}
