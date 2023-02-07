package gr.upatras.ceid.application.database_connection.user_table_connection.employee_table_connection;

import gr.upatras.ceid.application.database_connection.DatabaseConnector;
import gr.upatras.ceid.application.database_connection.degree_table_connection.DegreeRegistrar;
import gr.upatras.ceid.application.database_connection.language_table_connection.LanguageRegistrar;
import gr.upatras.ceid.application.database_connection.project_table_connection.ProjectRegistrar;
import application.database_models.Degree;
import application.database_models.Employee;
import application.database_models.Employee.Language;
import application.database_models.Project;

import java.sql.SQLException;
import java.util.List;

public class EmployeeUpdater extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // TODO needs comment
    public void updateEmployee(Employee employee, String resume, String certificates, String references, String awards, List<Language> languages, List<Degree> degrees, List<Project> projects) {
        if (existsInDatabase("employee", "user_username", employee.getUsername())) {
            try {
                String query = "UPDATE employee SET resume = '%s', certificates = '%s', `references` = '%s', awards = '%s' WHERE user_username = '%s';".formatted(resume, certificates, references, awards, employee.getUsername());
                statement.executeUpdate(query);
                updateEmployeeLanguages(employee.getUsername(), languages);
                updateEmployeeDegrees(employee.getUsername(), degrees);
                updateEmployeeProjects(employee.getUsername(), projects);
                employee.setResume(resume);
                employee.setCertificates(certificates);
                employee.setReferences(references);
                employee.setAwards(awards);
                employee.loadLanguages();
                employee.loadDegrees();
                employee.loadProjects();
                setSuccessful();
            } catch (SQLException exception) {
                exception.printStackTrace();
                setUnsuccessful();
            } finally {
                closeConnection();
            }
        }
    }

    // TODO needs comment
    private void updateEmployeeLanguages(String employeeUsername, List<Language> languages) throws SQLException {
        String query = "DELETE FROM employee_language WHERE employee_username = '%s';".formatted(employeeUsername);
        statement.executeUpdate(query);
        for (Language language : languages) {
            LanguageRegistrar languageRegistrar = new LanguageRegistrar();
            languageRegistrar.registerEmployeeLanguage(employeeUsername, language);
        }
    }

    // TODO needs comment
    private void updateEmployeeDegrees(String employeeUsername, List<Degree> degrees) throws SQLException {
        String query = "DELETE FROM employee_degree WHERE employee_username = '%s';".formatted(employeeUsername);
        statement.executeUpdate(query);
        for (Degree degree : degrees) {
            DegreeRegistrar degreeRegistrar = new DegreeRegistrar();
            degreeRegistrar.registerEmployeeDegree(employeeUsername, degree.getTitle(), degree.getInstitution(), degree.getLevel(), degree.getGradYear(), degree.getGrade());
        }
    }

    // TODO needs comment
    private void updateEmployeeProjects(String employeeUsername, List<Project> projects) throws SQLException {
        String query = "DELETE FROM project WHERE employee_username = '%s';".formatted(employeeUsername);
        statement.executeUpdate(query);
        for (Project project : projects) {
            ProjectRegistrar projectRegistrar = new ProjectRegistrar();
            projectRegistrar.registerEmployeeProject(employeeUsername, project.getDescription(), project.getUrl());
        }
    }

}
