import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public ObservableList<WeeklySubjects> subjectsList = FXCollections.observableArrayList();

    public ObservableList<AssessmentSemAct> assessmentsList = FXCollections.observableArrayList();
    public ObservableList<WorkloadSemAct> workloadSemActsList = FXCollections.observableArrayList();
    public ObservableList<CourseOutcome> courseOutcomesList = FXCollections.observableArrayList();
    ObservableList<WeeklySubjects> weekHolderList = FXCollections.observableArrayList();
    ObservableList<AssessmentSemAct> assessmentHolderList = FXCollections.observableArrayList();
    ObservableList<WorkloadSemAct> workloadHolderList = FXCollections.observableArrayList();
    ObservableList<CourseOutcome> outcomeHolderList = FXCollections.observableArrayList();


    /* Database controller variables */
    public CoursesModel model = new CoursesModel();
    public courseInfoController courseInfoController = new courseInfoController(this);
    /* Variables for Section 2: Week Table */
    public ObservableList<WeeklySubjects> weeklySubjectsList;
    @FXML
    public TableView<WeeklySubjects> weekTable;
    @FXML
    public TableColumn<WeeklySubjects, String> weekColumn, subjectColumn, reqColumn;
    /* Variables for Section 3: Assessment Table */
    public ObservableList<AssessmentSemAct> semesterActivities;
    @FXML
    public TableView<AssessmentSemAct> tableView;
    @FXML
    public TableColumn<AssessmentSemAct, String> semesterActivitiesColumn, numberColumn, weightingColumn, l01Column, l02Column, l03Column, l04Column, l05Column, l06Column, l07Column;
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
    Label savedSuccessfully;
    @FXML
    public TableColumn<WorkloadSemAct, String> workloadColumn;
    /* Variables for Section 5: Course/Program Outcome Matrix */
    public ObservableList<CourseOutcome> courseOutcomes; /* represents the non-editable first columns: # and program competencies / outcomes. */
    @FXML
    public TableView<CourseOutcome> outcomeTable;
    @FXML
    public TableColumn<CourseOutcome, String> sharpColumn, outcomeColumn, contColumn, loCol;
    @FXML
    public Button  updateCourseInfoB,updateWeeklyScheduleB,updateAssessmentB,updateWorkloadB,updateOutcomeB;
    //to be able to refresh the all information
    ObservableList<courseInfo> courseList = FXCollections.observableArrayList();
    String queryCourseInfo = null;
    String queryWeeklySchedule = null;
    Connection connection = null;
    PreparedStatement preparedStatementCourseInfo = null;
    ResultSet resultSetCourseInfo = null;
    //text fields are for courseInfo table
    @FXML
    TextField courseNameField, courseCodeField, theoryTimeField, labTimeField, prerequisitesField, coordinatorField, courseCreditsField, courseECTSField, teachingMethodsField, lecturerField, textbooksField, readingField, objectivesField, assistantField, outcomesField, descriptionField;
    //radio buttons are for courseInfo table
    @FXML
    Label courseNameLabel;
    @FXML
    RadioButton courseFallSemButton, courseSpringSemButton;
    //checkbox are for courseInfo table
    @FXML
    RadioButton turkishLangButton, englishLangButton, secondLangButton, thirdCycleButton, requiredTypeButton, electiveTypeButton, shortCycleButton, firstCycleButton, coreCategoryButton, secondCycleButton, f2fDel, onlineDel, blendedDel, majorAreaCategoryButton, supportiveCategoryButton, commCategoryButton, transferableCategoryButton;
    //the pages for buttons
    @FXML
    Pane displayVersionsPage, newCourseENPage, userManualPage, homePage;

    //for the display versions page's table
    @FXML
    TableView<courseInfo> courseInfoTable;
    @FXML
    TableView<WeeklySubjects> weeklyScheduleTable;
    @FXML
    TableView<AssessmentSemAct> assessmentTable;
    @FXML
    TableColumn<AssessmentSemAct, String>semActAssessmentCol,numberAssessmentCol,weightingAssessmentCol,LO1AssessmentCol,LO2AssessmentCol,LO3AssessmentCol,LO4AssessmentCol,LO5AssessmentCol,LO6AssessmentCol,LO7AssessmentCol;
    @FXML
    TableView<WorkloadSemAct> workloadTable;
    @FXML
    TableColumn<AssessmentSemAct, String>semActWorkloadColumn,numberWorkloadColumn,durationWorkloadColumn,workloadWorkloadColumn;
    @FXML
    TableView<CourseOutcome> outcomeTableD;
    @FXML
    TableColumn<CourseOutcome, String>sharpOutcomeCol,programOutcomesColumn,contributionLevOutcome,loOutcomeColumn;

    @FXML
    TableColumn<courseInfo, String> courseCodeCol, semesterCol,nameCol, prerequisitesCol, languageCol,typeCol,categoryCol,deliveryCol, teachingMethodsCol, objectivesCol, outcomesCol, textbooksCol, readingCol, descriptionCol, lecturersCol, assistantsCol, coordinatorCol, levelCol;
    @FXML
    TableColumn<courseInfo, Integer>  theoryCol, labCol, creditsCol, ectsCol;
    @FXML
    TableColumn<WeeklySubjects, String>weekNumberCol,subjectsCol,requiredMaterialsCol;
    @FXML
    Button refreshButton, newCourseButton;

    //entering for the newCoursePage
    String English = "English";
    String Turkish = "Turkish";
    String selectedLanguage;
    @FXML
    Button displayVersionsButton;
    @FXML
    Button userManualButton;
    String queryAdditionForCourseInfo = null;
    PreparedStatement preparedStatementAddition = null;
    //To store the output values of checkboxes
    String semesterValue;
    String languageValue;
    String courseType;
    String categoryType;
    String levelType;
    String deliveryType;
    String cName, cCode, semester, languageS, prerequisites, type, teachingMethods, objectives, outcomes, description, category, textbooks, reading, lecturers, assistants, coordinator, level, delivery;
    int theoryTime, labTime, credit, courseECTS;
    PreparedStatement preparedStatementForWeeks = null;
    WeeklySubjects selectedItem;

    public Controller() throws IOException {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (model.isDatabaseConnected()) {
            System.out.println("The database connection has been provided.");

        } else {
            System.out.println("The connection is failed.");
        }
        displayPageEditable();
        weekTableInitializer();
        refreshWeeklySchedule();
        assessmentTableInitializer();
        refreshAssessment();
        workloadTableInitializer();
        refreshWorkload();
        outcomeTableInitializer();
        refreshOutcome();
        courseInfoController.loadData();
        refreshTable();
    }

    /* To make displayPage's tables editable. */
    public void displayPageEditable(){
        /* CourseInfo table */
        courseCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        semesterCol.setCellFactory(TextFieldTableCell.forTableColumn());
        theoryCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        labCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        creditsCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ectsCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        prerequisitesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        languageCol.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        levelCol.setCellFactory(TextFieldTableCell.forTableColumn());
        deliveryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        teachingMethodsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        coordinatorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lecturersCol.setCellFactory(TextFieldTableCell.forTableColumn());
        assistantsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        objectivesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        outcomesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        textbooksCol.setCellFactory(TextFieldTableCell.forTableColumn());
        readingCol.setCellFactory(TextFieldTableCell.forTableColumn());

        /* Display Screen's Week table edit */
        subjectsCol.setCellFactory(TextFieldTableCell.forTableColumn());
        requiredMaterialsCol.setCellFactory(TextFieldTableCell.forTableColumn());

        /* Display Screen's Assessment table edit */
        numberAssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        weightingAssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LO1AssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LO2AssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LO3AssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LO4AssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LO5AssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LO6AssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());
        LO7AssessmentCol.setCellFactory(TextFieldTableCell.forTableColumn());

        /* Display Screen's Workload table edit */
        numberWorkloadColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durationWorkloadColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        workloadWorkloadColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        /* Display Screen's Outcome table edit */
        contributionLevOutcome.setCellFactory(TextFieldTableCell.forTableColumn());
        loOutcomeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    //*** INITIALIZATION OF ALL TABLEVIEWS IN THE NEW COURSE PAGE

    //Initializing method for the Week table
    public void weekTableInitializer() {
        weeklySubjectsList = FXCollections.observableArrayList(
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

        weekNumberCol.setCellValueFactory(new PropertyValueFactory<>("weekColumn"));
        weekTable.setItems(weeklySubjectsList);

        subjectColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        reqColumn.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    //Initializing method for the Assessment table
    public void assessmentTableInitializer() {
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

        // To edit, use TextFieldTableCell

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
    public void workloadTableInitializer() {
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
    public void outcomeTableInitializer() {
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
        contColumn.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("contColumn"));
        loCol.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("subContL0"));

        outcomeTable.setItems(courseOutcomes);

        contColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        loCol.setCellFactory(TextFieldTableCell.forTableColumn());

    }//THE COMPLETION OF THE INITIALIZATION TABLES ON THE NEW COURSE PAGE


    //*** HOME PAGE BUTTONS FUNCTIONALITY

    //To go to the new course page
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
                    displayVersionsPage.setVisible(false);
                    userManualPage.setVisible(false);
                    savedSuccessfully.setText("Conditions will appear here");
                    savedSuccessfully.setTextFill(Color.GRAY);
                    // Refresh the screen
                    weekTableInitializer();
                    assessmentTableInitializer();
                    workloadTableInitializer();
                    outcomeTableInitializer();
                    courseNameField.setText("");
                    courseCodeField.setText("");
                    theoryTimeField.setText("");
                    labTimeField.setText("");
                    prerequisitesField.setText("");
                    coordinatorField.setText("");
                    courseCreditsField.setText("");
                    courseECTSField.setText("");
                    teachingMethodsField.setText("");
                    lecturerField.setText("");
                    textbooksField.setText("");
                    readingField.setText("");
                    objectivesField.setText("");
                    assistantField.setText("");
                    outcomesField.setText("");
                    descriptionField.setText("");
                    courseFallSemButton.setSelected(false);
                    courseSpringSemButton.setSelected(false);
                    turkishLangButton.setSelected(false);
                    englishLangButton.setSelected(false);
                    secondLangButton.setSelected(false);
                    requiredTypeButton.setSelected(false);
                    electiveTypeButton.setSelected(false);
                    shortCycleButton.setSelected(false);
                    firstCycleButton.setSelected(false);
                    secondCycleButton.setSelected(false);
                    thirdCycleButton.setSelected(false);
                    f2fDel.setSelected(false);
                    onlineDel.setSelected(false);
                    blendedDel.setSelected(false);
                    coreCategoryButton.setSelected(false);
                    majorAreaCategoryButton.setSelected(false);
                    supportiveCategoryButton.setSelected(false);
                    commCategoryButton.setSelected(false);
                    transferableCategoryButton.setSelected(false);
                } else if (selectedLanguage != null && !selectedLanguage.isEmpty() && selectedLanguage.equals(Turkish)) {
                    homePage.setVisible(false);
                    newCourseENPage.setVisible(true);
                    displayVersionsPage.setVisible(false);
                    userManualPage.setVisible(false);
                    savedSuccessfully.setText("Conditions will appear here");
                    savedSuccessfully.setTextFill(Color.GRAY);
                    // Refresh the screen
                    weekTableInitializer();
                    assessmentTableInitializer();
                    workloadTableInitializer();
                    outcomeTableInitializer();
                    courseNameField.setText("");
                    courseCodeField.setText("");
                    theoryTimeField.setText("");
                    labTimeField.setText("");
                    prerequisitesField.setText("");
                    coordinatorField.setText("");
                    courseCreditsField.setText("");
                    courseECTSField.setText("");
                    teachingMethodsField.setText("");
                    lecturerField.setText("");
                    textbooksField.setText("");
                    readingField.setText("");
                    objectivesField.setText("");
                    assistantField.setText("");
                    outcomesField.setText("");
                    descriptionField.setText("");
                    courseFallSemButton.setSelected(false);
                    courseSpringSemButton.setSelected(false);
                    turkishLangButton.setSelected(false);
                    englishLangButton.setSelected(false);
                    secondLangButton.setSelected(false);
                    requiredTypeButton.setSelected(false);
                    electiveTypeButton.setSelected(false);
                    shortCycleButton.setSelected(false);
                    firstCycleButton.setSelected(false);
                    secondCycleButton.setSelected(false);
                    thirdCycleButton.setSelected(false);
                    f2fDel.setSelected(false);
                    onlineDel.setSelected(false);
                    blendedDel.setSelected(false);
                    coreCategoryButton.setSelected(false);
                    majorAreaCategoryButton.setSelected(false);
                    supportiveCategoryButton.setSelected(false);
                    commCategoryButton.setSelected(false);
                    transferableCategoryButton.setSelected(false);
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

    //for the display versions page
    public void clickDV() {
        displayVersionsButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            displayVersionsPage.setVisible(true);
            userManualPage.setVisible(false);
        });
    }

    //for the user manual page
    public void clickUM() {
        userManualButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            displayVersionsPage.setVisible(false);
            userManualPage.setVisible(true);
        });
    } //THE COMPLETION OF THE BUTTONS FUNCTIONALITIES ON THE HOME PAGE

    //*** GETTING DATA ENTERED TO THE TABLEVIEW TABLES BY THE USER TO INSERT INTO THE DATABASE TABLES--> NEW COURSE PAGE

    //to take the data that is entered to the "weekTable" TableView
    private void getDataForWeekTable() {
        boolean isTableEmpty = true;

        try {
            for (WeeklySubjects selectedItem : weekTable.getItems()) {
                if (selectedItem != null) {
                    // Check if any column in the current row is not empty (considering null as non-empty)
                    if (selectedItem.getSubjectColumn() != null && !selectedItem.getSubjectColumn().isEmpty() ||
                            selectedItem.getReqColumn() != null && !selectedItem.getReqColumn().isEmpty()) {
                        isTableEmpty = false;
                        break; // Exit the loop if any non-empty cell is found
                    }
                }
            }

            if (isTableEmpty) {
                // Table is completely empty, show a warning
                Alert notBlank = new Alert(Alert.AlertType.WARNING);
                notBlank.setTitle("Value Selection");
                notBlank.setHeaderText("The Week Table is empty. Please enter some data.");
                notBlank.initModality(Modality.APPLICATION_MODAL);
                notBlank.showAndWait();
            } else {
                // Table is not empty, retrieve data
                for (WeeklySubjects selectedItem : weekTable.getItems()) {
                    if (selectedItem != null) {
                        subjectsList.add(new WeeklySubjects(selectedItem));
                        weekHolderList.add(new WeeklySubjects(selectedItem));
                    }
                }
            }
        } catch (Exception e) {
            // Handle other errors
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while retrieving data from the Week Table.");
            errorAlert.setContentText(e.getMessage());
            errorAlert.initModality(Modality.APPLICATION_MODAL);
            errorAlert.showAndWait();
        }
    }


    //to take the data that is entered to the "tableView" TableView
    private void getDataForAssessmentTable() {
        boolean isTableEmpty = true;

        try {
            for (AssessmentSemAct assessmentSemAct : tableView.getItems()) {
                if (assessmentSemAct != null) {
                    // Check if any column in the current row is not empty (considering null as non-empty)
                    if (assessmentSemAct.getNumberColumn() != null && !assessmentSemAct.getNumberColumn().isEmpty() ||
                            assessmentSemAct.getWeightingColumn() != null && !assessmentSemAct.getWeightingColumn().isEmpty() ||
                            assessmentSemAct.getL01Column() != null && !assessmentSemAct.getL01Column().isEmpty() ||
                            assessmentSemAct.getL02Column() != null && !assessmentSemAct.getL02Column().isEmpty() ||
                            assessmentSemAct.getL03Column() != null && !assessmentSemAct.getL03Column().isEmpty() ||
                            assessmentSemAct.getL04Column() != null && !assessmentSemAct.getL04Column().isEmpty() ||
                            assessmentSemAct.getL05Column() != null && !assessmentSemAct.getL05Column().isEmpty() ||
                            assessmentSemAct.getL06Column() != null && !assessmentSemAct.getL06Column().isEmpty() ||
                            assessmentSemAct.getL07Column() != null && !assessmentSemAct.getL07Column().isEmpty()) {
                        isTableEmpty = false;
                        break; // Exit the loop if any non-empty cell is found
                    }
                }
            }

            if (isTableEmpty) {
                // Table is completely empty, show a warning
                Alert notBlank = new Alert(Alert.AlertType.WARNING);
                notBlank.setTitle("Value Selection");
                notBlank.setHeaderText("The Assessment Table is empty. Please enter some data.");
                notBlank.initModality(Modality.APPLICATION_MODAL);
                notBlank.showAndWait();
            } else {
                // Table is not empty, retrieve data
                for (AssessmentSemAct assessmentSemAct : tableView.getItems()) {
                    if (assessmentSemAct != null) {
                        assessmentsList.add(assessmentSemAct);
                        assessmentHolderList.add(new AssessmentSemAct(assessmentSemAct));
                    }
                }
            }
        } catch (Exception e) {
            // Handle other errors
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while retrieving data from the Assessment Table.");
            errorAlert.setContentText(e.getMessage());
            errorAlert.initModality(Modality.APPLICATION_MODAL);
            errorAlert.showAndWait();
        }
    }


    //to take the data that is entered to the "tableWorkload" TableView
    private void getDataForWorkloadTable(){
        try {
            boolean isTableEmpty = true;

            for (WorkloadSemAct workloadSemAct : tableWorkload.getItems()) {
                if (workloadSemAct != null) {
                    // Check if any column in the current row is not empty
                    if (workloadSemAct.getNumbColumn() != null && !workloadSemAct.getNumbColumn().isEmpty() ||
                            workloadSemAct.getDurationColumn() != null && !workloadSemAct.getDurationColumn().isEmpty() ||
                            workloadSemAct.getWorkloadColumn() != null && !workloadSemAct.getWorkloadColumn().isEmpty()) {
                        isTableEmpty = false;
                        break; // Exit the loop if any non-empty cell is found
                    }
                }
            }

            if (isTableEmpty) {
                // Table is completely empty, show a warning
                Alert notBlank = new Alert(Alert.AlertType.WARNING);
                notBlank.setTitle("Value Selection");
                notBlank.setHeaderText("The Workload Table's all columns cannot be empty.");
                notBlank.initModality(Modality.APPLICATION_MODAL);
                notBlank.showAndWait();
            } else {
                // Table is not empty, retrieve data
                for (WorkloadSemAct workloadSemAct : tableWorkload.getItems()) {
                    if (workloadSemAct != null) {
                        workloadSemActsList.add(workloadSemAct);
                        workloadHolderList.add(workloadSemAct);
                    }
                }
            }
        } catch (Exception e) {
            // Handle other errors
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while processing the Workload Table.");
            errorAlert.setContentText(e.getMessage());
            errorAlert.initModality(Modality.APPLICATION_MODAL);
            errorAlert.showAndWait();
        }

    }
    // to take the data that is entered to the "outcomeTable" TableView
    private void getDataForOutcomeTable() {
        boolean isTableEmpty = true;

        try {
            for (CourseOutcome courseOutcome : outcomeTable.getItems()) {
                if (courseOutcome != null) {
                    // Check if any column in the current row is not empty (considering null as non-empty)
                    if (courseOutcome.getContColumn() != null && !courseOutcome.getContColumn().isEmpty() ||
                            courseOutcome.getSubContL0() != null && !courseOutcome.getSubContL0().isEmpty()) {
                        isTableEmpty = false;
                        break; // Exit the loop if any non-empty cell is found
                    }
                }
            }

            if (isTableEmpty) {
                // Table is completely empty, show a warning
                Alert notBlank = new Alert(Alert.AlertType.WARNING);
                notBlank.setTitle("Value Selection");
                notBlank.setHeaderText("The Outcome Table is empty. Please enter some data.");
                notBlank.initModality(Modality.APPLICATION_MODAL);
                notBlank.showAndWait();
            } else {
                // Table is not empty, retrieve data
                for (CourseOutcome courseOutcome : outcomeTable.getItems()) {
                    if (courseOutcome != null) {
                        courseOutcomesList.add(courseOutcome);
                        outcomeHolderList.add(courseOutcome);
                    }
                }
            }
        } catch (Exception e) {
            // Handle other errors
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("An error occurred while retrieving data from the Outcome Table.");
            errorAlert.setContentText(e.getMessage());
            errorAlert.initModality(Modality.APPLICATION_MODAL);
            errorAlert.showAndWait();
        }
    }





    ArrayList<courseInfo> updateCourseInfoList = new ArrayList<>();
    // to take the data that is entered to the "courseInfoTable" TableView
    private void getCourseInfoDisplay(){
        for (int i= 0; i<courseInfoTable.getItems().size();i++){
            courseInfo courseInfo = courseInfoTable.getItems().get(i);
            if(courseInfoTable != null){
                updateCourseInfoList.add(courseInfo);
            }
        }
    }
    ObservableList<WeeklySubjects> updateWeeklySubjectsList = FXCollections.observableArrayList();
    // to take the data that is entered to the "weeklyScheduleTable" TableView
    private void getWeeklySubjectsDisplay(){
        for (int i= 0; i<weeklyScheduleTable.getItems().size();i++){
            WeeklySubjects weeklySubjects = weeklyScheduleTable.getItems().get(i);
            if(weeklyScheduleTable != null){
                updateWeeklySubjectsList.add(weeklySubjects);
                weekInfoOInsertAndUpdateWriteToJsonFile(updateWeeklySubjectsList,"src/main/resources/updatedWeekInfoData.json");
            }
        }
    }
    ObservableList<AssessmentSemAct> updateAssessmentList = FXCollections.observableArrayList();
    // to take the data that is entered to the "weeklyScheduleTable" TableView
    private void getAssessmentDisplay(){
        for (int i= 0; i<assessmentTable.getItems().size();i++){
            AssessmentSemAct assessmentSemAct = assessmentTable.getItems().get(i);
            if(assessmentTable != null){
                updateAssessmentList.add(assessmentSemAct);
                assessmentInfoInsertAndUpdateWriteToJsonFile(updateAssessmentList, "src/main/resources/assessmentInfoData.json");
            }
        }
    }
    ObservableList<WorkloadSemAct> updateWorkloadList = FXCollections.observableArrayList();
    // to take the data that is entered to the "workloadTable" TableView
    private void getWorkloadDisplay(){
        for (int i= 0; i<workloadTable.getItems().size();i++){
            WorkloadSemAct workloadSemAct = workloadTable.getItems().get(i);
            if(workloadTable != null){
                updateWorkloadList.add(workloadSemAct);
                workloadInfoInsertAndUpdateWriteToJsonFile(updateWorkloadList, "src/main/resources/workloadInfoData.json");
            }
        }
    }
    ObservableList<CourseOutcome> updateOutcomeList = FXCollections.observableArrayList();
    // to take the data that is entered to the "outcomeTableD" TableView
    private void getOutcomeDisplay(){
        for (int i= 0; i<outcomeTableD.getItems().size();i++){
            CourseOutcome courseOutcome = outcomeTableD.getItems().get(i);
            if(outcomeTableD != null){
                updateOutcomeList.add(courseOutcome);
                outcomeInfoInsertAndUpdateWriteToJsonFile(updateOutcomeList, "src/main/resources/outcomeInfoData.json");

            }
        }
    }//THE COMPLETION OF GETTING DATA ENTERED TO THE TABLEVIEW TABLES


    //*** INSERTING TO THE DATABASE HELPING WITH THE GET DATA METHODS

    //CourseInfo Table
    //According to the Text fields, to add below text fields data.
    boolean sendCourseInfo = false;
    public void insertCourseInfo() {
        connection = SQLConnection.Connector();
        cName = courseNameField.getText();
        cCode = courseCodeField.getText();
        semester = null;
        languageS = null;
        prerequisites = prerequisitesField.getText();
        type = null;
        level = null;
        delivery = null;
        teachingMethods = teachingMethodsField.getText();
        coordinator = coordinatorField.getText();
        lecturers = lecturerField.getText();
        assistants = assistantField.getText();
        objectives = objectivesField.getText();
        outcomes = outcomesField.getText();
        description = descriptionField.getText();
        category = null;
        textbooks = textbooksField.getText();
        reading = readingField.getText();

        System.out.println("\nThe pressed key information has been recorded, and String conversion processes have been completed:");
        try{
            convertSemesterValue();
            convertLanguageValue();
            convertCourseType();
            convertLevelType();
            convertDeliveryType();
            convertCategoryType();
        }catch (Exception e){
            courseInfoController.controlBlank();
        }
        if (courseInfoController.controlBlank()) {
            try {
                theoryTime = Integer.parseInt(theoryTimeField.getText());
                labTime = Integer.parseInt(labTimeField.getText());
                credit = Integer.parseInt(courseCreditsField.getText());
                courseECTS = Integer.parseInt(courseECTSField.getText());
                if (credit == 0 || courseECTS == 0) {
                    Alert notBlank = new Alert(Alert.AlertType.WARNING);
                    notBlank.setTitle("Value Selection");
                    notBlank.setHeaderText("The L.Credits and ECTS fields,both,must be a non-zero value.");
                    savedSuccessfully.setText("Data saving failed, please pay attention to the warnings.");
                    savedSuccessfully.setTextFill(Color.RED);
                    notBlank.initModality(Modality.APPLICATION_MODAL);
                    notBlank.showAndWait();
                    sendCourseInfo = false;
                } else if ((labTime == 0 && theoryTime == 0)) {
                    Alert notBlank = new Alert(Alert.AlertType.WARNING);
                    notBlank.setTitle("Value Selection");
                    notBlank.setHeaderText("At least one of the Lab Hour and Theory Hour must be a non-zero value.");
                    savedSuccessfully.setText("Data saving failed, please pay attention to the warnings.");
                    savedSuccessfully.setTextFill(Color.RED);
                    notBlank.initModality(Modality.APPLICATION_MODAL);
                    notBlank.showAndWait();
                    sendCourseInfo = false;
                } else {
                    courseInfoController.getQuery(cName, cCode, semester, theoryTime, labTime, credit, courseECTS, prerequisites, languageS, type, teachingMethods, objectives, outcomes, description, category, level, coordinator, lecturers, assistants, reading, textbooks, delivery);
                    courseInfoController.insertTable();
                    refreshTable();
                    courseList.clear();
                    //to refresh after sending to the database table
                    courseNameField.setText("");
                    courseCodeField.setText("");
                    theoryTimeField.setText("");
                    labTimeField.setText("");
                    prerequisitesField.setText("");
                    coordinatorField.setText("");
                    courseCreditsField.setText("");
                    courseECTSField.setText("");
                    teachingMethodsField.setText("");
                    lecturerField.setText("");
                    textbooksField.setText("");
                    readingField.setText("");
                    objectivesField.setText("");
                    assistantField.setText("");
                    outcomesField.setText("");
                    descriptionField.setText("");
                    courseFallSemButton.setSelected(false);
                    courseSpringSemButton.setSelected(false);
                    turkishLangButton.setSelected(false);
                    englishLangButton.setSelected(false);
                    secondLangButton.setSelected(false);
                    requiredTypeButton.setSelected(false);
                    electiveTypeButton.setSelected(false);
                    shortCycleButton.setSelected(false);
                    firstCycleButton.setSelected(false);
                    secondCycleButton.setSelected(false);
                    thirdCycleButton.setSelected(false);
                    f2fDel.setSelected(false);
                    onlineDel.setSelected(false);
                    blendedDel.setSelected(false);
                    coreCategoryButton.setSelected(false);
                    majorAreaCategoryButton.setSelected(false);
                    supportiveCategoryButton.setSelected(false);
                    commCategoryButton.setSelected(false);
                    transferableCategoryButton.setSelected(false);
                    savedSuccessfully.setText("Data are saved.");
                    savedSuccessfully.setTextFill(Color.GREEN);
                    sendCourseInfo = true;
                }
            } catch (NumberFormatException e) {
                Alert notBlank = new Alert(Alert.AlertType.WARNING);
                notBlank.setTitle("Value Selection");
                notBlank.setHeaderText("The theory time, lab time, credit and ECTS must be integer.");
                notBlank.initModality(Modality.APPLICATION_MODAL);
                notBlank.showAndWait();
            }
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    PreparedStatement preparedStatementWeekly = null;
    boolean sendWeekly = false;
    //According to the WeekNumber, to add the required materials that is entered by the user to the DB.
    private void insertWeeklyScheduleDB() {
        getDataForWeekTable();
        if (!subjectsList.isEmpty()) {
            connection = SQLConnection.Connector();
            try {
                String insertWeeklySchedule = "INSERT INTO WeeklySchedule (WeekNumber,Subjects, RequiredMaterials) VALUES (?, ?, ?)";
                String blank = "INSERT INTO WeeklySchedule (WeekNumber,Subjects, RequiredMaterials) VALUES ('----------------','----------------','----------------')";

                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(blank);
                preparedStatementWeekly = connection.prepareStatement(insertWeeklySchedule);


                for (WeeklySubjects weeklySubjects : subjectsList){
                    preparedStatementWeekly.setString(1,weeklySubjects.getWeekColumn());
                    preparedStatementWeekly.setString(2,weeklySubjects.getSubjectColumn());
                    preparedStatementWeekly.setString(3,weeklySubjects.getReqColumn());
                    weekInfoOInsertAndUpdateWriteToJsonFile(weekHolderList,"src/main/resources/updatedWeekInfoData.json");
                    preparedStatementWeekly.addBatch();

                }

                preparedStatement.addBatch();
                preparedStatement.executeBatch();
                preparedStatementWeekly.executeBatch();

                if (preparedStatementWeekly.getUpdateCount() > 0) {
                    System.out.println("Data updated successfully for required materials column.");
                    savedSuccessfully.setText("Data are saved.");
                    savedSuccessfully.setTextFill(Color.GREEN);
                    sendWeekly = true;
                    subjectsList.clear();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No data saved.");
            sendWeekly = false;
        }
    }

    //Assessment Table
    //According to the SemesterActivities, to add the Number, Weighting, LO1, LO2, LO3, LO4, LO5, LO6, LO7 that are entered by the user to the DB.
    boolean sendAssessment = false;
    PreparedStatement preparedStatementAssessment = null;
    private void insertAssessmentTable() {
        getDataForAssessmentTable();
        if (!assessmentsList.isEmpty()) {
            connection = SQLConnection.Connector();
            try {
                String insertAssessment = "INSERT INTO Assessment (SemesterActivities,Number, Weighting, LO1, LO2,LO3,LO4,LO5,LO6,LO7) VALUES (?, ?, ?, ?,?,?,?,?,?,?)";
                String blank = "INSERT INTO Assessment (SemesterActivities,Number, Weighting) VALUES ('----------------','----------------','----------------')";

                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(blank);

                preparedStatementAssessment = connection.prepareStatement(insertAssessment);


                for (AssessmentSemAct assessmentSemAct : assessmentsList) {
                    //for entered values except Semester Activities
                    preparedStatementAssessment.setString(1, assessmentSemAct.getSemesterActivitiesColumn());
                    preparedStatementAssessment.setString(2, assessmentSemAct.getNumberColumn());
                    preparedStatementAssessment.setString(3, assessmentSemAct.getWeightingColumn());
                    preparedStatementAssessment.setString(4, assessmentSemAct.getL01Column());
                    preparedStatementAssessment.setString(5, assessmentSemAct.getL02Column());
                    preparedStatementAssessment.setString(6, assessmentSemAct.getL03Column());
                    preparedStatementAssessment.setString(7, assessmentSemAct.getL04Column());
                    preparedStatementAssessment.setString(8, assessmentSemAct.getL05Column());
                    preparedStatementAssessment.setString(9, assessmentSemAct.getL06Column());
                    preparedStatementAssessment.setString(10, assessmentSemAct.getL07Column());
                    assessmentInfoInsertAndUpdateWriteToJsonFile(assessmentHolderList,"src/main/resources/assessmentInfoData.json");

                    //sending queries in bulk instead of individually.
                    preparedStatementAssessment.addBatch();
                }
                preparedStatement.addBatch();
                preparedStatement.executeBatch();
                preparedStatementAssessment.executeBatch();

                if (preparedStatementAssessment.getUpdateCount() > 0) {
                    System.out.println("Data updated successfully for number column.");
                    savedSuccessfully.setText("Data are saved.");
                    savedSuccessfully.setTextFill(Color.GREEN);
                    sendAssessment = true;

                    // refreshWeeklySchedule();
                    assessmentsList.clear();
                }} catch (SQLException e) {
                e.printStackTrace();
            } finally {
                {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No data saved");
            savedSuccessfully.setText("Data saving failed, please pay attention to the warnings.");
            savedSuccessfully.setTextFill(Color.RED);
        }
    }

    //Workload Table
    //According to the SemesterActivities, to add the WorkloadNumber, WorkloadDuration, TotalWorkload that are entered by the user to the DB.
    boolean sendWorkload = false;
    PreparedStatement preparedStatementInsertWorkload = null;
    private void insertWorkLoadTable() {
        getDataForWorkloadTable();
        if (!workloadSemActsList.isEmpty()) {
            connection = SQLConnection.Connector();
            try {
                // INSERT sorgusu oluşturun
                String insertWorkload = "INSERT INTO WorkloadTable (WorkloadNumber, SemesterActivities, WorkloadDuration, TotalWorkload) VALUES (?, ?, ?, ?)";
                String blank = "INSERT INTO WorkloadTable (WorkloadNumber, SemesterActivities, WorkloadDuration) VALUES ('----------------','----------------','----------------')";

                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(blank);

                preparedStatementInsertWorkload = connection.prepareStatement(insertWorkload);

                for (WorkloadSemAct workloadSemAct : workloadSemActsList) {
                    preparedStatementInsertWorkload.setString(1, workloadSemAct.getNumbColumn());
                    preparedStatementInsertWorkload.setString(2, workloadSemAct.getSemesterActColumn());
                    preparedStatementInsertWorkload.setString(3, workloadSemAct.getDurationColumn());
                    preparedStatementInsertWorkload.setString(4, workloadSemAct.getWorkloadColumn());
                    workloadInfoInsertAndUpdateWriteToJsonFile(workloadHolderList, "src/main/resources/workloadInfoData.json");
                    preparedStatementInsertWorkload.addBatch();
                }
                preparedStatement.addBatch();
                preparedStatement.executeBatch();

                preparedStatementInsertWorkload.executeBatch();

                System.out.println("Data successfully inserted into WorkloadTable.");
                savedSuccessfully.setTextFill(Color.GREEN);
                sendWorkload = true;

                workloadSemActsList.clear();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No data saved");
            savedSuccessfully.setText("Data saving failed, please pay attention to the warnings.");
            savedSuccessfully.setTextFill(Color.RED);
            sendWorkload = false;
        }
    }
    //OutcomeMatrix Table
    //According to the ProgramOutcomes, to add the ContributionLevel, LO that are entered by the user to the DB.

    PreparedStatement preparedStatementOutcome = null;
    boolean sendOutcome = false;
    private void insertOutcomeTable() {
        getDataForOutcomeTable();
        if (!courseOutcomesList.isEmpty()) {
            connection = SQLConnection.Connector();
            try {
                String insertOutcome = "INSERT INTO OutcomeMatrix ('#', ProgramOutcomes, ContributionLevel, LO) VALUES (?, ?, ?, ?)";
                String blank = "INSERT INTO OutcomeMatrix ('#', ProgramOutcomes, ContributionLevel) VALUES ('----------------','----------------','----------------')";

                PreparedStatement preparedStatement;
                preparedStatement = connection.prepareStatement(blank);

                preparedStatementOutcome = connection.prepareStatement(insertOutcome);

                for (CourseOutcome courseOutcome : courseOutcomesList) {
                    //for entered values
                    preparedStatementOutcome.setString(1, courseOutcome.getSharpColumn());
                    preparedStatementOutcome.setString(2,courseOutcome.getOutcomeColumn());
                    preparedStatementOutcome.setString(3,courseOutcome.getContColumn());
                    preparedStatementOutcome.setString(4,courseOutcome.getSubContL0());
                    outcomeInfoInsertAndUpdateWriteToJsonFile(outcomeHolderList, "src/main/resources/outcomeInfoData.json");

                    //sending queries in bulk instead of individually.
                    preparedStatementOutcome.addBatch();
                }
                preparedStatement.addBatch();
                preparedStatement.executeBatch();

                preparedStatementOutcome.executeBatch();

                if(preparedStatementOutcome.executeUpdate()>0){
                    System.out.println("Data updated successfully for contribution level number column for OutcomeMatrix table.");
                    savedSuccessfully.setText("Data are saved.");
                    sendOutcome = true;
                    courseOutcomesList.clear();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No data saved");
            savedSuccessfully.setText("Data saving failed, please pay attention to the warnings.");
            savedSuccessfully.setTextFill(Color.RED);
            sendOutcome = false;
        }
    } // COMPLETION TO INSERTING THE DATABASE HELPING WITH THE GET DATA METHODS



    //*** REFRESH METHODS IN THE DISPLAY VERSIONS PAGE
    public void refreshTable() {
        courseList.clear();
        //to refresh the tables in the database
        Connection connection = SQLConnection.Connector();
        try {
            queryCourseInfo = "SELECT * FROM 'CourseInfo'";
            //to prepare the SQLite query
            preparedStatementCourseInfo = connection.prepareStatement(queryCourseInfo);
            //to run the sqlite query above
            resultSetCourseInfo = preparedStatementCourseInfo.executeQuery();

            while (resultSetCourseInfo.next()) {
                //to create a new courseInfo object according to the data by the user
                courseList.add(new courseInfo(resultSetCourseInfo.getString("CourseID"),
                        resultSetCourseInfo.getString("CourseName"),
                        resultSetCourseInfo.getString("CourseSemester"),
                        resultSetCourseInfo.getInt("TheoryTime"),
                        resultSetCourseInfo.getInt("LabTime"),
                        resultSetCourseInfo.getInt("CourseCredit"),
                        resultSetCourseInfo.getInt("CourseECTS"),
                        resultSetCourseInfo.getString("Prerequisites"),
                        resultSetCourseInfo.getString("CourseLanguage"),
                        resultSetCourseInfo.getString("CourseType"),
                        resultSetCourseInfo.getString("TeachingMethods"),
                        resultSetCourseInfo.getString("CourseObj"),
                        resultSetCourseInfo.getString("Outcomes"),
                        resultSetCourseInfo.getString("CourseDesc"),
                        resultSetCourseInfo.getString("CourseCategory"),
                        resultSetCourseInfo.getString("CourseLevel"),
                        resultSetCourseInfo.getString("CourseCoordinator"),
                        resultSetCourseInfo.getString("CourseLecturer"),
                        resultSetCourseInfo.getString("Assistant"),
                        resultSetCourseInfo.getString("ReadingInfo"),
                        resultSetCourseInfo.getString("TextbookInfo"),
                        resultSetCourseInfo.getString("Delivery")
                ));
                //To update the elements of the courseInfoTable with the data received from courseList.
                courseInfoTable.setItems(courseList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert notBlank = new Alert(Alert.AlertType.WARNING);
            notBlank.setTitle("Value Selection");
            notBlank.setHeaderText("The L.Credits and ECTS fields,both,must be a non-zero value.");
            notBlank.initModality(Modality.APPLICATION_MODAL);
            notBlank.showAndWait();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void refreshWeeklySchedule(){
        Connection connection = SQLConnection.Connector();
        queryWeeklySchedule = "SELECT * FROM WeeklySchedule";
        try {
            preparedStatementForWeeks = connection.prepareStatement(queryWeeklySchedule);

            // Execute the query and get the ResultSet
            ResultSet resultSet = preparedStatementForWeeks.executeQuery();
            weeklyScheduleTable.getItems().clear();
            // Clear existing data in the table
            while (resultSet.next()) {
                WeeklySubjects weeklySubject = new WeeklySubjects();


                // Assuming your WeeklySubjects class has appropriate setter methods
                weeklySubject.setWeekColumn(resultSet.getString("WeekNumber"));
                weeklySubject.setSubjectColumn(resultSet.getString("Subjects"));
                weeklySubject.setReqColumn(resultSet.getString("RequiredMaterials"));

                weekNumberCol.setCellValueFactory(new PropertyValueFactory<>("weekColumn"));
                subjectsCol.setCellValueFactory(new PropertyValueFactory<>("subjectColumn"));
                requiredMaterialsCol.setCellValueFactory(new PropertyValueFactory<>("reqColumn"));

                weeklyScheduleTable.getItems().add(weeklySubject);

                // Add the WeeklySubjects object to the table
            }

            // Populate the table with the data from the ResultSet


        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    String queryAssessment = null;
    public void refreshAssessment(){
        Connection connection = SQLConnection.Connector();
        queryAssessment = "SELECT * FROM Assessment";
        try {
            PreparedStatement preparedStatementForAssessment = connection.prepareStatement(queryAssessment);

            // Execute the query and get the ResultSet
            ResultSet resultSet = preparedStatementForAssessment.executeQuery();
            assessmentTable.getItems().clear();
            // Clear existing data in the table
            while (resultSet.next()) {
                AssessmentSemAct assessmentSemAct = new AssessmentSemAct();

                // Assuming your WeeklySubjects class has appropriate setter methods
                assessmentSemAct.setSemesterActivitiesColumn(resultSet.getString("SemesterActivities"));
                assessmentSemAct.setNumberColumn(resultSet.getString("Number"));
                assessmentSemAct.setWeightingColumn(resultSet.getString("Weighting"));
                assessmentSemAct.setL01Column(resultSet.getString("LO1"));
                assessmentSemAct.setL02Column(resultSet.getString("LO2"));
                assessmentSemAct.setL03Column(resultSet.getString("LO3"));
                assessmentSemAct.setL04Column(resultSet.getString("LO4"));
                assessmentSemAct.setL05Column(resultSet.getString("LO5"));
                assessmentSemAct.setL06Column(resultSet.getString("LO6"));
                assessmentSemAct.setL07Column(resultSet.getString("LO7"));


                semActAssessmentCol.setCellValueFactory(new PropertyValueFactory<>("semesterActivitiesColumn"));
                numberAssessmentCol.setCellValueFactory(new PropertyValueFactory<>("numberColumn"));
                weightingAssessmentCol.setCellValueFactory(new PropertyValueFactory<>("weightingColumn"));
                LO1AssessmentCol.setCellValueFactory(new PropertyValueFactory<>("l01Column"));
                LO2AssessmentCol.setCellValueFactory(new PropertyValueFactory<>("l02Column"));
                LO3AssessmentCol.setCellValueFactory(new PropertyValueFactory<>("l03Column"));
                LO4AssessmentCol.setCellValueFactory(new PropertyValueFactory<>("l04Column"));
                LO5AssessmentCol.setCellValueFactory(new PropertyValueFactory<>("l05Column"));
                LO6AssessmentCol.setCellValueFactory(new PropertyValueFactory<>("l06Column"));
                LO7AssessmentCol.setCellValueFactory(new PropertyValueFactory<>("l07Column"));

                assessmentTable.getItems().add(assessmentSemAct);

                // Add the WeeklySubjects object to the table
            }

            // Populate the table with the data from the ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshWorkload(){
        Connection connection = SQLConnection.Connector();
        String queryWorkload = "SELECT * FROM WorkloadTable";
        try {
            PreparedStatement preparedStatementForWorkload = connection.prepareStatement(queryWorkload);

            // Execute the query and get the ResultSet
            ResultSet resultSet = preparedStatementForWorkload.executeQuery();
            workloadTable.getItems().clear();
            // Clear existing data in the table
            while (resultSet.next()) {
                WorkloadSemAct workloadSemAct = new WorkloadSemAct();

                // Assuming your WorkloadSemAct class has appropriate setter methods
                workloadSemAct.setSemesterActColumn(resultSet.getString("SemesterActivities"));
                workloadSemAct.setNumbColumn(resultSet.getString("WorkloadNumber"));
                workloadSemAct.setDurationColumn(resultSet.getString("WorkloadDuration"));
                workloadSemAct.setWorkloadColumn(resultSet.getString("TotalWorkload"));

                semActWorkloadColumn.setCellValueFactory(new PropertyValueFactory<>("semesterActColumn"));
                numberWorkloadColumn.setCellValueFactory(new PropertyValueFactory<>("numbColumn"));
                durationWorkloadColumn.setCellValueFactory(new PropertyValueFactory<>("durationColumn"));
                workloadWorkloadColumn.setCellValueFactory(new PropertyValueFactory<>("workloadColumn"));

                // Add the WeeklySubjects WorkloadSemAct to the table
                workloadTable.getItems().add(workloadSemAct);
            }

            // Populate the table with the data from the ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
        }try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void refreshOutcome(){
        Connection connection = SQLConnection.Connector();
        String queryOutcome = "SELECT * FROM OutcomeMatrix";
        try {
            PreparedStatement preparedStatementForOutcome = connection.prepareStatement(queryOutcome);

            // Execute the query and get the ResultSet
            ResultSet resultSet = preparedStatementForOutcome.executeQuery();
            outcomeTableD.getItems().clear();
            // Clear existing data in the table
            while (resultSet.next()) {
                CourseOutcome courseOutcome = new CourseOutcome();

                // Assuming your CourseOutcome class has appropriate setter methods
                courseOutcome.setSharpColumn(resultSet.getString("#"));
                courseOutcome.setOutcomeColumn(resultSet.getString("ProgramOutcomes"));
                courseOutcome.setContColumn(resultSet.getString("ContributionLevel"));
                courseOutcome.setSubContL0(resultSet.getString("LO"));

                sharpOutcomeCol.setCellValueFactory(new PropertyValueFactory<>("sharpColumn"));
                programOutcomesColumn.setCellValueFactory(new PropertyValueFactory<>("outcomeColumn"));
                contributionLevOutcome.setCellValueFactory(new PropertyValueFactory<>("contColumn"));
                loOutcomeColumn.setCellValueFactory(new PropertyValueFactory<>("subContL0"));

                // Add the CourseOutcome to the table
                outcomeTableD.getItems().add(courseOutcome);
            }

            // Populate the table with the data from the ResultSet
        } catch (SQLException e) {
            e.printStackTrace();
        }try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //*** UPDATE FUNCTIONS ON THE DISPLAY SCREEN

    //To update CourseInfo table
    PreparedStatement preparedStatementUpdateCourseInfo;
    public void updateCourseInfo(){
        getCourseInfoDisplay();
        String updateCourseInfo = "REPLACE INTO CourseInfo (CourseID,CourseName, CourseSemester,TheoryTime,LabTime,CourseCredit,CourseECTS,Prerequisites,CourseLanguage,CourseType,TeachingMethods,CourseObj,Outcomes,CourseDesc,CourseCategory,CourseLevel,CourseCoordinator,CourseLecturer,Assistant,ReadingInfo,TextbookInfo,Delivery) VALUES  (?,?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ? ,?,?, ?, ?, ?, ?, ? , ?,?)";
        connection = SQLConnection.Connector();
        try {
            preparedStatementUpdateCourseInfo = connection.prepareStatement(updateCourseInfo);
            for(courseInfo courseInfo : updateCourseInfoList){
                preparedStatementUpdateCourseInfo.setString(1, courseInfo.getCourseCode());  // Set CourseID for the WHERE clause
                preparedStatementUpdateCourseInfo.setString(2, courseInfo.getCourseName());
                preparedStatementUpdateCourseInfo.setString(3, courseInfo.getCourseSemester());
                preparedStatementUpdateCourseInfo.setInt(4, courseInfo.getTheoryTime());
                preparedStatementUpdateCourseInfo.setInt(5, courseInfo.getLabTime());
                preparedStatementUpdateCourseInfo.setInt(6, courseInfo.getCourseCredit());
                preparedStatementUpdateCourseInfo.setInt(7, courseInfo.getCourseECTS());
                preparedStatementUpdateCourseInfo.setString(8, courseInfo.getPrerequisites());
                preparedStatementUpdateCourseInfo.setString(9, String.valueOf(courseInfo.getCourseLang()));
                preparedStatementUpdateCourseInfo.setString(10, String.valueOf(courseInfo.getCourseType()));
                preparedStatementUpdateCourseInfo.setString(11, courseInfo.getTeachingMethods());
                preparedStatementUpdateCourseInfo.setString(12, courseInfo.getCourseObj());
                preparedStatementUpdateCourseInfo.setString(13, courseInfo.getOutcomes());
                preparedStatementUpdateCourseInfo.setString(14, courseInfo.getCourseDesc());
                preparedStatementUpdateCourseInfo.setString(15, String.valueOf(courseInfo.getCourseCategory()));
                preparedStatementUpdateCourseInfo.setString(16, String.valueOf(courseInfo.getCourseLevel()));
                preparedStatementUpdateCourseInfo.setString(17, courseInfo.getCourseCoordinator());
                preparedStatementUpdateCourseInfo.setString(18, courseInfo.getCourseLecturer());
                preparedStatementUpdateCourseInfo.setString(19, courseInfo.getAssistant());
                preparedStatementUpdateCourseInfo.setString(20, courseInfo.getReading());
                preparedStatementUpdateCourseInfo.setString(21, courseInfo.getTextbook());
                preparedStatementUpdateCourseInfo.setString(22, String.valueOf(courseInfo.getDelivery()));

                preparedStatementUpdateCourseInfo.executeUpdate();

            }
            courseList.clear();
            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //To update CourseInfo table
    PreparedStatement preparedStatementUpdateWeeklySchedule;
    public void updateWeeklySchedule(){
        getWeeklySubjectsDisplay();


        String updateWeeklySchedule = "REPLACE INTO WeeklySchedule (WeekNumber, Subjects, RequiredMaterials) VALUES (?, ?, ?)";
        connection = SQLConnection.Connector();

        try {
            preparedStatementUpdateWeeklySchedule = connection.prepareStatement(updateWeeklySchedule);
            for(WeeklySubjects weeklySubjects : updateWeeklySubjectsList){
                preparedStatementUpdateWeeklySchedule.setString(1, weeklySubjects.getWeekColumn());
                preparedStatementUpdateWeeklySchedule.setString(2, weeklySubjects.getSubjectColumn());
                preparedStatementUpdateWeeklySchedule.setString(3, weeklySubjects.getReqColumn());

                preparedStatementUpdateWeeklySchedule.executeUpdate();

            }
            updateWeeklySubjectsList.clear();
            refreshWeeklySchedule();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //To update Assessment table
    PreparedStatement preparedStatementUpdateAssessment;
    public void updateAssessment(){
        getAssessmentDisplay();
        String updateAssessment = "REPLACE INTO Assessment (SemesterActivities, Number, Weighting, LO1, LO2, LO3, LO4, LO5, LO6, LO7) VALUES (?, ?, ?,?,?,?,?,?,?,?)";
        connection = SQLConnection.Connector();
        try {
            preparedStatementUpdateAssessment = connection.prepareStatement(updateAssessment);
            for(AssessmentSemAct assessmentSemAct :updateAssessmentList){
                preparedStatementUpdateAssessment.setString(1, assessmentSemAct.getSemesterActivitiesColumn());
                preparedStatementUpdateAssessment.setString(2, assessmentSemAct.getNumberColumn());
                preparedStatementUpdateAssessment.setString(3, assessmentSemAct.getWeightingColumn());
                preparedStatementUpdateAssessment.setString(4, assessmentSemAct.getL01Column());
                preparedStatementUpdateAssessment.setString(5, assessmentSemAct.getL02Column());
                preparedStatementUpdateAssessment.setString(6, assessmentSemAct.getL03Column());
                preparedStatementUpdateAssessment.setString(7, assessmentSemAct.getL04Column());
                preparedStatementUpdateAssessment.setString(8, assessmentSemAct.getL05Column());
                preparedStatementUpdateAssessment.setString(9, assessmentSemAct.getL06Column());
                preparedStatementUpdateAssessment.setString(10, assessmentSemAct.getL07Column());

                preparedStatementUpdateAssessment.executeUpdate();

            }
            updateAssessmentList.clear();

            refreshAssessment();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //To update WorkloadTable table
    PreparedStatement preparedStatementUpdateWorkload;
    public void updateWorkload(){
        getWorkloadDisplay();
        String updateWorkload = "REPLACE INTO WorkloadTable (SemesterActivities, WorkloadNumber, WorkloadDuration, TotalWorkload) VALUES (?, ?, ?, ?)";
        connection = SQLConnection.Connector();
        try {
            preparedStatementUpdateWorkload = connection.prepareStatement(updateWorkload);
            for(WorkloadSemAct workloadSemAct :updateWorkloadList){
                preparedStatementUpdateWorkload.setString(1, workloadSemAct.getSemesterActColumn());
                preparedStatementUpdateWorkload.setString(2, workloadSemAct.getNumbColumn());
                preparedStatementUpdateWorkload.setString(3, workloadSemAct.getDurationColumn());
                preparedStatementUpdateWorkload.setString(4, workloadSemAct.getWorkloadColumn());

                preparedStatementUpdateWorkload.executeUpdate();

            }
            updateWorkloadList.clear();

            refreshWorkload();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //To update OutcomeMatrix table
    PreparedStatement preparedStatementUpdateOutcome;
    public void updateOutcome(){
        getOutcomeDisplay();
        String updateOutcome = "REPLACE INTO OutcomeMatrix ('#', ProgramOutcomes, ContributionLevel, LO) VALUES (?, ?, ?, ?)";
        connection = SQLConnection.Connector();
        try {
            preparedStatementUpdateOutcome = connection.prepareStatement(updateOutcome);
            for(CourseOutcome courseOutcome :updateOutcomeList){
                preparedStatementUpdateOutcome.setString(1, courseOutcome.getSharpColumn());
                preparedStatementUpdateOutcome.setString(2, courseOutcome.getOutcomeColumn());
                preparedStatementUpdateOutcome.setString(3, courseOutcome.getContColumn());
                preparedStatementUpdateOutcome.setString(4, courseOutcome.getSubContL0());

                preparedStatementUpdateOutcome.executeUpdate();

            }
            updateOutcomeList.clear();

            refreshOutcome();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }




    // COMPLETION REFRESH METHODS IN THE DISPLAY VERSIONS PAGE

    //*** FOR ORGANIZATION OF THE RADIO BUTTONS, HANDLE AND CONVERT METHODS
    @FXML
    public String handleCourseFallSemButton() {
        if (courseFallSemButton.isSelected()) {
            semesterValue = "1";
            System.out.println("Clicked fall semester button.");
        }
        return "Fall";
    }

    @FXML
    public String handleCourseSpringSemButton() {
        if (courseSpringSemButton.isSelected()) {
            semesterValue = "2";
            System.out.println("Clicked spring semester button.");
        }
        return "Spring";
    }

    @FXML
    public String handleTurkishButton() {
        if (turkishLangButton.isSelected()) {
            languageValue = "1";
            System.out.println("Clicked Turkish language button.");
        }
        return "Turkish";
    }

    @FXML
    public String handleEnglishButton() {
        if (englishLangButton.isSelected()) {
            languageValue = "2";
            System.out.println("Clicked English language button.");
        }
        return "English";
    }

    @FXML
    public String handleSecondLButton() {
        if (secondLangButton.isSelected()) {
            languageValue = "3";
            System.out.println("Clicked Second language button.");
        }
        return "Second Foreign Language";
    }

    @FXML
    public String handleRequiredButton() {
        if (requiredTypeButton.isSelected()) {
            courseType = "1";
            System.out.println("Clicked required course type button.");
        }
        return "Required";
    }

    @FXML
    public String handleElectiveButton() {
        if (electiveTypeButton.isSelected()) {
            courseType = "2";
            System.out.println("Clicked elective course type button.");
        }
        return "Elective";
    }

    @FXML
    public String handleShortButton() {
        if (shortCycleButton.isSelected()) {
            levelType = "1";
            System.out.println("Clicked Short Cycle button.");
        }
        return "Short Cycle";
    }

    @FXML
    public String handleFirstButton() {
        if (firstCycleButton.isSelected()) {
            levelType = "2";
            System.out.println("Clicked First Cycle button.");
        }
        return "First Cycle";
    }

    @FXML
    public String handleSecondButton() {
        if (secondCycleButton.isSelected()) {
            levelType = "3";
            System.out.println("Clicked Second Cycle button.");
        }
        return "Second Cycle";
    }

    @FXML
    public String handleThirdButton() {
        if (thirdCycleButton.isSelected()) {
            levelType = "4";
            System.out.println("Clicked Third Cycle button.");
        }
        return "Third Cycle";
    }

    @FXML
    public String handleF2FButton() {
        if (f2fDel.isSelected()) {
            deliveryType = "1";
            System.out.println("Clicked Face-to-Face button.");
        }
        return "Face-to-Face";
    }

    @FXML
    public String handleOnlineButton() {
        if (onlineDel.isSelected()) {
            deliveryType = "2";
            System.out.println("Clicked Online button.");
        }
        return "Online";
    }

    @FXML
    public String handleBlendedButton() {
        if (blendedDel.isSelected()) {
            deliveryType = "3";
            System.out.println("Clicked Blended button.");
        }
        return "Blended";
    }

    @FXML
    public String handleCoreButton() {
        if (coreCategoryButton.isSelected()) {
            categoryType = "1";
            System.out.println("Clicked Core Course button.");
        }
        return "Core Course";
    }

    @FXML
    public String handleMajorButton() {
        if (majorAreaCategoryButton.isSelected()) {
            categoryType = "2";
            System.out.println("Clicked Major Area Course button.");
        }
        return "Major Area Course";
    }

    @FXML
    public String handleSupportiveButton() {
        if (supportiveCategoryButton.isSelected()) {
            categoryType = "3";
            System.out.println("Clicked Supportive Course button.");
        }
        return "Supportive Course";
    }

    @FXML
    public String handleCommButton() {
        if (commCategoryButton.isSelected()) {
            categoryType = "4";
            System.out.println("Clicked Communication and Management Skills Course button.");
        }
        return "Communication and Management Skills Course";
    }
    //Database adding methods

    @FXML
    public String handleTransferableButton() {
        if (transferableCategoryButton.isSelected()) {
            categoryType = "5";
            System.out.println("Clicked Transferable Skill Course button.");
        }
        return "Transferable Skill Course";
    }

    public void convertSemesterValue() {

            if (semesterValue.equals("1")) {
                semester = handleCourseFallSemButton();
            } else if (semesterValue.equals("2")) {
                semester = handleCourseSpringSemButton();
            }

    }

    public void convertLanguageValue() {

            switch (languageValue) {
                case "1" -> languageS = handleTurkishButton();
                case "2" -> languageS = handleEnglishButton();
                case "3" -> languageS = handleSecondLButton();
            }

    }

    public void convertCourseType() {

            if (courseType.equals("1")) {
                type = handleRequiredButton();
            } else if (courseType.equals("2")) {
                type = handleElectiveButton();
            }

    }

    public void convertLevelType() {
            switch (levelType) {
                case "1" -> level = handleShortButton();
                case "2" -> level = handleFirstButton();
                case "3" -> level = handleSecondButton();
                case "4" -> level = handleThirdButton();
            }


    }

    public void convertDeliveryType() {
            switch (deliveryType) {
                case "1" -> delivery = handleF2FButton();
                case "2" -> delivery = handleOnlineButton();
                case "3" -> delivery = handleBlendedButton();
            }


    }

    public void convertCategoryType() {
            switch (categoryType) {
                case "1" -> category = handleCoreButton();
                case "2" -> category = handleMajorButton();
                case "3" -> category = handleSupportiveButton();
                case "4" -> category = handleCommButton();
                case "5" -> category = handleTransferableButton();
            }


    }// COMPLETION RADIO BUTTONS ORGANIZATION METHODS



    //*** HELPER METHODS FOR EDITABLE WEEK TABLES IN THE COURSE PAGE
    /* Methods to be able to edit the corresponding columns of Week table */
    public void changeSubjectCellEvent(TableColumn.CellEditEvent editedCell) {
        WeeklySubjects selectedItem = weekTable.getSelectionModel().getSelectedItem();
        selectedItem.setSubjectColumn(editedCell.getNewValue().toString());
    }

    public void changeReqCellEvent(TableColumn.CellEditEvent editedCell) {
        WeeklySubjects selectedItem = weekTable.getSelectionModel().getSelectedItem();
        selectedItem.setReqColumn(editedCell.getNewValue().toString());
    }

    /* Methods to be able to edit the corresponding columns' cells, i.e., number column's first cell. */
    public void changeNumberCellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setNumberColumn(editedCell.getNewValue().toString());
    }

    public void changeWeightCellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setWeightingColumn(editedCell.getNewValue().toString());
    }

    public void changeL01CellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL01Column(editedCell.getNewValue().toString());
    }

    public void changeL02CellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL02Column(editedCell.getNewValue().toString());
    }

    public void changeL03CellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL03Column(editedCell.getNewValue().toString());
    }

    public void changeL04CellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL04Column(editedCell.getNewValue().toString());
    }

    public void changeL05CellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL05Column(editedCell.getNewValue().toString());
    }

    public void changeL06CellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL06Column(editedCell.getNewValue().toString());
    }

    public void changeL07CellEvent(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedRow = tableView.getSelectionModel().getSelectedItem();
        selectedRow.setL07Column(editedCell.getNewValue().toString());
    }

    /* Methods to be able to edit the corresponding columns of ECTS table */
    public void changeNumbCellEvent(TableColumn.CellEditEvent editedCell) { //Number column of ECTS table.
        WorkloadSemAct selectedItem = tableWorkload.getSelectionModel().getSelectedItem();
        selectedItem.setNumbColumn(editedCell.getNewValue().toString());
    }

    public void changeDurationCellEvent(TableColumn.CellEditEvent editedCell) {
        WorkloadSemAct selectedItem = tableWorkload.getSelectionModel().getSelectedItem();
        selectedItem.setDurationColumn(editedCell.getNewValue().toString());
    }

    public void changeWorkloadCellEvent(TableColumn.CellEditEvent editedCell) {
        WorkloadSemAct selectedItem = tableWorkload.getSelectionModel().getSelectedItem();
        selectedItem.setWorkloadColumn(editedCell.getNewValue().toString());
    }

    /* Methods to be able to edit the corresponding columns of Outcome table */
    public void changeContColCellEvent(TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTable.getSelectionModel().getSelectedItem();
        selectedCell.setContColumn(editedCell.getNewValue().toString());
    }
    /* Methods to be able to edit the corresponding columns of Outcome table */
    public void changeLOCellEvent(TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTable.getSelectionModel().getSelectedItem();
        selectedCell.setSubContL0(editedCell.getNewValue().toString());
    }
    //Methods to edit for Display Versions Screen
    public void onCourseCode(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseCode(editedCell.getNewValue().toString());
    }
    public void onName(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseName(editedCell.getNewValue().toString());
    }
    public void onSemester(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseSemester(editedCell.getNewValue().toString());
    }
    public void onTheoryTime(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setTheoryTime(Integer.parseInt(editedCell.getNewValue().toString()));
    }
    public void onLabTime(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setLabTime(Integer.parseInt(editedCell.getNewValue().toString()));
    }
    public void onLocalCredit(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseCredit(Integer.parseInt(editedCell.getNewValue().toString()));
    }
    public void onECTS(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseECTS(Integer.parseInt(editedCell.getNewValue().toString()));
    }
    public void onPre(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setPrerequisites(editedCell.getNewValue().toString());
    }
    public void onLanguage(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseLang(editedCell.getNewValue().toString());
    }
    public void onType(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseType(editedCell.getNewValue().toString());
    }
    public void onCourseLevel(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseLevel(editedCell.getNewValue().toString());
    }
    public void onDelivery(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setDelivery(editedCell.getNewValue().toString());
    }
    public void onMethods(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setTeachingMethods(editedCell.getNewValue().toString());
    }
    public void onCoordinator(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseCoordinator(editedCell.getNewValue().toString());
    }
    public void onLecturer(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseLecturer(editedCell.getNewValue().toString());
    }
    public void onAssistants(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setAssistant(editedCell.getNewValue().toString());
    }
    public void onObjective(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseObj(editedCell.getNewValue().toString());
    }
    public void onOutcome(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setOutcomes(editedCell.getNewValue().toString());
    }
    public void onDescription(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseDesc(editedCell.getNewValue().toString());
    }
    public void onCategory(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setCourseCategory(editedCell.getNewValue().toString());
    }
    public void onTextbook(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setTextbook(editedCell.getNewValue().toString());
    }
    public void onReading(TableColumn.CellEditEvent editedCell) {
        courseInfo selectedCell = courseInfoTable.getSelectionModel().getSelectedItem();
        selectedCell.setReading(editedCell.getNewValue().toString());
    }



    /* For Display Screen's Weekly Schedule Table */
    public void onSubjects(TableColumn.CellEditEvent editedCell) {
        WeeklySubjects selectedCell = weeklyScheduleTable.getSelectionModel().getSelectedItem();
        selectedCell.setSubjectColumn(editedCell.getNewValue().toString());
    }
    public void onReqMat(TableColumn.CellEditEvent editedCell) {
        WeeklySubjects selectedCell = weeklyScheduleTable.getSelectionModel().getSelectedItem();
        selectedCell.setReqColumn(editedCell.getNewValue().toString());
    }

    /* For Dispaly Screen's Assessment Table*/
    public void onSemAct(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setSemesterActivitiesColumn(editedCell.getNewValue().toString());
    }

    public void onNumber(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setNumberColumn(editedCell.getNewValue().toString());
    }

    public void onWeighting(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setWeightingColumn(editedCell.getNewValue().toString());
    }

    public void onLO1(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setL01Column(editedCell.getNewValue().toString());
    }

    public void onLO2(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setL02Column(editedCell.getNewValue().toString());
    }

    public void onLO3(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setL03Column(editedCell.getNewValue().toString());
    }

    public void onLO4(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setL04Column(editedCell.getNewValue().toString());
    }

    public void onLO5(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setL05Column(editedCell.getNewValue().toString());
    }

    public void onLO6(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setL06Column(editedCell.getNewValue().toString());
    }

    public void onLO7(TableColumn.CellEditEvent editedCell) {
        AssessmentSemAct selectedCell = assessmentTable.getSelectionModel().getSelectedItem();
        selectedCell.setL07Column(editedCell.getNewValue().toString());
    }

    /* For Display Screen's Workload Table */
    public void onSemActWork(TableColumn.CellEditEvent editedCell) {
        WorkloadSemAct selectedCell = workloadTable.getSelectionModel().getSelectedItem();
        selectedCell.setSemesterActColumn(editedCell.getNewValue().toString());
    }
    public void onNumberWork(TableColumn.CellEditEvent editedCell) {
        WorkloadSemAct selectedCell = workloadTable.getSelectionModel().getSelectedItem();
        selectedCell.setNumbColumn(editedCell.getNewValue().toString());
    }
    public void onDurationWork(TableColumn.CellEditEvent editedCell) {
        WorkloadSemAct selectedCell = workloadTable.getSelectionModel().getSelectedItem();
        selectedCell.setDurationColumn(editedCell.getNewValue().toString());
    }
    public void onWorkloadWork(TableColumn.CellEditEvent editedCell) {
        WorkloadSemAct selectedCell = workloadTable.getSelectionModel().getSelectedItem();
        selectedCell.setWorkloadColumn(editedCell.getNewValue().toString());
    }

    /* For Display Screen's Outcome Table */
    public void onSharp(TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTableD.getSelectionModel().getSelectedItem();
        selectedCell.setSharpColumn(editedCell.getNewValue().toString());
    }
    public void onOutcomes(TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTableD.getSelectionModel().getSelectedItem();
        selectedCell.setOutcomeColumn(editedCell.getNewValue().toString());
    }
    public void onContribution(TableColumn.CellEditEvent editedCell) {
        CourseOutcome selectedCell = outcomeTableD.getSelectionModel().getSelectedItem();
        selectedCell.setContColumn(editedCell.getNewValue().toString());
    }
    public void onLO(TableColumn.CellEditEvent editedCell){
        CourseOutcome selectedCell = outcomeTableD.getSelectionModel().getSelectedItem();
        selectedCell.setSubContL0(editedCell.getNewValue().toString());
    } /* COMPLETION OF HELPER METHODS */

    /* SAVE TO THE DATABASE BUTTON BY CLICKING ON THE SAVE BUTTON */
    @FXML
    public void save() {
        /* Save all tables to the database */
        insertCourseInfo();
        refreshTable();
        insertWeeklyScheduleDB();
        refreshWeeklySchedule();
        insertAssessmentTable();
        refreshAssessment();
        insertWorkLoadTable();
        refreshWorkload();
        insertOutcomeTable();
        refreshOutcome();

        /* To Refresh the screen */
        weekTableInitializer();
        assessmentTableInitializer();
        workloadTableInitializer();
        outcomeTableInitializer();
        courseInfoInsertWriteToJsonFile(courseList, "src/main/resources/courseInfoData.json");
    }
    private static void courseInfoInsertWriteToJsonFile(ObservableList<courseInfo> courseInfoList, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath), courseInfoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Wrote to the Course Info JSON file successfully.");
    }
    private static void weekInfoOInsertAndUpdateWriteToJsonFile(ObservableList<WeeklySubjects> weeklySubjectsList, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath), weeklySubjectsList);
            System.out.println("Wrote to the Week JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void assessmentInfoInsertAndUpdateWriteToJsonFile(ObservableList<AssessmentSemAct> assessmentInfoList, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath), assessmentInfoList);
            System.out.println("Wrote to the Assessment JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void workloadInfoInsertAndUpdateWriteToJsonFile(ObservableList<WorkloadSemAct> workloadSemActsList, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath), workloadSemActsList);
            System.out.println("Wrote to the Workload JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void outcomeInfoInsertAndUpdateWriteToJsonFile(ObservableList<CourseOutcome> courseOutcomesList, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath), courseOutcomesList);
            System.out.println("Wrote to the Outcome JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }















    /* Helper methods to check if a table is empty */
    @FXML
    public void updateCourseInfoAction(){
        updateCourseInfo();
        updateCourseInfoB.setText("Updated.");
    }
    @FXML
    public void updateWeeklyScheduleAction(){
        updateWeeklySchedule();
        updateWeeklyScheduleB.setText("Updated.");
    }
    @FXML
    public void updateAssessmentAction(){
        updateAssessment();
        updateAssessmentB.setText("Updated.");
    }
    @FXML
    public void updateWorkloadAction(){
        updateWorkload();
        updateWorkloadB.setText("Updated.");
    }
    @FXML
    public void updateOutcomeAction(){
        updateOutcome();
        updateOutcomeB.setText("Updated.");
    }
}


