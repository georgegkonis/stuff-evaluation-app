package gr.upatras.ceid.application.database_models;

import gr.upatras.ceid.application.database_connection.field_table_connection.FieldLoader;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Position {

    // ----------------------------------------- Instance Field ----------------------------------------

    private final Integer idNum;
    private final String evaluatorUsername, companyVatNum;
    private final Timestamp announcementTimestamp;

    private Date startDate, submissionDate;
    private Float salary;
    private String title, headquarters;

    private final List<Field> fields = new ArrayList<>();

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Position(Integer idNum, String evaluatorUsername, String companyVatNum, Timestamp announcementTimestamp, Date startDate, Date submissionDate, Float salary, String title, String headquarters) {
        this.idNum = idNum;
        this.evaluatorUsername = evaluatorUsername;
        this.companyVatNum = companyVatNum;
        this.announcementTimestamp = announcementTimestamp;
        this.startDate = startDate;
        this.submissionDate = submissionDate;
        this.salary = salary;
        this.title = title;
        this.headquarters = headquarters;
        loadFields();
    }

    // TODO needs comment
    public void loadFields() {
        fields.clear();
        FieldLoader fieldLoader = new FieldLoader();
        fields.addAll(fieldLoader.loadPositionFields(idNum));
    }

    // Overrides the toString method
    @Override
    public String toString() {
        return title;
    }

    // ----------------------------------------- Setter Methods ----------------------------------------

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public Integer getIdNum() {
        return idNum;
    }

    public String getEvaluatorUsername() {
        return evaluatorUsername;
    }

    public String getCompanyVatNum() {
        return companyVatNum;
    }

    public Timestamp getAnnouncementTimestamp() {
        return announcementTimestamp;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public Float getSalary() {
        return salary;
    }

    public String getTitle() {
        return title;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public List<Field> getFields() {
        return fields;
    }

}
