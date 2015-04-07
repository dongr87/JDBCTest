import java.sql.*;

public class CS650MetaData {
    public static void main (String args[]) {
        String user = "C_WAN1_DONG";
        String pass = "S03430083";

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
            ResultSet result = stmt.executeQuery(
                "SELECT * FROM CS650Data ORDER BY pizzas DESC");
            ResultSetMetaData meta = result.getMetaData();

            int numbers = 0;
            int columns = meta.getColumnCount();
            for (int i=1;i<=columns;i++) {
                System.out.println (meta.getColumnLabel(i) + "\t"
                              + meta.getColumnTypeName(i) + "\t"
                              + meta.getColumnDisplaySize(i)
);
   //             if (meta.isSigned(i)) { // is it a signed number?
   //                 numbers++;
   //             }
            }
            System.out.println ("Columns: " + columns);

            con.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
}