import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public CoursesModel model = new CoursesModel();
    @FXML
    Pane displayVersionsPage, newCoursePage, updateCoursePage, userManualPage, homePage;

    @FXML

    ComboBox<String> documentLang;
    String English = "English";
    String Turkish = "Turkish";
    ObservableList<String> documentLanguageList = FXCollections.observableArrayList(English, Turkish);

    @FXML
    Button newCourseButton;

    public void clickDocumentLangCB(){
        documentLang.getItems().addAll(documentLanguageList);
    }

    public void clickNC(){
        newCourseButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(true);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            userManualPage.setVisible(false);
        });
    }

    @FXML
    Button updateCourseButton;
    public void clickUC(){
        updateCourseButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(false);
            updateCoursePage.setVisible(true);
            displayVersionsPage.setVisible(false);
            userManualPage.setVisible(false);
        });
    }

    @FXML
    Button displayVersionsButton;

    public void clickDV(){
        displayVersionsButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(true);
            userManualPage.setVisible(false);
        });
    }

    @FXML
    Button userManualButton;

    public void clickUM(){
        userManualButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            userManualPage.setVisible(true);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (model.isDatabaseConnected()) {
            System.out.println("The database connection has been provided.");

        } else {
            System.out.println("The connection is failed.");
        }
        clickDocumentLangCB();
    }

}


