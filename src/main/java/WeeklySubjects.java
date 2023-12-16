import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WeeklySubjects {
    private String weekColumn;
    private String  subjectColumn;
    private String reqColumn;
    public Controller controller;
    public WeeklySubjects(String weekColumn) {
        this.weekColumn = weekColumn;
    }

    public WeeklySubjects(String weekColumn, String subjectColumn, String reqColumn) {
        this.weekColumn = weekColumn;
        this.subjectColumn = subjectColumn;
        this.reqColumn = reqColumn;
    }
    public WeeklySubjects(){

    }
    public WeeklySubjects(Controller controller) {
        this.controller = controller;
    }
    public WeeklySubjects(String subjectColumn, String reqColumn) {
        this.subjectColumn = subjectColumn;
        this.reqColumn = reqColumn;
    }
    public WeeklySubjects(WeeklySubjects weeklySubjects){
        this.weekColumn = weeklySubjects.weekColumn;
        this.subjectColumn = weeklySubjects.subjectColumn;
        this.reqColumn = weeklySubjects.reqColumn;
    }

    public String getWeekColumn() {
        return weekColumn;
    }

    public void setWeekColumn(String weekColumn) {
        this.weekColumn = weekColumn;
    }

    public String getSubjectColumn() {
        return subjectColumn;
    }

    public void setSubjectColumn(String subjectColumn) {
        this.subjectColumn = subjectColumn;
    }

    public String getReqColumn() {
        return reqColumn;
    }

    public void setReqColumn(String  reqColumn) {
        this.reqColumn = reqColumn;
    }

//    void loadData() {
//        controller.connection = SQLConnection.Connector();
//        controller.weeklyScheduleTable.setItems((ObservableList<WeeklySubjects>) controller.subjects);
//            try {
//                PreparedStatement preparedStatement = controller.connection.prepareStatement("SELECT * FROM WeeklySchedule");
//                ResultSet resultSet = preparedStatement.executeQuery();
//
//                while (resultSet.next()) {
//                    WeeklySubjects newSubject = new WeeklySubjects();
//                    newSubject.setWeekColumn(resultSet.getString("WeekNumber"));
//                    newSubject.setSubjectColumn(resultSet.getString("Subjects"));
//                    newSubject.setReqColumn(resultSet.getString("RequiredMaterials"));
//                    boolean isNewOrNot = true;
//                    for (WeeklySubjects weeklySubjects : subjectsList) {
//                        if (weeklySubjects.getWeekColumn().equals(newSubject.getWeekColumn())) {
//                            if (weeklySubjects.getSubjectColumn().equals(newSubject.getSubjectColumn()) || weeklySubjects.getReqColumn().equals(newSubject.getReqColumn())) {
//                                isNewOrNot = false;
//                                break;
//                            }
//                        }
//                    }
//                    if (isNewOrNot) {
//                        subjectsList.add(newSubject);
//                    }
//                }
//                Platform.runLater(()-> controller.weeklyScheduleTable.refresh());
//                Thread.sleep(1000);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//
//            try {
//                controller.connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
    }
