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
            conn = DriverManager.getConnection("jdbc:mysql://localhost/team1",
                    "scott", "tiger");
            
            
            // Make sure database is up-to-date
            
            // create user table
            update("create table if not exists `user` (\n" +
                "  `username` varchar(20) NOT NULL,\n" +
                "  `password` varchar(64) DEFAULT NULL,\n" +
                "  `firstname` varchar(20) DEFAULT NULL,\n" +
                "  `lastname` varchar(20) DEFAULT NULL,\n" +
                "  `mi` varchar(1) DEFAULT NULL,\n" +
                "  `email` varchar(30) DEFAULT NULL,\n" +
                "  `phone` varchar(20) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`username`),\n" +
                "  UNIQUE KEY `username` (`username`)\n" +
                ");");


            update("create table if not exists `accesscodes` (\n" +
                "  `code` varchar(100) NOT NULL DEFAULT '',\n" +
                "  `status` enum('A','I') DEFAULT NULL,\n" +
                "  PRIMARY KEY (`code`),\n" +
                "  UNIQUE KEY `code` (`code`)\n" +
                ");");
                        
            update("create table if not exists `usercodes` (\n" +
                "  `username` varchar(20) NOT NULL DEFAULT '',\n" +
                "  `code` int(11) NOT NULL DEFAULT '0',\n" +
                "  `maxcodes` int(11) DEFAULT NULL,\n" +
                "  `remaining` int(11) DEFAULT NULL,\n" +
                "  `startDate` date DEFAULT NULL,\n" +
                "  `endDate` date DEFAULT NULL,\n" +
                "  PRIMARY KEY (`username`,`code`)\n" +
                ");");


            update("create table if not exists `quizanswers` (\n" +
                "  `chapter` int(11) NOT NULL,\n" +
                "  `number` int(11) NOT NULL,\n" +
                "  `answer` int(11) NOT NULL,\n" +
                "  `answertext` text,\n" +
                "  `iscorrect` tinyint(1) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`chapter`,`number`,`answer`)\n" +
                ");");
            
            update("create table if not exists `quizquestions` (\n" +
                "  `chapter` int(11) NOT NULL,\n" +
                "  `section` int(11) NOT NULL,\n" +
                "  `number` int(11) NOT NULL,\n" +
                "  `questiontext` text,\n" +
                "  `multiplechoice` tinyint(1) DEFAULT '0',\n" +
                "  PRIMARY KEY (`chapter`,`section`,`number`),\n" +
                "  KEY `question_number` (`number`)\n" +
                ");");
            
            update("create table if not exists `useranswers` (\n" +
                "  `username` varchar(20) NOT NULL,\n" +
                "  `chapter` int(11) NOT NULL,\n" +
                "  `number` int(11) NOT NULL,\n" +
                "  `correct` tinyint(1) DEFAULT '-1',\n" +
                "  `lastanswers` varchar(20) DEFAULT '',\n" +
                "  PRIMARY KEY (`chapter`,`number`,`username`)\n" +
                ");");
            
            
            // Check if accesscodes table is empty, and insert code if needed
            if(query("select * from accesscodes;").isEmpty()) {
                update("insert into accesscodes(code, status) values(12245, 'A');");
            }
            
            // Check if quizquestions table is empty, and if so, populate it 
            // and the quizanswers table
            if(query("select * from quizquestions;").isEmpty()) {
                update("insert into quizquestions values "
                    + "(5,2,1,'<p>How many times will the following code print \\\"Welcome to Java\\\"?</p>\\n\\n<pre class=\\\"javaCode\\\">int count = 0;\\nwhile (count < 10) {\\n  System.out.println(\\\"Welcome to Java\\\");\\n  count++;\\n}\\n</pre>',0),"
                    + "(5,2,2,'<p>Analyze the following code.</p>\\n\\n<pre class=\\\"javaCode\\\">int count = 0;\\nwhile (count < 100) {\\n  // Point A\\n  System.out.println(\\\"Welcome to Java!\\\");\\n  count++;\\n  // Point B\\n}\\n\\n  // Point C\\n</pre>',1),"
                    + "(5,2,3,'<p>How many times will the following code print \\\"Welcome to Java\\\"?</p>\\n\\n<pre class=\\\"javaCode\\\">int count = 0;\\nwhile (count++ < 10) {\\n  System.out.println(\\\"Welcome to Java\\\");\\n}\\n</pre>',0),"
                    + "(5,3,4,'<p>How many times will the following code print \\\"Welcome to Java\\\"?</p>\\n\\n<pre class=\\\"javaCode\\\">int count = 0;\\ndo {\\n  System.out.println(\\\"Welcome to Java\\\");\\n  count++;\\n} while (count < 10);\\n</pre>',0),"
                    + "(5,3,5,'<p>How many times will the following code print \\\"Welcome to Java\\\"?</p>\\n\\n<pre class=\\\"javaCode\\\">int count = 0;\\ndo {\\n  System.out.println(\\\"Welcome to Java\\\");\\n} while (count++ < 10);\\n</pre>',0),"
                    + "(5,3,6,'<p>How many times will the following code print \\\"Welcome to Java\\\"?</p>\\n\\n<pre class=\\\"javaCode\\\">int count = 0;\\ndo {\\n  System.out.println(\\\"Welcome to Java\\\");\\n} while (++count < 10);\\n</pre>',0),"
                    + "(5,3,7,'<p>What is the value in count after the following loop is executed?</p>\\n\\n<pre class=\\\"javaCode\\\">int count = 0;\\ndo {\\n  System.out.println(\\\"Welcome to Java\\\");\\n} while (count++ < 9);\\nSystem.out.println(count);\\n</pre>',0);");
            
                update("insert into quizanswers values "
                    + "(5,1,0,'8',0),(5,1,1,'9',0),(5,1,2,'10',1),(5,1,3,'11',0),(5,1,4,'0',0),"
                    + "(5,2,0,'count < 100 is always true at Point A',1),(5,2,1,'count < 100 is always true at Point B',0),(5,2,2,'count < 100 is always false at Point B',0),(5,2,3,'count < 100 is always true at Point C',0),(5,2,4,'count < 100 is always false at Point C',1),"
                    + "(5,3,0,'8',0),(5,3,1,'9',0),(5,3,2,'10',1),(5,3,3,'11',0),(5,3,4,'0',0),"
                    + "(5,4,0,'8',0),(5,4,1,'9',0),(5,4,2,'10',1),(5,4,3,'11',0),(5,4,4,'0',0),"
                    + "(5,5,0,'8',0),(5,5,1,'9',0),(5,5,2,'10',0),(5,5,3,'11',1),(5,5,4,'0',0),"
                    + "(5,6,0,'8',0),(5,6,1,'9',0),(5,6,2,'10',1),(5,6,3,'11',0),(5,6,4,'0',0),"
                    + "(5,7,0,'8',0),(5,7,1,'9',0),(5,7,2,'10',1),(5,7,3,'11',0),(5,7,4,'0',0);");
            }
            
            
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
        
        //System.out.println("SQL: "+st);
        
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