import java.sql.*;

public class CS650Report {
    public static void main (String args[]) {
        String user = "C_wan1_dong";
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
                "SELECT programmer, pizzas FROM CS650Data ORDER BY pizzas DESC");
            result.next();  // move to first row
            String name = result.getString("programmer");
            int pizzas = result.getInt("pizzas");
            System.out.println("Programmer "+name+
                " consumed the most pizza: "+pizzas+" pizzas.");

            result = stmt.executeQuery(
                "SELECT pizzas FROM CS650Data");

            // for each row of data
            pizzas = 0;
            while(result.next()) {
                pizzas += result.getInt("pizzas");
            }
            System.out.println("Total sales of "+pizzas+" pizzas.");

            con.close();
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
}