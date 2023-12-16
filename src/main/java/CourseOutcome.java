public class CourseOutcome {
    private String sharpColumn;
    private String outcomeColumn;
    private String contColumn;
    private String subContL0;

    public String getSharpColumn() {
        return sharpColumn;
    }

    public CourseOutcome(String sharpColumn, String outcomeColumn) {
        this.sharpColumn = sharpColumn;
        this.outcomeColumn = outcomeColumn;
    }

    public void setSharpColumn(String sharpColumn) {
        this.sharpColumn = sharpColumn;
    }

    public String getOutcomeColumn() {
        return outcomeColumn;
    }

    public void setOutcomeColumn(String outcomeColumn) {
        this.outcomeColumn = outcomeColumn;
    }

    public String getContColumn() {
        return contColumn;
    }

    public void setContColumn(String contColumn) {
        this.contColumn = contColumn;
    }

    public String getSubContL0() {
        return subContL0;
    }

    public void setSubContL0(String subContL0) {
        this.subContL0 = subContL0;
    }
}

