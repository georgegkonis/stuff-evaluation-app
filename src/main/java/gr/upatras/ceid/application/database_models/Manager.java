package gr.upatras.ceid.application.database_models;

import gr.upatras.ceid.application.database_connection.company_table_connection.CompanyLoader;
import gr.upatras.ceid.application.database_connection.user_table_connection.employee_table_connection.EmployeeLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Manager extends User {

    // ----------------------------------------- Instance Field ----------------------------------------

    private final String companyVatNumber;
    private final Integer expYears;
    private final Company company;

    private final List<Employee> employees = new ArrayList<>();
    private final List<Position> positions = new ArrayList<>();
    private final List<Evaluation> evaluations = new ArrayList<>();

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Manager(String username, String password, String firstName, String lastName, String email, Timestamp regDate, String companyVatNumber, Integer expYears) {
        super(username, password, firstName, lastName, email, regDate);
        super.userType = UserType.MANAGER;
        this.companyVatNumber = companyVatNumber;
        this.expYears = expYears;
        company = loadCompany();
        loadEmployees();
    }

    // Loads the company the manager works for
    private Company loadCompany() {
        CompanyLoader companyLoader = new CompanyLoader();
        return companyLoader.loadCompany(companyVatNumber);
    }

    // Loads all the employees of the company the manager works for
    public void loadEmployees() {
        employees.clear();
        EmployeeLoader employeeLoader = new EmployeeLoader();
        employees.addAll(employeeLoader.loadCompanyEmployees(companyVatNumber));
    }

    // TODO
    public void loadPositions() {

    }

    // TODO
    public void loadEvaluations() {

    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public String getCompanyVatNum() {
        return companyVatNumber;
    }

    public Integer getExpYears() {
        return expYears;
    }

    public Company getCompany() {
        return company;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

}
