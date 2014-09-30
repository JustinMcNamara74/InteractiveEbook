package beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.Login;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author James
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    @Inject
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
            response = "";
            
            FacesContext context = FacesContext.getCurrentInstance();
            ExternalContext externalContext = context.getExternalContext();
            
            try {
                Map<String,String> params = externalContext.getRequestParameterMap();
                
                String url = params.get("redir");
                System.out.println("REDIR: "+url);
                externalContext.redirect(url);
            }
            catch(IOException ex) {
                ex.printStackTrace();
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
