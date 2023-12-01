import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        SQLConnection connection = new SQLConnection();
        connection.getDatabaseConnection();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("java-fx.fxml"));
       primaryStage.setTitle("Hello world");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    }
}
