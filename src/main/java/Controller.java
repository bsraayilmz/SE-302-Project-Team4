import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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

    public ObservableList<SemesterActivityRow> semesterActivities;
    @FXML
    public TableView<SemesterActivityRow> tableView;
    @FXML
    public TableColumn<SemesterActivityRow, String> semesterActivitiesColumn;
    @FXML
    public TableColumn<SemesterActivityRow, String> numberColumn;
    @FXML
    public TableColumn<SemesterActivityRow, String> weightingColumn ;
    @FXML
    public TableColumn<SemesterActivityRow, String> l01Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l02Column ;
    @FXML
    public TableColumn<SemesterActivityRow, String> l03Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l04Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l05Column ;
    @FXML
    public TableColumn<SemesterActivityRow, String> l06Column ;
    @FXML
    public TableColumn<SemesterActivityRow, String> l07Column;


//    public ObservableList<SemesterActivityRow> semesterActivities2;

//    @FXML
//    public TableView<SemesterActivityRow> tableViews;

//    @FXML
//    public TableColumn<SemesterActivityRow, String> semesterActColumn;
//
//    @FXML
//    public TableColumn<SemesterActivityRow, String> numbColumn;
//
//    @FXML
//    public TableColumn<SemesterActivityRow, String> durationColumn;
//
//    @FXML
//    public TableColumn<SemesterActivityRow, String> workloadColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (model.isDatabaseConnected()) {
            System.out.println("The database connection has been provided.");

        } else {
            System.out.println("The connection is failed.");
        }

        semesterActivities = FXCollections.observableArrayList(
                new SemesterActivityRow("Participation"),
                new SemesterActivityRow("Laboratory/Application"),
                new SemesterActivityRow("Field Work"),
                new SemesterActivityRow("Quiz/Studio Critique"),
                new SemesterActivityRow("Homework/Assignment"),
                new SemesterActivityRow("Presentation/Jury"),
                new SemesterActivityRow("Project"),
                new SemesterActivityRow("Seminar/Workshop"),
                new SemesterActivityRow("Oral Exam"),
                new SemesterActivityRow("Midterm"),
                new SemesterActivityRow("Final Exam"),
                new SemesterActivityRow("Total")
        );

        semesterActivitiesColumn.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("semesterActivitiesColumn"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("numberColumn"));
        weightingColumn.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("weightingColumn"));
        l01Column.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("l01Column"));
        l02Column.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("l02Column"));
        l03Column.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("l03Column"));
        l04Column.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("l04Column"));
        l05Column.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("l05Column"));
        l06Column.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("l06Column"));
        l07Column.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("l07Column"));

        tableView.setItems(semesterActivities);

        // Düzenleme için TextFieldTableCell kullan

        numberColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        weightingColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        l01Column.setCellFactory(TextFieldTableCell.forTableColumn());
        l02Column.setCellFactory(TextFieldTableCell.forTableColumn());
        l03Column.setCellFactory(TextFieldTableCell.forTableColumn());
        l04Column.setCellFactory(TextFieldTableCell.forTableColumn());
        l05Column.setCellFactory(TextFieldTableCell.forTableColumn());
        l06Column.setCellFactory(TextFieldTableCell.forTableColumn());
        l07Column.setCellFactory(TextFieldTableCell.forTableColumn());


  /*      semesterActivities2 = FXCollections.observableArrayList(
                new SemesterActivityRow("Course Hours (Including exam week: 16 x total hours)"),
                new SemesterActivityRow("Laboratory/Application Hours (Including exam week: 16 x total hours)"),
                new SemesterActivityRow("Study Hours out of Class"),
                new SemesterActivityRow("Field Work"),
                new SemesterActivityRow("Quiz/Studio Critique"),
                new SemesterActivityRow("Homework/Assignment"),
                new SemesterActivityRow("Presentation/Jury"),
                new SemesterActivityRow("Project"),
                new SemesterActivityRow("Seminar/Workshop"),
                new SemesterActivityRow("Oral Exam"),
                new SemesterActivityRow("Midterm"),
                new SemesterActivityRow("Final Exam"),
                new SemesterActivityRow("Total")
        );

        semesterActivitiesColumn.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("semesterActivitiesColumn"));
        numbColumn.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("numbColumn"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("durationColumn"));
        workloadColumn.setCellValueFactory(new PropertyValueFactory<SemesterActivityRow, String>("workloadColumn"));


        tableViews.setItems(semesterActivities2);

        numbColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        workloadColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        semesterActivitiesColumn.setOnEditCommit(event -> {
            int rowIndex = event.getTablePosition().getRow();
            var newValue = event.getNewValue();
//           // Düzenleme işlemlerini burada gerçekleştir
            tableView.getItems().get(rowIndex).setSemesterActivitiesColumn(newValue);
        });*/

    }
}


