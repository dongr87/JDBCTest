import java.io.*;
import java.sql.*;


public class Assign4Q1 {
    public static void main (String args[]) 
        throws SQLException, IOException{
  
      String user = "C_WAN1_DONG";
        String pass = "S03430083";
        
        
        String year = readEntry("please enter a year: ");
        
        try {
               Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            System.out.println("Failed to load JDBC driver.");
            return;
        }

        Statement stmt = null;
        Connection con=null;
        
        try {
            con = DriverManager.getConnection (
"jdbc:oracle:thin:"+user+"/"+pass+"@oraclass2.bentley.edu:1521:cisdb"
);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.err.println("problems connecting to JDBC");
        }

        try {
        	System.out.println( "              Year: " + year);
            ResultSet rset = stmt.executeQuery 
                    ("select row_number() over (order by qty desc) as rank, p.pno, p.pname, qty "+
                        "from parts p, odetails od, orders o "+
                    "where '20'||substr(to_char(o.received),8,2) like '"+ year +"' and "+
                         "od.ono = o.ono and "+
                          "od.pno = p.pno and " +
                         "rownum <= 5");
            
            System.out.println("RANK PNO   Part Name            Quantity Ordered");
            System.out.println("------------------------------------------------");
            
             while (rset.next ()) {
               System.out.println(rset.getString(1) + "  " +
                                  rset.getString(2) + "  " +
                                  String.format("%-20s", rset.getString(3)) + "  " +
                                  rset.getInt(4));
            }
            System.out.println("------------------------------------------------");
          
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
