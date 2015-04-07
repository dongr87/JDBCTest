import java.io.*;
import java.sql.*;


public class Assign4Q2 {
    public static void main (String args[]) 
    		throws SQLException, IOException{
 
    	String user = "C_WAN1_DONG";
        String pass = "S03430083";
        String incrtZIP = readEntry("please enter the incorrect zipcode: "); 
        String crtZIP = readEntry("please enter the correct zipcode: "); 
        String[] SQL = {
            "insert into zipcodes values (" + crtZIP + ", (select city from zipcodes where zip = " + incrtZIP + "))",
            "update customers set zip = " + crtZIP + " where zip = " + incrtZIP, 
            "update employees set zip = " + crtZIP + " where zip = " + incrtZIP,
            "delete from zipcodes where zip = " + incrtZIP
        };
        
        try {
               Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println("Failed to load JDBC driver.");
            return;
        }

        Statement stmt = null;
        Connection con = null;
       
        try {
            con = DriverManager.getConnection (
"jdbc:oracle:thin:"+user+"/"+pass+"@oraclass2.bentley.edu:1521:cisdb"
);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.err.println("problems connecting to JDBC");
        }

        try {
           for (int i = 0; i<SQL.length; i++ ) {
            int upd = stmt.executeUpdate (SQL[i]);
           }  
          
            con.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    static String readEntry(String prompt) {
        try {
          StringBuffer buffer = new StringBuffer();
          System.out.print(prompt);
          System.out.flush();
          int c = System.in.read();
          while(c != '\n' && c != -1) {
            buffer.append((char)c);
            c = System.in.read();
          }
          return buffer.toString().trim();
        } catch (IOException e) {
          return "";
          }
     }
}
