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
    /* Database controller variables */
    public CoursesModel model = new CoursesModel();
    public courseInfoController courseInfoController = new courseInfoController(this);

    /* Variables for Section 2: Week Table */
    public ObservableList<WeeklySubjects> weeklySubjects;
    @FXML
    public TableView<WeeklySubjects> weekTable;
    @FXML
    public TableColumn<WeeklySubjects,String> weekColumn;
    @FXML
    public TableColumn<WeeklySubjects,String> subjectColumn;
    @FXML
    public TableColumn<WeeklySubjects,String> reqColumn;

    /* Variables for Section 3: Assessment Table */
    public ObservableList<AssessmentSemAct> semesterActivities;
    @FXML
    public TableView<AssessmentSemAct> tableView;
    @FXML
    public TableColumn<AssessmentSemAct, String> semesterActivitiesColumn;
    @FXML
    public TableColumn<AssessmentSemAct, String> numberColumn;
    @FXML
    public TableColumn<AssessmentSemAct, String> weightingColumn ;
    @FXML
    public TableColumn<AssessmentSemAct, String> l01Column;
    @FXML
    public TableColumn<AssessmentSemAct, String> l02Column ;
    @FXML
    public TableColumn<AssessmentSemAct, String> l03Column;
    @FXML
    public TableColumn<AssessmentSemAct, String> l04Column;
    @FXML
    public TableColumn<AssessmentSemAct, String> l05Column ;
    @FXML
    public TableColumn<AssessmentSemAct, String> l06Column ;
    @FXML
    public TableColumn<AssessmentSemAct, String> l07Column;

    /* Variables for Section 4: ECTS/Workload Table */
    public ObservableList<WorkloadSemAct> semesterWorkload;

    @FXML
    public TableView<WorkloadSemAct> tableWorkload;

    @FXML
    public TableColumn<WorkloadSemAct, String> semesterActColumn;

    @FXML
    public TableColumn<WorkloadSemAct, String> numbColumn;

    @FXML
    public TableColumn<WorkloadSemAct, String> durationColumn;

    @FXML
    public TableColumn<WorkloadSemAct, String> workloadColumn;

    /* Variables for Section 5: Course/Program Outcome Matrix */
    public ObservableList<CourseOutcome> courseOutcomes; /* represents the non-editable first columns: # and program competencies / outcomes. */
    @FXML
    public TableView<CourseOutcome> outcomeTable;
    @FXML
    public TableColumn<CourseOutcome, String> sharpColumn;
    @FXML
    public TableColumn<CourseOutcome, String> outcomeColumn;
    @FXML
    public TableColumn<CourseOutcome, String> contColumn;
    @FXML
    public TableColumn<CourseOutcome, String> subContCol1;
    @FXML
    public TableColumn<CourseOutcome, String> subContCol2;
    @FXML
    public TableColumn<CourseOutcome, String> subContCol3;
    @FXML
    public TableColumn<CourseOutcome, String> subContCol4;
    @FXML
    public TableColumn<CourseOutcome, String> subContCol5;
















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
        weekTableInitializer();
        assessmentTableInitializer();
        workloadTableInitializer();
        outcomeTableInitializer();
        courseInfoController.loadData();
    }

    //Initializing method for the Week table
    public void weekTableInitializer(){
        weeklySubjects = FXCollections.observableArrayList(
                new WeeklySubjects("1"),
                new WeeklySubjects("2"),
                new WeeklySubjects("3"),
                new WeeklySubjects("4"),
                new WeeklySubjects("5"),
                new WeeklySubjects("6"),
                new WeeklySubjects("7"),
                new WeeklySubjects("8"),
                new WeeklySubjects("9"),
                new WeeklySubjects("10"),
                new WeeklySubjects("11"),
                new WeeklySubjects("12"),
                new WeeklySubjects("13"),
                new WeeklySubjects("14"),
                new WeeklySubjects("15"),
                new WeeklySubjects("16")
        );

        weekColumn.setCellValueFactory(new PropertyValueFactory<WeeklySubjects, String>("weekColumn"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<WeeklySubjects, String>("subjectColumn"));
        reqColumn.setCellValueFactory(new PropertyValueFactory<WeeklySubjects, String>("reqColumn"));

        weekTable.setItems(weeklySubjects);

        subjectColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        reqColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    //Initializing method for the Assessment table
    public void assessmentTableInitializer(){
        semesterActivities = FXCollections.observableArrayList(
                new AssessmentSemAct("Participation"),
                new AssessmentSemAct("Laboratory/Application"),
                new AssessmentSemAct("Field Work"),
                new AssessmentSemAct("Quiz/Studio Critique"),
                new AssessmentSemAct("Homework/Assignment"),
                new AssessmentSemAct("Presentation/Jury"),
                new AssessmentSemAct("Project"),
                new AssessmentSemAct("Seminar/Workshop"),
                new AssessmentSemAct("Oral Exam"),
                new AssessmentSemAct("Midterm"),
                new AssessmentSemAct("Final Exam"),
                new AssessmentSemAct("Total")
        );

        semesterActivitiesColumn.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("semesterActivitiesColumn"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("numberColumn"));
        weightingColumn.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("weightingColumn"));
        l01Column.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("l01Column"));
        l02Column.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("l02Column"));
        l03Column.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("l03Column"));
        l04Column.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("l04Column"));
        l05Column.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("l05Column"));
        l06Column.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("l06Column"));
        l07Column.setCellValueFactory(new PropertyValueFactory<AssessmentSemAct, String>("l07Column"));

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
    }

    //Initializing method for the Workload table
    public void workloadTableInitializer(){
        semesterWorkload = FXCollections.observableArrayList(
                new WorkloadSemAct("Course Hours (Including exam week: 16 x total hours)"),
                new WorkloadSemAct("Laboratory/Application Hours (Including exam week: 16 x total hours)"),
                new WorkloadSemAct("Study Hours out of Class"),
                new WorkloadSemAct("Field Work"),
                new WorkloadSemAct("Quiz/Studio Critique"),
                new WorkloadSemAct("Homework/Assignment"),
                new WorkloadSemAct("Presentation/Jury"),
                new WorkloadSemAct("Project"),
                new WorkloadSemAct("Seminar/Workshop"),
                new WorkloadSemAct("Oral Exam"),
                new WorkloadSemAct("Midterm"),
                new WorkloadSemAct("Final Exam"),
                new WorkloadSemAct("Total")
        );

        semesterActColumn.setCellValueFactory(new PropertyValueFactory<WorkloadSemAct, String>("semesterActColumn"));
        numbColumn.setCellValueFactory(new PropertyValueFactory<WorkloadSemAct, String>("numbColumn"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<WorkloadSemAct, String>("durationColumn"));
        workloadColumn.setCellValueFactory(new PropertyValueFactory<WorkloadSemAct, String>("workloadColumn"));

        tableWorkload.setItems(semesterWorkload);

        numbColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        workloadColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    //Initializing method for the Outcome table
    public void outcomeTableInitializer(){
        courseOutcomes = FXCollections.observableArrayList(
                new CourseOutcome("1", "To have adequate knowledge in Mathematics, Science and\n" +
                        "Computer Engineering; to be able to use theoretical and\n" +
                        "applied information in these areas on complex engineering\n" +
                        "problems."),
                new CourseOutcome("2", "To be able to identify, define, formulate, and solve complex\n" +
                        "Computer Engineering problems; to be able to select and apply\n" +
                        "proper analysis and modeling methods for this purpose."),
                new CourseOutcome("3", "To be able to design a complex system, process, device or\n" +
                        "product under realistic constraints and conditions, in such a\n" +
                        "way as to meet the requirements; to be able to apply modern\n" +
                        "design methods for this purpose."),
                new CourseOutcome("4", "To be able to devise, select, and use modern techniques and\n" +
                        "tools needed for analysis and solution of complex problems in\n" +
                        "Computer Engineering applications; to be able to use\n" +
                        "information technologies effectively."),
                new CourseOutcome("5", "To be able to design and conduct experiments, gather data,\n" +
                        "analyze, and interpret results for investigating complex\n" +
                        "engineering problems or Computer Engineering research\n" +
                        "topics."),
                new CourseOutcome("6", "To be able to work efficiently in Computer Engineering\n" +
                        "disciplinary and multi-disciplinary teams; to be able to work\n" +
                        "individually."),
                new CourseOutcome("7", "To be able to communicate effectively in Turkish, both orally\n" +
                        "and in writing; to be able to author and comprehend written\n" +
                        "reports, to be able to prepare design and implementation\n" +
                        "reports, to present effectively, to be able to give and receive\n" +
                        "clear and comprehensible instructions."),
                new CourseOutcome("8", "To have knowledge about global and social impact of Computer\n" +
                        "Engineering practices on health, environment, and safety; to\n" +
                        "have knowledge about contemporary issues as they pertain to\n" +
                        "engineering; to be aware of the legal ramifications of Computer\n" +
                        "Engineering solutions."),
                new CourseOutcome("9", "To be aware of ethical behavior, professional and ethical\n" +
                        "responsibility; to have knowledge about standards utilized in\n" +
                        "engineering applications."),
                new CourseOutcome("10", "To have knowledge about industrial practices such as project\n" +
                        "management, risk management, and change management; to\n" +
                        "have awareness of entrepreneurship and innovation; to have\n" +
                        "knowledge about sustainable development."),
                new CourseOutcome("11", "To be able to collect data in the area of Computer Engineering,\n" +
                        "and to be able to communicate with colleagues in a foreign\n" +
                        "language. (\"European Language Portfolio Global Scale\", Level\n" +
                        "B1)"),
                new CourseOutcome("12", "To be able to speak a second foreign language at a medium\n" +
                        "level of fluency efficiently."),
                new CourseOutcome("13", "To recognize the need for lifelong learning; to be able to access\n" +
                        "information, to be able to stay current with developments in\n" +
                        "science and technology; to be able to relate the knowledge accumulated throughout the human history to Computer\n" +
                        "Engineering.")
        );

        sharpColumn.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("sharpColumn"));
        outcomeColumn.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("outcomeColumn"));
//        contColumn.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("contColumn"));
        subContCol1.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("subContCol1"));
        subContCol2.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("subContCol2"));
        subContCol3.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("subContCol3"));
        subContCol4.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("subContCol4"));
        subContCol5.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("subContCol5"));
        outcomeTable.setItems(courseOutcomes);

