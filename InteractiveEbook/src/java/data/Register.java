package data;

import utils.ShaPWEncryption;
import beans.UserBean;
import utils.Email;

/*
 * Author: Jacob
 * Revised by: Justin
 */
public class Register {

    public static boolean register(UserBean user) {
        AccessDB db = AccessDB.getInstance();
        /**
         * Check if user exists, if not, add user's info to the User table.
         */
        String s = db.query("select * from user where username = '" + user.getUserName() + "';");
        if (s.equals("")) {
            s = ShaPWEncryption.encrypt(user.getPassword());
            db.update("insert into user values('" + user.getUserName()
                    + "', '" + s
                    + "', '" + user.getFirstName()
                    + "', '" + user.getLastName()
                    + "', '" + user.getMiddle()
                    + "', '" + user.getEmail()
                    + "', '" + user.getPhone()
                    + "');");

            Email.email(user.getEmail(), "WheeReader Registration", "You are now registered for WheeReader! Congrats!");
        }
        else {
            return false;
        }
        
        Populate.populate(user.getUserName());
        //}
        return true;
    }
}
