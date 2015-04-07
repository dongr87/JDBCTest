import java.sql.*;
import java.io.*;

class simple {
   public static void main (String args [])
       throws SQLException, IOException {

      try {
        Class.forName ("oracle.jdbc.driver.OracleDriver");
      } catch (ClassNotFoundException e) {
          System.out.println ("Could not load the driver");
        }
      String user, pass;
      user = readEntry("userid  : ");
      pass = readEntry("password: ");
      Connection conn = DriverManager.getConnection(
 "jdbc:oracle:thin:"+user+"/"+pass+"@oraclass2.bentley.edu:1521:cisdb"
);
      
      Statement stmt = conn.createStatement ();

      ResultSet rset = stmt.executeQuery 
             ("select eno,ename,zip,hdate from employees");
      while (rset.next ()) {
        System.out.println(rset.getString(1) + "  " +
                           rset.getString(2) + "  " +
                           rset.getString(3) + "  " +
                           rset.getString(4));
      }
      stmt.close();
      conn.close();

   }

   //readEntry function -- to read input string
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