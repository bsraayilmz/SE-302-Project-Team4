import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public CoursesModel model = new CoursesModel();
    @FXML
    Pane displayVersionsPage, newCoursePage, updateCoursePage, userManualPage, homePage, saveFeaturesPage;

    @FXML
    Button newCourseButton;

    public void clickNC(){
        newCourseButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(true);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            saveFeaturesPage.setVisible(false);
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
            saveFeaturesPage.setVisible(false);
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
            saveFeaturesPage.setVisible(false);
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
            saveFeaturesPage.setVisible(false);
            userManualPage.setVisible(true);
        });
    }

    @FXML
    Button saveFeaturesButton;

    public void clickSF(){
        saveFeaturesButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCoursePage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            saveFeaturesPage.setVisible(true);
            userManualPage.setVisible(false);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (model.isDatabaseConnected()) {
            System.out.println("The database connection has been provided.");

        } else {
            System.out.println("The connection is failed.");
        }
    }}


