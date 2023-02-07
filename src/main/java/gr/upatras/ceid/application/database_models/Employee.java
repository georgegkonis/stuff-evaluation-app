package gr.upatras.ceid.application.database_models;

import gr.upatras.ceid.application.database_connection.company_table_connection.CompanyLoader;
import gr.upatras.ceid.application.database_connection.degree_table_connection.DegreeLoader;
import gr.upatras.ceid.application.database_connection.language_table_connection.LanguageLoader;
import gr.upatras.ceid.application.database_connection.position_table_connection.PositionLoader;
import gr.upatras.ceid.application.database_connection.project_table_connection.ProjectLoader;

import java.sql.Timestamp;
import java.util.List;

public class Employee extends User {

    // ------------------------------------------- Enum Field ------------------------------------------

    public enum Language {
        EN("English"), GR("Greek"), SP("Spanish"), FR("French");

        private final String custom;

        Language(String custom) {
            this.custom = custom;
        }

        @Override
        public String toString() {
            return custom;
        }
    }

    // ----------------------------------------- Instance Field ----------------------------------------

    private final String companyVatNumber;
    private final Integer expYears;
    private final Company company;

    private final List<Project> projects;
    private final List<Degree> degrees;
    private final List<Language> languages;
    private final List<Position> positions;

    private String resume, awards, certificates, references;

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Employee(String username, String password, String firstName, String lastName, String email, Timestamp regDate, String companyVatNumber, Integer expYears, String resume, String awards, String certificates, String references) {
        super(username, password, firstName, lastName, email, regDate);
        super.userType = UserType.EMPLOYEE;
        this.companyVatNumber = companyVatNumber;
        this.expYears = expYears;
        this.resume = resume;
        this.awards = awards;
        this.certificates = certificates;
        this.references = references;
        company = loadCompany();
        projects = loadProjects();
        languages = loadLanguages();
        degrees = loadDegrees();
        positions = loadPositions();
    }

    // Loads the company the employee works for
    private Company loadCompany() {
        CompanyLoader companyLoader = new CompanyLoader();
        return companyLoader.loadCompany(companyVatNumber);
    }

    // Loads all the projects the employee has completed
    public List<Project> loadProjects() {
        ProjectLoader projectLoader = new ProjectLoader();
        return projectLoader.loadEmployeeProjects(username);
    }

    // Loads all the degrees the employee has
    public List<Degree> loadDegrees() {
        DegreeLoader degreeLoader = new DegreeLoader();
        return degreeLoader.loadEmployeeDegrees(username);
    }

    // Loads all the projects the employee knows
    public List<Language> loadLanguages() {
        LanguageLoader languageLoader = new LanguageLoader();
        return languageLoader.loadEmployeeLanguages(username);
    }

    // Loads all the positions the employee has requested
    public List<Position> loadPositions() {
        PositionLoader positionLoader = new PositionLoader();
        return positionLoader.loadEmployeePositions(username);
    }

    // ----------------------------------------- Setter Methods ----------------------------------------

    public void setResume(String resume) {
        this.resume = resume;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public String getCompanyVatNumber() {
        return companyVatNumber;
    }

    public Integer getExpYears() {
        return expYears;
    }

    public Company getCompany() {
        return company;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public List<Degree> getDegrees() {
        return degrees;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public String getResume() {
        return resume;
    }

    public String getAwards() {
        return awards;
    }

    public String getCertificates() {
        return certificates;
    }

    public String getReferences() {
        return references;
    }


}
