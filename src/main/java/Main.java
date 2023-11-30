import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Example");

        Button btn = new Button("Click me!");
        btn.setOnAction(e -> System.out.println("Hello, JavaFX!"));

        Scene scene = new Scene(btn, 300, 200);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
