package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessDB {

    private Connection conn;
    private static AccessDB instance;

    private AccessDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Couldn't load mysql driver");
        }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://jayjayjayjay.ddns.net/ebook"
                    + "?user=jjjj&password=JjJj1234!@#$");
        } catch (SQLException se) {
            System.err.println("Couldn't connect to database");
        }
    }

    public static AccessDB getInstance() {
        if (instance == null) {
            instance = new AccessDB();
        }

        return instance;
    }

    public String query(String st) {

        StringBuilder returnString = new StringBuilder();
        try {
            PreparedStatement statement = conn.prepareStatement(st);
            ResultSet rs = statement.executeQuery();
            if (rs == null) {
                return "";
            }
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    returnString.append(rs.getString(i));
                    returnString.append(" ");
                }
                //separates records
                returnString.append("#!");
            }
            //stuff
            rs.close();
            statement.close();
        } catch (SQLException se) {
            System.err.println("Error querying database: " + se.getMessage());
        }
        return returnString.toString();
    }

    public boolean isValidCode(int code) {
        AccessDB db = AccessDB.getInstance();
        String s = db.query("select code "
                + "from AccessCodes "
                + "where code = " + code
                + ";");
        
        System.out.println(s);
            return !s.equals("");

        }


    public void update(String st) {
        try {
            PreparedStatement statement = conn.prepareStatement(st);
            statement.executeUpdate();
            //stuff
            statement.close();
        } catch (SQLException se) {
            System.err.println("Error updating database: " + se.getMessage());
        }

    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException se) {
            System.err.println("Error closing connection");
        }
    }
}