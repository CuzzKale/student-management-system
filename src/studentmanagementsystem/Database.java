package studentmanagementsystem;
import java.sql.*;
import org.h2.tools.Server;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
   private static final String URL = "jdbc:h2:tcp://localhost/~/studentManagement";
    private static final String USER = "sa";
    private static final String PASS = "";
    private static Server server;
    public static void startConnection(){
        try{
       if (server == null){
       server = Server.createTcpServer("-tcpAllowOthers").start();
       System.out.println("H2 server started at: " + server.getURL());
        }
       
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
        public static Connection continueConnection() throws SQLException{
            return DriverManager.getConnection(URL, USER, PASS);
    }
    
}
