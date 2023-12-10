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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public CoursesModel model = new CoursesModel();
    public courseInfoController courseInfoController = new courseInfoController(this);
    public ObservableList<SemesterActivityRow> semesterActivities;
    @FXML
    public TableView<SemesterActivityRow> tableView;
    @FXML
    public TableColumn<SemesterActivityRow, String> semesterActivitiesColumn;
    @FXML
    public TableColumn<SemesterActivityRow, String> numberColumn;
    @FXML
    public TableColumn<SemesterActivityRow, String> weightingColumn;
    @FXML
    public TableColumn<SemesterActivityRow, String> l01Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l02Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l03Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l04Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l05Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l06Column;
    @FXML
    public TableColumn<SemesterActivityRow, String> l07Column;
    //to be able to refresh the all information
    ObservableList<courseInfo> courseList = FXCollections.observableArrayList();
    String queryCourseInfo = null;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    //text fields are for courseInfo table
    @FXML
    TextField courseNameField, courseCodeField, theoryTimeField, labTimeField, prerequisitesField, courseCreditsField, courseECTSField, teachingMethodsField, objectivesField, outcomesField, descriptionField;
    //radio buttons are for courseInfo table
    @FXML
    Label courseNameLabel;
    @FXML
    RadioButton courseFallSemButton, courseSpringSemButton;
    //checkbox are for courseInfo table
    @FXML
    CheckBox turkishLangButton, englishLangButton, secondLangButton, thirdCycleButton, requiredTypeButton, electiveTypeButton, shortCycleButton,firstCycleButton,coreCategoryButton,secondCycleButton, f2fDel, onlineDel, blendedDel,majorAreaCategoryButton, supportiveCategoryButton, commCategoryButton, transferableCategoryButton;
    //the pages for buttons
    @FXML
    Pane displayVersionsPage, newCourseENPage, updateCoursePage, userManualPage, homePage, newCourseTRPage;
    @FXML
    TableView<courseInfo> courseInfoTable;
    //for the courseInfo table
    @FXML
    TableColumn<courseInfo, String> courseCodeCol, nameCol, prerequisitesCol, teachingMethodsCol, objectivesCol, outcomesCol, descriptionCol ;
    @FXML
    TableColumn<courseInfo, Integer> semesterCol, theoryCol, labCol, creditsCol, ectsCol, languageCol, typeCol, categoryCol;
    @FXML
    Button refreshButton, newCourseButton;
    //entering for the newCoursePage
    String English = "English";
    String Turkish = "Turkish";
    String selectedLanguage;
    @FXML
    Button updateCourseButton;
    @FXML
    Button displayVersionsButton;
    @FXML
    Button userManualButton;
    String queryAddition = null;
    PreparedStatement preparedStatementAddition = null;
    //To store the output values of checkboxes
    int semesterValue;
    int languageValue;
    int courseType;
    int categoryType;
    String cName, cCode, semester, languageS, prerequisites, type, teachingMethods, objectives, outcomes, description, category;
    int theoryTime, labTime, credit, courseECTS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (model.isDatabaseConnected()) {
            System.out.println("The database connection has been provided.");

        } else {
            System.out.println("The connection is failed.");
        }
        tableViewInitializer();
        courseInfoController.loadData();
    }

    public void clickNC() {
        newCourseButton.setOnAction(event -> {
            showLanguageSelectionDialog();
        });
    }

    //selection of document language
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
                } else if (selectedLanguage != null && !selectedLanguage.isEmpty() && selectedLanguage.equals(Turkish)) {
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

    public void clickUC() {
        updateCourseButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            updateCoursePage.setVisible(true);
            displayVersionsPage.setVisible(false);
            newCourseTRPage.setVisible(false);
            userManualPage.setVisible(false);
        });
    }

    public void clickDV() {
        displayVersionsButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(true);
            userManualPage.setVisible(false);
            newCourseTRPage.setVisible(false);
        });
    }

    public void clickUM() {
        userManualButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            updateCoursePage.setVisible(false);
            displayVersionsPage.setVisible(false);
            userManualPage.setVisible(true);
            newCourseTRPage.setVisible(false);
        });
    }

    //for the courseInfo table

    @FXML
    void refreshTable() {
        //to refresh the tables in the database
        try {
            courseList.clear();
            queryCourseInfo = "SELECT * FROM 'CourseInfo'";
            //to prepare the SQLite query
            preparedStatement = connection.prepareStatement(queryCourseInfo);
            //to run the sqlite query above
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //to create a new courseInfo object according to the data by the user
                courseList.add(new courseInfo(resultSet.getString("courseID"),
                        resultSet.getString("CourseName"),
                        resultSet.getInt("CourseSemester"),
                        resultSet.getInt("TheoryTime"),
                        resultSet.getInt("LabTime"),
                        resultSet.getInt("CourseCredit"),
                        resultSet.getInt("CourseECTS"),
                        resultSet.getString("Prerequisites"),
                        resultSet.getInt("CourseLanguage"),
                        resultSet.getInt("CourseType"),
                        resultSet.getString("TeachingMethods"),
                        resultSet.getString("CourseObj"),
                        resultSet.getString("Outcomes"),
                        resultSet.getString("CourseDesc"),
                        resultSet.getInt("CourseCategory")
                ));
                //To update the elements of the courseInfoTable with the data received from courseList.
                courseInfoTable.setItems(courseList);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public String handleCourseFallSemButton() {
        if (courseFallSemButton.isSelected()) {
            semesterValue = 1;
            System.out.println("Clicked fall semester button.");
        }
        return "Fall";
    }

    @FXML
    public String handleCourseSpringSemButton() {
        if (courseSpringSemButton.isSelected()) {
            semesterValue = 2;
            System.out.println("Clicked spring semester button.");
        }
        return "Spring";
    }

    @FXML
    public String handleTurkishButton() {
        if (turkishLangButton.isSelected()) {
            languageValue = 1;
            System.out.println("Clicked Turkish language button.");
        }
        return "Turkish";
    }

    @FXML
    public String handleEnglishButton() {
        if (englishLangButton.isSelected()) {
            languageValue = 2;
            System.out.println("Clicked English language button.");
        }
        return "English";
    }

    @FXML
    public String handleSecondLButton() {
        if (secondLangButton.isSelected()) {
            languageValue = 3;
            System.out.println("Clicked Second language button.");
        }
        return "Second Foreign Language";
    }

    @FXML
    public String handleRequiredButton() {
        if (requiredTypeButton.isSelected()) {
            courseType = 1;
            System.out.println("Clicked required course type button.");
        }
        return "Required";
    }

    @FXML
    public String handleElectiveButton() {
        if (electiveTypeButton.isSelected()) {
            courseType = 2;
            System.out.println("Clicked elective course type button.");
        }
        return "Elective";
    }

    @FXML
    public String handleCoreButton() {
        if (coreCategoryButton.isSelected()) {
            categoryType = 1;
            System.out.println("Clicked Core Course button.");
        }
        return "Core Course";
    }

    @FXML
    public String handleMajorButton() {
        if (majorAreaCategoryButton.isSelected()) {
            categoryType = 2;
            System.out.println("Clicked Major Area Course button.");
        }
        return "Major Area Course";
    }

    @FXML
    public String handleSupportiveButton() {
        if (supportiveCategoryButton.isSelected()) {
            categoryType = 3;
            System.out.println("Clicked Supportive Course button.");
        }
        return "Supportive Course";
    }

    @FXML
    public String handleCommButton() {
        if (commCategoryButton.isSelected()) {
            categoryType = 4;
            System.out.println("Clicked Communication and Management Skills Course button.");
        }
        return "Communication and Management Skills Course";
    }

    @FXML
    public String handleTransferableButton() {
        if (transferableCategoryButton.isSelected()) {
            categoryType = 5;
            System.out.println("Clicked Transferable Skill Course button.");
        }
        return "Transferable Skill Course";
    }
//


    @FXML
    public void saveENG() {
        connection = SQLConnection.Connector();
        cName = courseNameField.getText();
        cCode = courseCodeField.getText();
        semester = null;
        languageS = null;
        prerequisites = prerequisitesField.getText();
        type = null;
        teachingMethods = teachingMethodsField.getText();
        objectives = objectivesField.getText();
        outcomes = outcomesField.getText();
        description = descriptionField.getText();
        category = null;

        if (semesterValue == 1) {
            semester = handleCourseFallSemButton();
        } else if (semesterValue == 2) {
            semester = handleCourseSpringSemButton();
        }
        if (languageValue == 1) {
            languageS = handleTurkishButton();
        } else if (languageValue == 2) {
            languageS = handleEnglishButton();
        } else if (languageValue == 3) {
            languageS = handleSecondLButton();
        }
        if (courseType == 1) {
            type = handleRequiredButton();
        } else if (courseType == 2) {
            type = handleElectiveButton();
        }
        if (categoryType == 1) {
            category = handleCoreButton();
        } else if (categoryType == 2) {
            category = handleMajorButton();
        } else if (categoryType == 3) {
            category = handleSupportiveButton();
        } else if (categoryType == 4) {
            category = handleCommButton();
        } else if (categoryType == 5) {
            category = handleTransferableButton();
        }

        if (courseInfoController.controlBlank()) {
            try{
                theoryTime = Integer.parseInt(theoryTimeField.getText());
                labTime = Integer.parseInt(labTimeField.getText());
                credit = Integer.parseInt(courseCreditsField.getText());
                courseECTS = Integer.parseInt(courseECTSField.getText());
            }catch (NumberFormatException e) {
                Alert notBlank = new Alert(Alert.AlertType.WARNING);
                notBlank.setTitle("Value Selection");
                notBlank.setHeaderText("The theory time, lab time, credit and ECTS must be integer.");
                notBlank.initModality(Modality.APPLICATION_MODAL);
                notBlank.showAndWait();
            }

            courseInfoController.getQuery(cName, cCode, semester, theoryTime, labTime, credit, courseECTS, prerequisites, languageS, type, teachingMethods, objectives, outcomes, description, category);
            courseInfoController.insertTable();
        }
    }

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

    public void tableViewInitializer() {
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

    //Methods to be able to edit the corresponding columns' cells, i.e., number column's first cell.
    public void changeNumberCellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setNumberColumn(editedCell.getNewValue().toString());
    }

    public void changeWeightCellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setWeightingColumn(editedCell.getNewValue().toString());
    }

    public void changeL01CellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL01Column(editedCell.getNewValue().toString());
    }

    public void changeL02CellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL02Column(editedCell.getNewValue().toString());
    }

    public void changeL03CellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL03Column(editedCell.getNewValue().toString());
    }

    public void changeL04CellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL04Column(editedCell.getNewValue().toString());
    }

    public void changeL05CellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL05Column(editedCell.getNewValue().toString());
    }

    public void changeL06CellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL06Column(editedCell.getNewValue().toString());
    }

    public void changeL07CellEvent(TableColumn.CellEditEvent editedCell) {
        SemesterActivityRow selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL07Column(editedCell.getNewValue().toString());
    }
}


