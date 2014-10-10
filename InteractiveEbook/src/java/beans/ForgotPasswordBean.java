
package beans;

import data.AccessDB;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import utils.Email;
import utils.ShaPWEncryption;
import utils.Utils;


@Named(value = "forgotPasswordBean")
@RequestScoped
public class ForgotPasswordBean {

    private String response;
    
    private String email;
    private AccessDB db = AccessDB.getInstance();
    
    /**
     * Creates a new instance of ForgotPasswordBean
     */
    public ForgotPasswordBean() {
    }

    public String submit() {
        
        List<String> res = db.query("select email from user where email = '"+email+"'");
        
        if(!res.isEmpty()) {
            String newPassword = Utils.generateRandomString(12);
            
            db.update("update user set password = '"+ShaPWEncryption.encrypt(newPassword)+"' where "
                +"email = '"+email+"';");
            
            Email.email(email, "WheeReader - Password Reset Request", 
                    "Hi there!<br><br>"
                    + "You have a new password:<br><br>"
                    + "<strong>"+newPassword+"</strong><br><br>"
                    + "It is recommended that you change your password after logging in with this one.");

        }
        else {
            email = null;
            response = "That email address is not registered with our service.";
        }
        
        return "forgotpassword";
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
     * @return the response
     */
    public String getResponse() {
        return response;
    }
    
    
}
