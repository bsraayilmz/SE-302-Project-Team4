

public class WeeklySubjects {
    private String weekColumn;
    private String  subjectColumn;
    private String reqColumn;
    public WeeklySubjects(String weekColumn) {
        this.weekColumn = weekColumn;
    }

    public WeeklySubjects(){

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
    }
