package data;

public class Populate{
    static AccessDB db = AccessDB.getInstance();
    public static void populate(String user){
        String s = db.query("select problem from answers");
        String[] stArr = s.replaceAll(" ", "").split("#!");
        for(String st : stArr){
            System.out.println(st);
            //u for unanswered
            db.update("insert into answered values('" + user + "', '" + st + "', 'u');");
        }
    }
}