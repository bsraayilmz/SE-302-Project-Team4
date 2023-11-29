import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SQLConnection{
    // db parameters
    Connection conn = null;
    String url = "jdbc:sqlite:src/main/resources/DatabaseConnection.db";
    public void getDatabaseConnection(){
        // create a connection to the database, if there is a problem about connection etc. "try catch"
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        }
        catch (SQLException e){
            System.out.println("null");
            System.out.println(e.getMessage());
        }

        //to close the database connection
        try{
            if(conn != null){
                conn.close();
                System.out.println("Connection terminated.");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
