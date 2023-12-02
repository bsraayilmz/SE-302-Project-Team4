

import java.sql.Connection;
import java.sql.SQLException;

public class CoursesModel {
    Connection connection;
    public CoursesModel(){
        // to control the connection whether successful or not
        connection = SQLConnection.Connector();

        if(connection == null){
            System.exit(1);
        }
    }
    public boolean isDatabaseConnected(){
        try {
            //if the connection is not closed, db is connected.
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            //Otherwise, it returns false.
            return false;
        }

    }
}
