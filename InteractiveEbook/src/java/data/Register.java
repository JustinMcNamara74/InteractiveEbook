package data;


import utils.ShaPWEncryption;
import beans.UserBean;


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
        String s = db.query("select * from users where username = '" + user.getUserName() + "';");
        if (s.equals("")) {
            s = ShaPWEncryption.encrypt(user.getPassword());
            db.update("insert into user values('" + user.getUserName()
                    + "', '" + s
                    + "', '" + user.getFirstName()
                    + "', '" + user.getLastName()
                    + "', '" + user.getMiddle()
                    + "', '" + user.getEmail()
                    + "');");

        }
        //}
        return true;
    }
}
