package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Login;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author James
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {

    @Inject
    private UserBean userBean;
    
    private String response;
    private String originalURL;
    
    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        
    }
    
    @PostConstruct
    public void init() {
        this.originalURL = userBean.getOriginalURL();
    }

    public void login() {
        if(Login.login(userBean.getUserName(), userBean.getPassword())) {
            userBean.setLoggedIn(true);
            response = "";
            
            if(originalURL != null) {
                FacesContext context = FacesContext.getCurrentInstance();
                ExternalContext externalContext = context.getExternalContext();

                try {
                    externalContext.redirect(originalURL);
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
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
