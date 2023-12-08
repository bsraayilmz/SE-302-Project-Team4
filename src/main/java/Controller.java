import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public CoursesModel model = new CoursesModel();
    @FXML
    Pane displayVersionsPage, newCourseENPage, updateCoursePage, userManualPage, homePage, newCourseTRPage;

    @FXML

    String English = "English";
    String Turkish = "Turkish";
    String selectedLanguage;

    @FXML
    Button newCourseButton;

    public void clickNC(){
        newCourseButton.setOnAction(event -> {
            showLanguageSelectionDialog();
        });
    }

       private void showLanguageSelectionDialog() {
           Alert languageDialog = new Alert(Alert.AlertType.INFORMATION);
           languageDialog.setTitle("Language Selection");
           languageDialog.setHeaderText("Please select a language for the document.");
           languageDialog.initModality(Modality.APPLICATION_MODAL);

           ComboBox<String> selectLang = new ComboBox<>();
           selectLang.setPromptText("Document Language");
           selectLang.getItems().addAll(English, Turkish);

           VBox choiceBoxContainer = new VBox();
           selectLang.setPadding(new Insets(10, 20, 10, 20)); // Set padding (top, right, bottom, left)

           choiceBoxContainer.setAlignment(Pos.CENTER);
           choiceBoxContainer.getChildren().add(selectLang);
           languageDialog.getDialogPane().setContent(choiceBoxContainer);


           //to accept the language chosen
           ButtonType okButton = new ButtonType("OK");
           languageDialog.getButtonTypes().setAll(okButton);

           //to take the document language of the new course that will added.
           languageDialog.showAndWait().ifPresent(buttonType -> {
               if (buttonType == okButton) {
                   selectedLanguage = selectLang.getValue();
                   if (selectedLanguage != null && !selectedLanguage.isEmpty() && selectedLanguage.equals(English)) {
                       homePage.setVisible(false);
                       newCourseENPage.setVisible(true);
                       newCourseTRPage.setVisible(false);
                       updateCoursePage.setVisible(false);
                       displayVersionsPage.setVisible(false);
                       userManualPage.setVisible(false);
                   }else if(selectedLanguage != null && !selectedLanguage.isEmpty() && selectedLanguage.equals(Turkish)){
                       homePage.setVisible(false);
                       newCourseENPage.setVisible(false);
                       newCourseTRPage.setVisible(true);
                       updateCoursePage.setVisible(false);
                       displayVersionsPage.setVisible(false);
                       userManualPage.setVisible(false);
                   }
                   //if no selection
                   else {
                       Alert alert = new Alert(Alert.AlertType.WARNING);
                       alert.setTitle("Warning");
                       alert.setHeaderText(null);
                       alert.setContentText("Please select a language!");
                       alert.showAndWait();
                   }
               }
           });
       }


    @FXML
    Button updateCourseButton;
    public void clickUC(){
        updateCourseButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            updateCoursePage.setVisible(true);
            displayVersionsPage.setVisible(false);
            newCourseTRPage.setVisible(false);
            userManualPage.setVisible(false);
        });
    }

    @FXML
    Button displayVersionsButton;

    public void clickDV(){
        displayVersionsButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(true);
            userManualPage.setVisible(false);
            newCourseTRPage.setVisible(false);
        });
    }

    @FXML
    Button userManualButton;

    public void clickUM(){
        userManualButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            userManualPage.setVisible(true);
            newCourseTRPage.setVisible(false);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (model.isDatabaseConnected()) {
            System.out.println("The database connection has been provided.");

        } else {
            System.out.println("The connection is failed.");
        }
    }
}


