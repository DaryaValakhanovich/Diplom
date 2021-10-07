// org.apache.tomcat.jdbc.pool.DataSource;
//import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public final class ConnectionManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/diplom?autoReconnect=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static DataSource dataSource;

    static {
       // Properties Properties = new Properties();
       // Properties.setDriverClassName("com.mysql.cj.jdbc.Driver");
      //  Properties.setUrl(DB_URL);
       // Properties.setUsername(USER);
       // Properties.setPassword(PASSWORD);
       // dataSource = new DataSource(poolProperties);
    }

    private ConnectionManager() {}

    public static Connection getConnection() throws SQLException, ClassNotFoundException, NoSuchMethodException {
        Connection  conn= null;
        try{
            String url = "jdbc:mysql://localhost/diplom?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "root";
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(DB_URL, username, password);
            System.out.println("Connection to ProductDB succesfull!");
               // writer.println("Connection to ProductDB succesfull!");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return conn;
    }
}