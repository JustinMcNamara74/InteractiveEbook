package beans;

import data.AccessDB;
import data.Register;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author James
 */
@Named
@SessionScoped
public class RegisterBean implements Serializable {

    @Inject
    private UserBean userBean;
    
    private String response;
    
    /**
     * Creates a new instance of RegisterBean
     */
    public RegisterBean() {
    }

    public void register() {
        if(userBean.getPassword().equals(userBean.getConfirmedPassword())) {
            if (AccessDB.getInstance().isValidCode(userBean.getAccessCode())){
                if(Register.register(userBean)) {
                    userBean.setLoggedIn(true);
                }
                else {
                    response = "Username is already taken.";
                }
                
            }
            else{
                //response = "Invalid AccessCode" + userBean.getAccessCode();
                response = ""+AccessDB.getInstance().isValidCode(userBean.getAccessCode()); 
            }
            
        }
        else {
            response = "Passwords do not match!";
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
