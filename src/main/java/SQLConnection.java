import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SQLConnection{
    public static Connection Connector(){
        // db parameters
        Connection conn;
        String url = "jdbc:sqlite:src/main/resources/DatabaseConnection.db";
        // create a connection to the database, if there is a problem about connection etc. "try catch"
        try{
            conn = DriverManager.getConnection(url);
            return conn;
        }
        catch (SQLException e){
            System.out.println("null");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
