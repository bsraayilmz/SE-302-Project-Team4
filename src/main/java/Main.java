import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("JavaFxController.fxml"));
        primaryStage.getIcons().add(new Image("pngwing.com.png"));
        primaryStage.setTitle("Syllabus System");
       primaryStage.setScene(new Scene(root));
       primaryStage.show();
    }
}
