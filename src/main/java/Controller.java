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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public ArrayList<WeeklySubjects> subjects = new ArrayList<>();
    /* Database controller variables */
    public CoursesModel model = new CoursesModel();
    public WeeklySubjects weeklySubjects = new WeeklySubjects(this);
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
    public TableColumn<WorkloadSemAct, String> workloadColumn;
    /* Variables for Section 5: Course/Program Outcome Matrix */
    public ObservableList<CourseOutcome> courseOutcomes; /* represents the non-editable first columns: # and program competencies / outcomes. */
    @FXML
    public TableView<CourseOutcome> outcomeTable;
    @FXML
    public TableColumn<CourseOutcome, String> sharpColumn, outcomeColumn, contColumn, loCol;
    //to be able to refresh the all information
    ObservableList<courseInfo> courseList = FXCollections.observableArrayList();
    ObservableList<WeeklySubjects> SubjectsMaterialsList = FXCollections.observableArrayList();
    String queryCourseInfo = null;
    String queryWeeklySchedule = null;
    Connection connection = null;
    PreparedStatement preparedStatementCourseInfo = null;
    PreparedStatement preparedStatementWeeklySchedule = null;
    ResultSet resultSetCourseInfo = null;
    ResultSet resultSetWeeklySchedule = null;
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
    Pane displayVersionsPage, newCourseENPage, userManualPage, homePage, newCourseTRPage;

    //for the display versions page's table
    @FXML
    TableView<courseInfo> courseInfoTable;
    @FXML
    TableView<WeeklySubjects> weeklyScheduleTable;
    @FXML
    TableColumn<courseInfo, String> courseCodeCol, nameCol, prerequisitesCol, teachingMethodsCol, objectivesCol, outcomesCol, textbooksCol, readingCol, descriptionCol, lecturersCol, assistantsCol, coordinatorCol, levelCol, subjectsCol, requiredMaterialsCol;
    @FXML
    TableColumn<courseInfo, Integer> semesterCol, theoryCol, labCol, creditsCol, ectsCol, languageCol, typeCol, categoryCol, deliveryCol, weekNumberCol;
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
    int semesterValue;
    int languageValue;
    int courseType;
    int categoryType;
    int levelType;
    int deliveryType;
    String cName, cCode, semester, languageS, prerequisites, type, teachingMethods, objectives, outcomes, description, category, textbooks, reading, lecturers, assistants, coordinator, level, delivery;
    int theoryTime, labTime, credit, courseECTS;
    PreparedStatement preparedStatementWeekN;
    String updateQuery = null;
    PreparedStatement preparedStatementForWeeks = null;
    WeeklySubjects selectedItem;

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
//        weeklySubjects.loadData();
    }

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

        insertWeekNumber(weeklySubjectsList);

        weekColumn.setCellValueFactory(new PropertyValueFactory<WeeklySubjects, String>("weekColumn"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<WeeklySubjects, String>("subjectColumn"));
        reqColumn.setCellValueFactory(new PropertyValueFactory<WeeklySubjects, String>("reqColumn"));

        weekNumberCol.setCellValueFactory(new PropertyValueFactory<>("weekColumn"));
        weekTable.setItems(weeklySubjectsList);

        subjectColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        reqColumn.setCellFactory(TextFieldTableCell.forTableColumn());


    }

    private void insertWeekNumber(ObservableList<WeeklySubjects> weeklySubjectsL) {
        connection = SQLConnection.Connector();
        //Week Number Column Addition
        String insertWNQuery = "INSERT INTO WeeklySchedule (WeekNumber) VALUES (?)";
        try {
            preparedStatementWeekN = connection.prepareStatement(insertWNQuery);
            for (WeeklySubjects weeklySubjects : weeklySubjectsL) {
                if (weeklySubjects.getWeekColumn().isEmpty()) {
                    preparedStatementWeekN.setString(1, weeklySubjects.getWeekColumn());
                    preparedStatementWeekN.executeUpdate();
                    System.out.println("The Week Column was completed.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection not to block the database.
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //to take the data that is entered to the weekTable TableView
    private void getData() {
        //not to conflict with the previous data
        subjects.clear();
        for (int i = 0; i < weekTable.getItems().size(); i++) {
            //all items are taken from the all rows
            WeeklySubjects selectedItem = weekTable.getItems().get(i);
            if (selectedItem != null) {
                WeeklySubjects updatedSubject = new WeeklySubjects(selectedItem);
                subjects.add(updatedSubject);
            }
        }
    }

    private void insertWeeklyScheduleSubjectsDB() {
        //firstly data are taken.
        getData();
        if (!subjects.isEmpty()) {
            connection = SQLConnection.Connector();
            try {
                //to update the Subjects column in the WeeklySchedule table.
                //CASE WHEN: allows updating based on the week number.
                StringBuilder insertQuery = new StringBuilder("UPDATE WeeklySchedule SET Subjects = CASE WeekNumber ");
                for (int i = 0; i < subjects.size(); i++) {
                    /*In the getData method, all the data entered by the user has been added to the subjects list. In this section, each element of
                    subjects is assigned to a WeeklySubjects object.*/
                    WeeklySubjects newSubject = subjects.get(i);
                    //WHEN WeekColumn --> adding to the relational week number row. And then Subject Column.
                    insertQuery.append(" WHEN ").append(newSubject.getWeekColumn()).append(" THEN '").append(newSubject.getSubjectColumn()).append("' ");
                }
                insertQuery.append(" END WHERE WeekNumber IN (");
                for (WeeklySubjects subject : subjects) {
                    insertQuery.append(subject.getWeekColumn()).append(", ");
                }
                //to modify the code.
                insertQuery.deleteCharAt(insertQuery.length() - 2);
                insertQuery.append(")");


                preparedStatementForWeeks = connection.prepareStatement(insertQuery.toString());
                int result = preparedStatementForWeeks.executeUpdate();

                if (result > 0) {
                    System.out.println("Data updated successfully for subjects column.");
                    refreshWeeklySchedule();
                    subjects.clear();
                } else {
                    System.out.println("Data failed.");
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
            System.out.println("No data updated");
        }
    }

    private void insertWeeklyScheduleReqMatDB() {
        getData();
        if (!subjects.isEmpty()) {
            connection = SQLConnection.Connector();
            try {
                StringBuilder insertQuery = new StringBuilder("UPDATE WeeklySchedule SET RequiredMaterials = CASE WeekNumber ");
                for (int i = 0; i < subjects.size(); i++) {
                    WeeklySubjects newSubject = subjects.get(i);
                    insertQuery.append(" WHEN ").append(newSubject.getWeekColumn()).append(" THEN '").append(newSubject.getReqColumn()).append("' ");
                }
                insertQuery.append(" END WHERE WeekNumber IN (");
                for (WeeklySubjects subject : subjects) {
                    insertQuery.append(subject.getWeekColumn()).append(", ");
                }
                insertQuery.deleteCharAt(insertQuery.length() - 2);
                insertQuery.append(")");

                preparedStatementForWeeks = connection.prepareStatement(insertQuery.toString());
                int result = preparedStatementForWeeks.executeUpdate();
                if (result > 0) {
                    System.out.println("Data updated successfully for required materials column.");
                    refreshWeeklySchedule();
                    subjects.clear();
                } else {
                    System.out.println("Data failed.");
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
            System.out.println("No data updated");
        }
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
        insertSemesterActivities(semesterActivities);

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
    PreparedStatement preparedStatementSemAct;
    private void insertSemesterActivities(ObservableList<AssessmentSemAct> assessmentSemActs) {
        connection = SQLConnection.Connector();
        //Week Number Column Addition
        String insertAssessmentAct = "INSERT INTO Assessment (SemesterActivities) VALUES (?)";
        try {
            preparedStatementSemAct = connection.prepareStatement(insertAssessmentAct);
            for (AssessmentSemAct assessmentSemAct : assessmentSemActs) {
                if (assessmentSemAct.getSemesterActivitiesColumn().isEmpty()) {
                    preparedStatementSemAct.setString(1, assessmentSemAct.getSemesterActivitiesColumn());
                    preparedStatementSemAct.executeUpdate();
                }
            }
            System.out.println("The Semester Activities column was completed.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection not to block the database.
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        insertWorkloadSemAct(semesterWorkload);

        semesterActColumn.setCellValueFactory(new PropertyValueFactory<WorkloadSemAct, String>("semesterActColumn"));
        numbColumn.setCellValueFactory(new PropertyValueFactory<WorkloadSemAct, String>("numbColumn"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<WorkloadSemAct, String>("durationColumn"));
        workloadColumn.setCellValueFactory(new PropertyValueFactory<WorkloadSemAct, String>("workloadColumn"));

        tableWorkload.setItems(semesterWorkload);

        numbColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        durationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        workloadColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    PreparedStatement preparedStatementWorkloadSemAct;
    public void insertWorkloadSemAct(ObservableList<WorkloadSemAct> workloadSemActs){
        connection = SQLConnection.Connector();
        //Week Number Column Addition
        String insertAssessmentAct = "INSERT INTO WorkloadTable (SemesterActivities) VALUES (?)";
        try {
            preparedStatementWorkloadSemAct = connection.prepareStatement(insertAssessmentAct);
            for (WorkloadSemAct workloadSemAct : workloadSemActs) {
                if (workloadSemAct.getSemesterActColumn().isEmpty()) {
                    preparedStatementWorkloadSemAct.setString(1, workloadSemAct.getSemesterActColumn());
                    preparedStatementWorkloadSemAct.executeUpdate();
                }
            }
            System.out.println("The Semester Activities column for Workload Table was completed.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection not to block the database.
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

        //insertOutcome(courseOutcomes);

        sharpColumn.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("sharpColumn"));
        outcomeColumn.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("outcomeColumn"));
        contColumn.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("contColumn"));
        loCol.setCellValueFactory(new PropertyValueFactory<CourseOutcome, String>("subContL0"));

        outcomeTable.setItems(courseOutcomes);

        contColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        loCol.setCellFactory(TextFieldTableCell.forTableColumn());

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
                    displayVersionsPage.setVisible(false);
                    userManualPage.setVisible(false);
                } else if (selectedLanguage != null && !selectedLanguage.isEmpty() && selectedLanguage.equals(Turkish)) {
                    homePage.setVisible(false);
                    newCourseENPage.setVisible(false);
                    newCourseTRPage.setVisible(true);
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


    public void clickDV() {
        displayVersionsButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            displayVersionsPage.setVisible(true);
            userManualPage.setVisible(false);
            newCourseTRPage.setVisible(false);
        });
    }

    //for the courseInfo table

    public void clickUM() {
        userManualButton.setOnAction(event -> {
            homePage.setVisible(false);
            newCourseENPage.setVisible(false);
            displayVersionsPage.setVisible(false);
            userManualPage.setVisible(true);
            newCourseTRPage.setVisible(false);
        });
    }

    @FXML
    void refreshTable() {
        //to refresh the tables in the database
        try {
            refreshWeeklySchedule();
            if(!courseList.isEmpty()){
                    courseList.clear();
                    queryCourseInfo = "SELECT * FROM 'CourseInfo'";
                    //to prepare the SQLite query
                    preparedStatementCourseInfo = connection.prepareStatement(queryCourseInfo);
                    //to run the sqlite query above
                    resultSetCourseInfo = preparedStatementCourseInfo.executeQuery();

                    while (resultSetCourseInfo.next()) {
                        //to create a new courseInfo object according to the data by the user
                        courseList.add(new courseInfo(resultSetCourseInfo.getString("CourseID"),
                                resultSetCourseInfo.getString("CourseName"),
                                resultSetCourseInfo.getInt("CourseSemester"),
                                resultSetCourseInfo.getInt("TheoryTime"),
                                resultSetCourseInfo.getInt("LabTime"),
                                resultSetCourseInfo.getInt("CourseCredit"),
                                resultSetCourseInfo.getInt("CourseECTS"),
                                resultSetCourseInfo.getString("Prerequisites"),
                                resultSetCourseInfo.getInt("CourseLanguage"),
                                resultSetCourseInfo.getInt("CourseType"),
                                resultSetCourseInfo.getString("TeachingMethods"),
                                resultSetCourseInfo.getString("CourseObj"),
                                resultSetCourseInfo.getString("Outcomes"),
                                resultSetCourseInfo.getString("CourseDesc"),
                                resultSetCourseInfo.getInt("CourseCategory"),
                                resultSetCourseInfo.getInt("CourseLevel"),
                                resultSetCourseInfo.getString("CourseCoordinator"),
                                resultSetCourseInfo.getString("CourseLecturer"),
                                resultSetCourseInfo.getString("Assistant"),
                                resultSetCourseInfo.getString("ReadingInfo"),
                                resultSetCourseInfo.getString("TextbookInfo"),
                                resultSetCourseInfo.getInt("Delivery")
                        ));
                        //To update the elements of the courseInfoTable with the data received from courseList.
                        courseInfoTable.setItems(courseList);

        }}
        } catch (Exception e) {
            e.printStackTrace();
            Alert notBlank = new Alert(Alert.AlertType.WARNING);
            notBlank.setTitle("Value Selection");
            notBlank.setHeaderText("The L.Credits and ECTS fields,both,must be a non-zero value.");
            notBlank.initModality(Modality.APPLICATION_MODAL);
            notBlank.showAndWait();
        }
    }
    public void refreshWeeklySchedule(){
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
    public String handleShortButton() {
        if (shortCycleButton.isSelected()) {
            levelType = 1;
            System.out.println("Clicked Short Cycle button.");
        }
        return "Short Cycle";
    }

    @FXML
    public String handleFirstButton() {
        if (firstCycleButton.isSelected()) {
            levelType = 2;
            System.out.println("Clicked First Cycle button.");
        }
        return "First Cycle";
    }

    @FXML
    public String handleSecondButton() {
        if (secondCycleButton.isSelected()) {
            levelType = 3;
            System.out.println("Clicked Second Cycle button.");
        }
        return "Second Cycle";
    }

    @FXML
    public String handleThirdButton() {
        if (thirdCycleButton.isSelected()) {
            levelType = 4;
            System.out.println("Clicked Third Cycle button.");
        }
        return "Third Cycle";
    }

    @FXML
    public String handleF2FButton() {
        if (f2fDel.isSelected()) {
            deliveryType = 1;
            System.out.println("Clicked Face-to-Face button.");
        }
        return "Face-to-Face";
    }

    @FXML
    public String handleOnlineButton() {
        if (onlineDel.isSelected()) {
            deliveryType = 2;
            System.out.println("Clicked Online button.");
        }
        return "Online";
    }

    @FXML
    public String handleBlendedButton() {
        if (blendedDel.isSelected()) {
            deliveryType = 3;
            System.out.println("Clicked Blended button.");
        }
        return "Blended";
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
    //Database adding methods

    @FXML
    public String handleTransferableButton() {
        if (transferableCategoryButton.isSelected()) {
            categoryType = 5;
            System.out.println("Clicked Transferable Skill Course button.");
        }
        return "Transferable Skill Course";
    }
//    String queryForWeeks;
//    PreparedStatement preparedStatementForWeeks;
//    public void getQuerySchedule(ObservableList<WeeklySubjects> numberList, String subjects,String requiredMaterials ) {
//        connection = SQLConnection.Connector();
//        queryForWeeks = "INSERT INTO WeeklySchedule('WeekNumber') VALUES ('"+weeklySubjectsList+"')";

    @FXML
    public void save() {
        //for Subjects column is WeeklySchedule table
        insertWeeklyScheduleSubjectsDB();
        //for Required Materials column is WeeklySchedule table
        insertWeeklyScheduleReqMatDB();
        saveCourseInfo();
    }

    public void saveCourseInfo() {
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

        convertSemesterValue();
        convertLanguageValue();
        convertCourseType();
        convertLevelType();
        convertDeliveryType();
        convertCategoryType();

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
                    notBlank.initModality(Modality.APPLICATION_MODAL);
                    notBlank.showAndWait();
                } else if ((labTime == 0 && theoryTime == 0)) {
                    Alert notBlank = new Alert(Alert.AlertType.WARNING);
                    notBlank.setTitle("Value Selection");
                    notBlank.setHeaderText("At least one of the Lab Hour and Theory Hour must be a non-zero value.");
                    notBlank.initModality(Modality.APPLICATION_MODAL);
                    notBlank.showAndWait();
                } else {
                    courseInfoController.getQuery(cName, cCode, semester, theoryTime, labTime, credit, courseECTS, prerequisites, languageS, type, teachingMethods, objectives, outcomes, description, category, level, coordinator, lecturers, assistants, reading, textbooks, delivery);
                    courseInfoController.insertTable();
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

    public void convertSemesterValue() {
        if (semesterValue == 1) {
            semester = handleCourseFallSemButton();
        } else if (semesterValue == 2) {
            semester = handleCourseSpringSemButton();
        }
    }

    public void convertLanguageValue() {
        if (languageValue == 1) {
            languageS = handleTurkishButton();
        } else if (languageValue == 2) {
            languageS = handleEnglishButton();
        } else if (languageValue == 3) {
            languageS = handleSecondLButton();
        }
    }

    public void convertCourseType() {
        if (courseType == 1) {
            type = handleRequiredButton();
        } else if (courseType == 2) {
            type = handleElectiveButton();
        }
    }

    public void convertLevelType() {
        if (levelType == 1) {
            level = handleShortButton();
        } else if (levelType == 2) {
            level = handleFirstButton();
        } else if (levelType == 3) {
            level = handleSecondButton();
        } else if (levelType == 4) {
            level = handleThirdButton();
        }
    }

    public void convertDeliveryType() {
        if (deliveryType == 1) {
            delivery = handleF2FButton();
        } else if (deliveryType == 2) {
            delivery = handleOnlineButton();
        } else if (deliveryType == 3) {
            delivery = handleBlendedButton();
        }
    }

    public void convertCategoryType() {
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
    }

    /* Methods to be able to edit the corresponding columns of Week table */
    public void changeSubjectCellEvent(TableColumn.CellEditEvent editedCell) {
        selectedItem = weekTable.getSelectionModel().getSelectedItem();
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
}


