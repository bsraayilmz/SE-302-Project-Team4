import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
//        project.SQLConnection connection = new project.SQLConnection();
//        connection.getDatabaseConnection();
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("JavaFxController.fxml"));
       primaryStage.setTitle("Syllabus System");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    }


}
