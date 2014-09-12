package data;

public class AddAccessCode{
    public static void add(){
        AccessDB adb = AccessDB.getInstance();
        String s = "sentinel";
        int code = 0;
        while(!s.isEmpty()){
        code = (int)(Math.random()*1000000);  
        s = adb.query("select * from accessCode where code = " + code + ";");
        }
        adb.update("insert into accessCode values(" + code + ");");
    }
}