package gr.upatras.ceid.application.database_models;

public class Project {

    // ----------------------------------------- Instance Field ----------------------------------------

    private final Integer idNumber;
    private final String employeeUsername, description, url;

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Project(Integer idNumber, String employeeUsername, String description, String url) {
        this.idNumber = idNumber;
        this.employeeUsername = employeeUsername;
        this.description = description;
        this.url = url;
    }

    // Overrides the toString method
    @Override
    public String toString() {
        return description;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public Integer getIdNumber() {
        return idNumber;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

}
