public class AssessmentSemAct {

    private String semesterActivitiesColumn;
    private String numberColumn;
    private String weightingColumn;
    private String l01Column;
    private String l02Column;
    private String l03Column;
    private String l04Column;
    private String l05Column;
    private String l06Column;
    private String l07Column;

    public AssessmentSemAct(String semesterActivitiesColumn) {
        this.semesterActivitiesColumn = semesterActivitiesColumn;
        // this.semesterActColumn = semesterActColumn;
    }
    public AssessmentSemAct(){

    }
    public AssessmentSemAct(AssessmentSemAct assessmentSemAct){
        this.semesterActivitiesColumn = assessmentSemAct.semesterActivitiesColumn;
        this.numberColumn = assessmentSemAct.numberColumn;
        this.weightingColumn = assessmentSemAct.weightingColumn;
        this.l01Column = assessmentSemAct.l01Column;
        this.l02Column = assessmentSemAct.l02Column;
        this.l03Column = assessmentSemAct.l03Column;
        this.l04Column = assessmentSemAct.l04Column;
        this.l05Column = assessmentSemAct.l05Column;
        this.l06Column = assessmentSemAct.l06Column;
        this.l07Column = assessmentSemAct.l07Column;
    }

    public String getSemesterActivitiesColumn() {
        return semesterActivitiesColumn;
    }

    public void setSemesterActivitiesColumn(String semesterActivitiesColumn) {
        this.semesterActivitiesColumn = semesterActivitiesColumn;
    }

    public String getNumberColumn() {
        return numberColumn;
    }

    public void setNumberColumn(String numberColumn) {
        this.numberColumn = numberColumn;
    }

    public String getWeightingColumn() {
        return weightingColumn;
    }

    public void setWeightingColumn(String weightingColumn) {
        this.weightingColumn = weightingColumn;
    }

    public String getL01Column() {
        return l01Column;
    }

    public void setL01Column(String l01Column) {
        this.l01Column = l01Column;
    }

    public String getL02Column() {
        return l02Column;
    }

    public void setL02Column(String l02Column) {
        this.l02Column = l02Column;
    }

    public String getL03Column() {
        return l03Column;
    }

    public void setL03Column(String l03Column) {
        this.l03Column = l03Column;
    }

    public String getL04Column() {
        return l04Column;
    }

    public void setL04Column(String l04Column) {
        this.l04Column = l04Column;
    }

    public String getL05Column() {
        return l05Column;
    }

    public void setL05Column(String l05Column) {
        this.l05Column = l05Column;
    }

    public String getL06Column() {
        return l06Column;
    }

    public void setL06Column(String l06Column) {
        this.l06Column = l06Column;
    }

    public String getL07Column() {
        return l07Column;
    }

    public void setL07Column(String l07Column) {
        this.l07Column = l07Column;
    }
}
