import javafx.fxml.FXML;

import java.sql.SQLException;

public class courseInfo{
    String courseCode, courseName, prerequisites, teachingMethods, courseObj, outcomes, courseDesc;
    int courseSemester, theoryTime, labTime, courseCredit, courseECTS,courseLang, courseType, courseCategory;

    public courseInfo(String courseCode, String courseName, int courseSemester, int theoryTime, int labTime, int courseCredit, int courseECTS, String prerequisites, int courseLang, int courseType, String teachingMethods, String courseObj, String outcomes, String courseDesc, int courseCategory) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.prerequisites = prerequisites;
        this.teachingMethods = teachingMethods;
        this.courseObj = courseObj;
        this.outcomes = outcomes;
        this.courseDesc = courseDesc;
        this.courseSemester = courseSemester;
        this.theoryTime = theoryTime;
        this.labTime = labTime;
        this.courseCredit = courseCredit;
        this.courseECTS = courseECTS;
        this.courseLang = courseLang;
        this.courseType = courseType;
        this.courseCategory = courseCategory;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getTeachingMethods() {
        return teachingMethods;
    }

    public void setTeachingMethods(String teachingMethods) {
        this.teachingMethods = teachingMethods;
    }

    public String getCourseObj() {
        return courseObj;
    }

    public void setCourseObj(String courseObj) {
        this.courseObj = courseObj;
    }

    public String getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(String outcomes) {
        this.outcomes = outcomes;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public int getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(int courseSemester) {
        this.courseSemester = courseSemester;
    }

    public int getTheoryTime() {
        return theoryTime;
    }

    public void setTheoryTime(int theoryTime) {
        this.theoryTime = theoryTime;
    }

    public int getLabTime() {
        return labTime;
    }

    public void setLabTime(int labTime) {
        this.labTime = labTime;
    }

    public int getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(int courseCredit) {
        this.courseCredit = courseCredit;
    }

    public int getCourseECTS() {
        return courseECTS;
    }

    public void setCourseECTS(int courseECTS) {
        this.courseECTS = courseECTS;
    }

    public int getCourseLang() {
        return courseLang;
    }

    public void setCourseLang(int courseLang) {
        this.courseLang = courseLang;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public int getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(int courseCategory) {
        this.courseCategory = courseCategory;
    }
}
