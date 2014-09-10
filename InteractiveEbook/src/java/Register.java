
public class Register {

    public static boolean register(int code, String username, String password) {
        AccessDB db = AccessDB.getInstance();
        //String st = db.query("select * from accesscode where code = " + code + ";");
        //if(st.isEmpty()){
        //  return false;
        //}
        //else{
        //db.Update("delete from accesscode where code = " + code + ";");
        String s = db.query("select * from users where username = '" + username + "';");
        if(s.equals("")){
        s = ShaPWEncryption.encrypt(password);
        db.update("insert into users values('" + username + "', '" + s + "');");
        }
        //}
        return true;
    }
}