//        addNestedColumns();
//        contColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        subContCol1.setCellFactory(TextFieldTableCell.forTableColumn());
        subContCol2.setCellFactory(TextFieldTableCell.forTableColumn());
        subContCol3.setCellFactory(TextFieldTableCell.forTableColumn());
        subContCol3.setCellFactory(TextFieldTableCell.forTableColumn());
        subContCol4.setCellFactory(TextFieldTableCell.forTableColumn());
        subContCol5.setCellFactory(TextFieldTableCell.forTableColumn());
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
    //Database adding methods

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

    /* Methods to be able to edit the corresponding columns of Week table */
    public void changeSubjectCellEvent(TableColumn.CellEditEvent editedCell){
        WeeklySubjects selectedItem = weekTable.getSelectionModel().getSelectedItem();
        selectedItem.setSubjectColumn(editedCell.getNewValue().toString());
    }
    public void changeReqCellEvent(TableColumn.CellEditEvent editedCell){
        WeeklySubjects selectedItem = weekTable.getSelectionModel().getSelectedItem();
        selectedItem.setReqColumn(editedCell.getNewValue().toString());
    }

    /* Methods to be able to edit the corresponding columns' cells, i.e., number column's first cell. */
    public void changeNumberCellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setNumberColumn(editedCell.getNewValue().toString());
    }
    public void changeWeightCellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setWeightingColumn(editedCell.getNewValue().toString());
    }
    public void changeL01CellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL01Column(editedCell.getNewValue().toString());
    }
    public void changeL02CellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL02Column(editedCell.getNewValue().toString());
    }
    public void changeL03CellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL03Column(editedCell.getNewValue().toString());
    }
    public void changeL04CellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL04Column(editedCell.getNewValue().toString());
    }
    public void changeL05CellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL05Column(editedCell.getNewValue().toString());
    }
    public void changeL06CellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL06Column(editedCell.getNewValue().toString());
    }
    public void changeL07CellEvent(TableColumn.CellEditEvent editedCell){
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL07Column(editedCell.getNewValue().toString());
    }

    /* Methods to be able to edit the corresponding columns of ECTS table */
    public void changeNumbCellEvent(TableColumn.CellEditEvent editedCell){ //Number column of ECTS table.
        WorkloadSemAct selectedItem = tableWorkload.getSelectionModel().getSelectedItem();
        selectedItem.setNumbColumn(editedCell.getNewValue().toString());
    }
    public void changeDurationCellEvent(TableColumn.CellEditEvent editedCell){
        WorkloadSemAct selectedItem = tableWorkload.getSelectionModel().getSelectedItem();
        selectedItem.setDurationColumn(editedCell.getNewValue().toString());
    }
    public void changeWorkloadCellEvent(TableColumn.CellEditEvent editedCell){
        WorkloadSemAct selectedItem = tableWorkload.getSelectionModel().getSelectedItem();
        selectedItem.setWorkloadColumn(editedCell.getNewValue().toString());
    }

    /* Methods to be able to edit the corresponding columns of Outcome table */
    public void changeSubContCol1CellEvent (TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTable.getSelectionModel().getSelectedItem();
        selectedCell.setSubContCol1(editedCell.getNewValue().toString());
    }
    public void changeSubContCol2CellEvent (TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTable.getSelectionModel().getSelectedItem();
        selectedCell.setSubContCol2(editedCell.getNewValue().toString());
    }
    public void changeSubContCol3CellEvent (TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTable.getSelectionModel().getSelectedItem();
        selectedCell.setSubContCol3(editedCell.getNewValue().toString());
    }
    public void changeSubContCol4CellEvent (TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTable.getSelectionModel().getSelectedItem();
        selectedCell.setSubContCol4(editedCell.getNewValue().toString());
    }
    public void changeSubContCol5CellEvent (TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTable.getSelectionModel().getSelectedItem();
        selectedCell.setSubContCol5(editedCell.getNewValue().toString());
    }
}


