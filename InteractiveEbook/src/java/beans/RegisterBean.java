package beans;

import data.AccessDB;
import data.Register;
import java.io.IOException;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author James
 */
@Named
@RequestScoped
public class RegisterBean implements Serializable {

    @Inject
    private UserBean userBean;
    
    private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
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
                    
                    FacesContext context = FacesContext.getCurrentInstance();
                    ExternalContext externalContext = context.getExternalContext();

                    if(userBean.getOriginalURL() != null) {
                        try {
                            externalContext.redirect(userBean.getOriginalURL());
                        }
                        catch(IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else {
                    response = "Username is already taken.";
                }
                
            }
            else{
                response = "Invalid Access Code.";
                //response = ""+AccessDB.getInstance().isValidCode(userBean.getAccessCode());

            }
            
        }
        else {
            response = "Passwords do not match!";
        }
        
    }


    public void validate(String email) {

        String enteredEmail = email;
        //Set the email pattern string
        Pattern p = Pattern.compile(EMAIL_PATTERN);

        //Match the given string with the pattern
        Matcher m = p.matcher(enteredEmail);

        //Check whether match is found
        if (!m.matches()) {
            System.out.println(email + " validated!");
            response = "Not a valid Email";
        } else {
            System.out.println(email + " is an invalid email name");
            response = "Valid Email";
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
