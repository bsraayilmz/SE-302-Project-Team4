public class WorkloadSemAct {
    private String semesterActColumn;
    private String numbColumn;
    private String durationColumn;
    private String workloadColumn;

    public WorkloadSemAct(String semesterActColumn){
        this.semesterActColumn = semesterActColumn;
    }

    public String getSemesterActColumn() {
        return semesterActColumn;
    }

    public void setSemesterActColumn(String semesterActColumn) {
        this.semesterActColumn = semesterActColumn;
    }

    public String getNumbColumn() {
        return numbColumn;
    }

    public void setNumbColumn(String numbColumn) {
        this.numbColumn = numbColumn;
    }

    public String getDurationColumn() {
        return durationColumn;
    }

    public void setDurationColumn(String durationColumn) {
        this.durationColumn = durationColumn;
    }

    public String getWorkloadColumn() {
        return workloadColumn;
    }

    public void setWorkloadColumn(String workloadColumn) {
        this.workloadColumn = workloadColumn;
    }
}
