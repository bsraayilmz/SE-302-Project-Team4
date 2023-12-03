import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public CoursesModel model = new CoursesModel();
    @FXML
    Pane displayVersionsPage, newCoursePage, updateCoursePage, userManualPage, homePage, saveFeaturesPage;

    @FXML
    Button newCourseButton;

    @FXML
    ComboBox<String> documentLanguageBox;
    @FXML
    ComboBox<String> courseTermBox;
    @FXML
    ComboBox<String> courseLanguageBox;
    @FXML
    ComboBox<String> courseTypeBox;
    @FXML
    ComboBox<String> courseLevelBox;
    @FXML
    ComboBox<String> courseDeliveryBox;

    ObservableList<String> documentLanguageList = FXCollections.observableArrayList("Turkish", "English");
    ObservableList<String> courseTermList = FXCollections.observableArrayList("Fall", "Spring");
    ObservableList<String> courseLanguageList = FXCollections.observableArrayList("Turkish", "English", "Second Foreign Language");
    ObservableList<String> courseTypeList = FXCollections.observableArrayList("Required", "Elective");
    ObservableList<String> courseLeveList = FXCollections.observableArrayList("Short Cycle", "First Cycle",
                                                                                   "Second Cycle", "Third Cycle");
    ObservableList<String> courseDeliveryList = FXCollections.observableArrayList("Face-to-Face", "Online", "Blended");


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
        initializeComboBoxes();
    }
    public void initializeComboBoxes(){
        documentLanguageBox.getItems().addAll(documentLanguageList);
        courseLanguageBox.getItems().addAll(courseLanguageList);
        courseTypeBox.getItems().addAll(courseTypeList);
        courseLevelBox.getItems().addAll(courseLeveList);
        courseTermBox.getItems().addAll(courseTermList);
        courseDeliveryBox.getItems().addAll(courseDeliveryList);

    }
}


