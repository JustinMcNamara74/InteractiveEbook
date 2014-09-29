package data;

public class CheckAnswer{
    private static AccessDB db = AccessDB.getInstance();
    
    public static boolean checkAnswer(String problem, String response){
        String s = db.query("select * from answers where problem ='" + problem + "' and answer = '" + response + "';");        
        return !s.equals("");
    }
    
    public static void setAnswer(char c, String user, String problem){
        db.update("update answered set rightwrong = '" + c + "' where"
                + " username = '" + user + "' and problem = '" + problem + "';"); 
    }
}