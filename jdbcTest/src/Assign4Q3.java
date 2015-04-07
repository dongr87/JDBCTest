import java.io.*;
import java.sql.*;


public class Assign4Q3 {
    public static void main (String args[]) 
    		throws SQLException, IOException{
  
    	  String user = "C_WAN1_DONG";
        String pass = "S03430083";
        String zip = readEntry("please enter a zipcode: ");
        
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
            
            ResultSet rset = stmt.executeQuery 
                    ("select cno id, cname from customers where zip = " + zip
                        + "union "
                    + "select eno id, ename from employees where zip = " + zip);
            
            System.out.println( "ID     Name");
             while (rset.next ()) {
               System.out.println(rset.getString(1) + "  " +
                                  rset.getString(2) );
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
