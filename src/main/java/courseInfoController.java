import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

import java.sql.SQLException;

public class courseInfoController{
    public Controller controller;

    public courseInfoController(Controller controller) {
        this.controller = controller;
    }
    void loadData() {
        controller.connection = SQLConnection.Connector();

        controller.courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        controller.nameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        controller.semesterCol.setCellValueFactory(new PropertyValueFactory<>("courseSemester"));
        controller.theoryCol.setCellValueFactory(new PropertyValueFactory<>("theoryTime"));
        controller.labCol.setCellValueFactory(new PropertyValueFactory<>("labTime"));
        controller.creditsCol.setCellValueFactory(new PropertyValueFactory<>("courseCredit"));
        controller.ectsCol.setCellValueFactory(new PropertyValueFactory<>("courseECTS"));
        controller.prerequisitesCol.setCellValueFactory(new PropertyValueFactory<>("prerequisites"));
        controller.languageCol.setCellValueFactory(new PropertyValueFactory<>("courseLang"));
        controller.typeCol.setCellValueFactory(new PropertyValueFactory<>("courseType"));
        controller.teachingMethodsCol.setCellValueFactory(new PropertyValueFactory<>("teachingMethods"));
        controller.objectivesCol.setCellValueFactory(new PropertyValueFactory<>("courseObj"));
        controller.outcomesCol.setCellValueFactory(new PropertyValueFactory<>("outcomes"));
        controller.descriptionCol.setCellValueFactory(new PropertyValueFactory<>("courseDesc"));
        controller.categoryCol.setCellValueFactory(new PropertyValueFactory<>("courseCategory"));
        controller.levelCol.setCellValueFactory(new PropertyValueFactory<>("courseLevel"));
        controller.coordinatorCol.setCellValueFactory(new PropertyValueFactory<>("courseCoordinator"));
        controller.lecturersCol.setCellValueFactory(new PropertyValueFactory<>("courseLecturer"));
        controller.assistantsCol.setCellValueFactory(new PropertyValueFactory<>("assistant"));
        controller.readingCol.setCellValueFactory(new PropertyValueFactory<>("reading"));
        controller.textbooksCol.setCellValueFactory(new PropertyValueFactory<>("textbook"));
        controller.deliveryCol.setCellValueFactory(new PropertyValueFactory<>("delivery"));

        try {
                controller.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public void getQuery(String cCode, String cName, String semester, int theory, int lab, int credit, int ECTS, String prerequisites, String language, String type, String teachingM, String objectives, String outcomes, String desc, String category, String level, String coordinator, String lecturer, String assistant, String readings, String textbooks, String delivery) {
        controller.queryAdditionForCourseInfo = "INSERT INTO CourseInfo ('CourseID', 'CourseName', 'CourseSemester', 'TheoryTime', 'LabTime','CourseCredit','CourseECTS', 'Prerequisites', 'CourseLanguage', 'CourseType', 'TeachingMethods', 'CourseObj', 'Outcomes', 'CourseDesc', 'CourseCategory', 'CourseLevel', 'CourseCoordinator', 'CourseLecturer', 'Assistant', 'ReadingInfo', 'TextbookInfo', 'Delivery')" +
                "VALUES ('" + cCode + "','" + cName + "','" + semester + "','" + theory + "','" + lab + "','" + credit + "','" + ECTS + "','" + prerequisites + "','" + language + "','" + type + "','" + teachingM + "','" + objectives + "','" + outcomes + "','" + desc + "','" + category + "','" + level + "','" + coordinator + "','" + lecturer + "','" + assistant + "','" + readings + "','" + textbooks + "','" + delivery + "')";
    }

    public void insertTable() {
        try {
            controller.connection = SQLConnection.Connector();
            controller.preparedStatementAddition = controller.connection.prepareStatement(controller.queryAdditionForCourseInfo);
            controller.preparedStatementAddition.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        // Veritabanı bağlantısını kapat
        try {
            controller.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }
    public boolean controlBlank() {
        if (controller.cName == null || controller.cCode == null || controller.semester == null || controller.semesterValue == null|| controller.languageValue == null|| controller.courseType == null|| controller.levelType == null|| controller.deliveryType == null|| controller.categoryType == null|| controller.languageS == null || controller.type == null || controller.teachingMethods == null || controller.objectives == null || controller.outcomes == null || controller.category == null || controller.level == null || controller.coordinator == null || controller.lecturers == null || controller.delivery == null) {
            Alert notBlank = new Alert(Alert.AlertType.WARNING);
            notBlank.setTitle("Value Selection");
            notBlank.setHeaderText("The required fields cannot be left empty.");
            notBlank.initModality(Modality.APPLICATION_MODAL);
            notBlank.showAndWait();
            return false; // Throw exception if any of these values are null
        } else if (controller.courseCreditsField.getText().isEmpty() || controller.courseECTSField.getText().isEmpty()) {
            Alert notBlank = new Alert(Alert.AlertType.WARNING);
            notBlank.setTitle("Value Selection");
            notBlank.setHeaderText("The text fields of theory time, lab time and credit, ECTS can not be empty.");
            notBlank.initModality(Modality.APPLICATION_MODAL);
            notBlank.showAndWait();
            return false;
        }
        return true;
    }

}
