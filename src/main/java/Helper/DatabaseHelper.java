 
package Helper;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    public static Connection openConnection() throws ClassNotFoundException, SQLException{
        Connection con;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=AppQLSV;"
                    + "username=sa;password=123");
            
        return con;
    }
}
