// org.apache.tomcat.jdbc.pool.DataSource;
//import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class ConnectionManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydiplom";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private ConnectionManager() {}


    public static Connection getConnection(){
        Connection con = null;
        try {
           // Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        return con;
    }
}
