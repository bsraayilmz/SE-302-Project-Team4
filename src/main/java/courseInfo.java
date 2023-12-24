public class courseInfo{
    String courseCode, courseSemester,courseName, prerequisites, teachingMethods,courseType, courseObj, outcomes, courseDesc, courseCoordinator, courseLecturer, assistant, reading, textbook,courseLang, courseCategory, courseLevel, delivery;;
    int theoryTime,labTime, courseCredit, courseECTS;

    public courseInfo(String courseCode, String courseName, String courseSemester, int theoryTime, int labTime, int courseCredit, int courseECTS, String prerequisites, String courseLang, String courseType, String teachingMethods, String courseObj, String outcomes, String courseDesc, String courseCategory, String courseLevel, String courseCoordinator, String courseLecturer, String assistant, String reading, String textbook, String delivery) {
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
        this.courseLevel = courseLevel;
        this.courseCoordinator = courseCoordinator;
        this.courseLecturer = courseLecturer;
        this.assistant = assistant;
        this.reading = reading;
        this.textbook = textbook;
        this.delivery = delivery;
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

    public String getCourseSemester() {
        return courseSemester;
    }

    public void setCourseSemester(String courseSemester) {
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

    public String getCourseLang() {
        return courseLang;
    }

    public void setCourseLang(String courseLang) {
        this.courseLang = courseLang;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(String courseCategory) {
        this.courseCategory = courseCategory;
    }

    public String getCourseCoordinator() {
        return courseCoordinator;
    }

    public void setCourseCoordinator(String courseCoordinator) {
        this.courseCoordinator = courseCoordinator;
    }

    public String getCourseLecturer() {
        return courseLecturer;
    }

    public void setCourseLecturer(String courseLecturer) {
        this.courseLecturer = courseLecturer;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public String getTextbook() {
        return textbook;
    }

    public void setTextbook(String textbook) {
        this.textbook = textbook;
    }

    public String getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
