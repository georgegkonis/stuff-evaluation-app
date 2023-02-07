package gr.upatras.ceid.application.database_models;

import gr.upatras.ceid.application.database_connection.company_table_connection.CompanyLoader;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Evaluator extends User {

    // ----------------------------------------- Instance Field ----------------------------------------

    private final String companyVatNumber;
    private final Integer expYears;
    private final Company company;

    private final List<Position> positions = new ArrayList<>();
    private final List<Evaluation> evaluations = new ArrayList<>();

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Evaluator(String username, String password, String firstName, String lastName, String email, Timestamp regDate, String companyVatNumber, Integer expYears) {
        super(username, password, firstName, lastName, email, regDate);
        super.userType = UserType.EVALUATOR;
        this.companyVatNumber = companyVatNumber;
        this.expYears = expYears;
        company = loadCompany();
    }

    // Loads the company the evaluator works for
    private Company loadCompany() {
        CompanyLoader companyLoader = new CompanyLoader();
        return companyLoader.loadCompany(companyVatNumber);
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

    public List<Position> getPositions() {
        return positions;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }
}
