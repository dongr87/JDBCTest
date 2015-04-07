import java.sql.*;  //import all the JDBC classes

public class CreateCS650Data {
    static String[] SQL = {
        "create table CS650Data ("+
            "programmer varchar (32),"+
            "day varchar (3),"+
            "pizzas integer)",
        "insert into CS650Data values ('George', 'Mon', 2)",
        "insert into CS650Data values ('Walter', 'Mon', 3)",
        "insert into CS650Data values ('Edward', 'Tue', 9)",
        "insert into CS650Data values ('Walter', 'Tue', 3)",
        "insert into CS650Data values ('Egbert', 'Tue', 4)",
        "insert into CS650Data values ('Jillian', 'Wed', 3)",
        "insert into CS650Data values ('Egbert', 'Thu', 4)",
        "insert into CS650Data values ('George', 'Thu', 2)",
        "insert into CS650Data values ('Clarence', 'Fri', 10)",
        "insert into CS650Data values ('Edward', 'Fri', 4)",
        "insert into CS650Data values ('Jillian', 'Fri', 5)",
    };

    public static void main(String[] args) {
      // PUT your username and password below before running javac and java
        String user = "c_wan1_dong";
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
            // execute SQL commands to create table, insert data
            for (int i=0; i<SQL.length; i++) {
                stmt.execute(SQL[i]);
            }
            con.close();
        } catch (Exception e) {
            System.err.println("problems with SQL sent to JDBC"+
                ": "+e.getMessage());
        }
    }
}