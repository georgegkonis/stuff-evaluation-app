package gr.upatras.ceid.application.database_models;

public class Evaluation {

    // ------------------------------------------- Enum Field ------------------------------------------

    public enum EvaluationStatus {
        FINAL, PENDING
    }

    // ----------------------------------------- Instance Field ----------------------------------------

    private final Integer idNum, positionIdNum;
    private final String employeeUsername;

    private String comment;
    private Integer resumeGrade, interviewGrade, managerGrade, finalGrade;
    private EvaluationStatus status;

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Evaluation(Integer idNum, Integer positionIdNum, String employeeUsername, String comment, Integer resumeGrade, Integer interviewGrade, Integer managerGrade, Integer finalGrade, EvaluationStatus status) {
        this.idNum = idNum;
        this.positionIdNum = positionIdNum;
        this.employeeUsername = employeeUsername;
        this.comment = comment;
        this.resumeGrade = resumeGrade;
        this.interviewGrade = interviewGrade;
        this.managerGrade = managerGrade;
        this.finalGrade = finalGrade;
        this.status = status;
    }

    // ----------------------------------------- Setter Methods ----------------------------------------

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setResumeGrade(Integer resumeGrade) {
        this.resumeGrade = resumeGrade;
    }

    public void setInterviewGrade(Integer interviewGrade) {
        this.interviewGrade = interviewGrade;
    }

    public void setManagerGrade(Integer managerGrade) {
        this.managerGrade = managerGrade;
    }

    public void setFinalGrade(Integer finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setStatus(EvaluationStatus status) {
        this.status = status;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public Integer getIdNum() {
        return idNum;
    }

    public Integer getPositionIdNum() {
        return positionIdNum;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public String getComment() {
        return comment;
    }

    public Integer getResumeGrade() {
        return resumeGrade;
    }

    public Integer getInterviewGrade() {
        return interviewGrade;
    }

    public Integer getManagerGrade() {
        return managerGrade;
    }

    public Integer getFinalGrade() {
        return finalGrade;
    }

    public EvaluationStatus getStatus() {
        return status;
    }

}
