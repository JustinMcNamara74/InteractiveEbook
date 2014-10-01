package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccessDB {

    private Connection conn;
    private static AccessDB instance;
    public static final String DELIMETER = "#!BOOYAH!#";
    
    private AccessDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println("Couldn't load mysql driver");
        }

        try {
            // DO NOT EDIT THE FOLLOW 2 LINES (JACOB)
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

    public List<String> query(String st) {

        List<String> returnList = new ArrayList<>();
        
        System.out.println("SQL: "+st);
        
        try {
            PreparedStatement statement = conn.prepareStatement(st);
            ResultSet rs = statement.executeQuery();
            if (rs == null) {
                return returnList;
            }
            int columnCount = rs.getMetaData().getColumnCount();
            
            
            while (rs.next()) {
                StringBuilder rowString = new StringBuilder();
                
                for (int i = 1; i <= columnCount; i++) {
                    rowString.append(rs.getString(i));
                    rowString.append(DELIMETER);
                }
                //separates records
                returnList.add(rowString.toString());
            }
            //stuff
            rs.close();
            statement.close();
        } catch (SQLException se) {
            System.err.println("Error querying database: " + se.getMessage());
        }
        return returnList;
    }

    public boolean isValidCode(String code) {
        AccessDB db = AccessDB.getInstance();
        List<String> s = db.query("select code "
                + "from AccessCodes "
                + "where code = " + code
                + ";");
        
        System.out.println(s);
        return !s.isEmpty();

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