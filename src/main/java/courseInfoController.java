import javafx.fxml.FXML;
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
        controller.refreshTable();

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
    }
    public void getQuery(String cCode, String cName, String semester, int theory, int lab, int credit, int ECTS, String prerequisites, String language, String type, String teachingM, String objectives, String outcomes, String desc, String category) {
        controller.queryAddition = "INSERT INTO CourseInfo ('CourseID', 'CourseName', 'CourseSemester', 'TheoryTime', 'LabTime','CourseCredit','CourseECTS', 'Prerequisites', 'CourseLanguage', 'CourseType', 'TeachingMethods', 'CourseObj', 'Outcomes', 'CourseDesc', 'CourseCategory')" +
                "VALUES ('" + cCode + "','" + cName + "','" + semester + "','" + theory + "','" + lab + "','" + credit + "','" + ECTS + "','" + prerequisites + "','" + language + "','" + type + "','" + teachingM + "','" + objectives + "','" + outcomes + "','" + desc + "','" + category + "')";
    }

    public void insertTable() {
        try {
            controller.connection = SQLConnection.Connector();
            controller.preparedStatementAddition = controller.connection.prepareStatement(controller.queryAddition);
            controller.preparedStatementAddition.execute();
//            courseNameLabel.setText(courseCodeField.getText());
            controller.refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean controlBlank() {
        if (controller.cName == null || controller.cCode == null || controller.semester == null || controller.languageS == null || controller.type == null || controller.teachingMethods == null || controller.objectives == null || controller.outcomes == null || controller.category == null) {
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
