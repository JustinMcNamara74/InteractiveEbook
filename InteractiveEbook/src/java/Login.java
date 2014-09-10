public class Login{   
    public static boolean login(String username, String password){
        AccessDB db = AccessDB.getInstance();
        String s = ShaPWEncryption.encrypt(password);
        String loge = db.query("select * from users where username = '" + username + "' and password = '" + s + "';");
        return !loge.equals("");      
    }
}