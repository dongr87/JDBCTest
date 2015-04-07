import java.sql.*;
import java.io.*;

class SQLMeta {
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
                 "jdbc:oracle:thin:"+user+"/"+pass+"@oraclass2.bentley.edu:1521:cisdb");
      
      System.out.println("Welcome to the CS650 SQL Interpreter\n");
      System.out.print("SQL> ");
      Statement stmt = conn.createStatement ();

      do {
        String query = readQuery();
        if (query .equals("exit"))
           break;
        ResultSet rset;
        try {
          rset = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("Not well formed query");
            continue;
          }
        ResultSetMetaData rsetmd = rset.getMetaData();
        int nCols;
        nCols = rsetmd.getColumnCount();
        for (int i = 1; i <= nCols; i++) {
           System.out.print(rsetmd.getColumnName(i));
           int colSize = rsetmd.getColumnDisplaySize(i);
           for (int k=0;
                k < colSize-rsetmd.getColumnName(i).length();
                k++)
           System.out.print(" ");
        }
        System.out.println("");
        while (rset.next ()) {
          for (int i = 1; i <= nCols; i++) {
             String val = rset.getString(i);
             if (rset.wasNull())
                System.out.print("null");
             else
                System.out.print(rset.getString(i));
             int colSize;
             if (rset.wasNull()) colSize = 4;
             else colSize = rsetmd.getColumnDisplaySize(i);
             if (rset.wasNull()) {
               for (int k = 0; k < colSize-4; k++)
                 System.out.print(" ");
             }
             else {
               for (int k=0;
                    k < colSize-rset.getString(i).length();
                    k++)
               System.out.print(" ");
             }
          }
          System.out.println("");
        }
      } while (true);
      stmt.close();
      conn.close();
      System.out.println("Thank you for using the CS650" +
                         " SQL Interpreter\n");
   }
   //readQuery function
   static String readQuery() {
      try {
         StringBuffer buffer = new StringBuffer();
         System.out.flush();
         int c = System.in.read();
         while(c != ';' && c != -1) {
            if (c != '\n')
               buffer.append((char)c);
            else {
               buffer.append(" ");
               System.out.print("SQL> ");
               System.out.flush();
            }
            c = System.in.read();
         }
         return buffer.toString().trim();
      } catch (IOException e) {
        return "";
        }
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