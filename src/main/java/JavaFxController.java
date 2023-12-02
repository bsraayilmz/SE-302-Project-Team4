import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JavaFxController implements Initializable {

    @FXML
    private TextField tfTitle;

    @FXML
    void btnnOKClickced(ActionEvent event) {
        Stage mainWindow = (Stage) tfTitle.getScene().getWindow();
        String title = tfTitle.getText();
        mainWindow.setTitle(title);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
