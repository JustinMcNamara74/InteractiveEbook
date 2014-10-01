package data;

import java.util.List;
import utils.ShaPWEncryption;

/**
 * 
 * @author Jacob
 */
public class Login{   
    public static boolean login(String username, String password){
        AccessDB db = AccessDB.getInstance();
        String s = ShaPWEncryption.encrypt(password);
        List<String> loge = db.query("select * from user where username = '" 
                + username + "' and password = '" + s + "';");
        return !loge.isEmpty();  
    }
}
