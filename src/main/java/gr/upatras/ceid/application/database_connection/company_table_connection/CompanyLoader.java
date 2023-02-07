package gr.upatras.ceid.application.database_connection.company_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static application.Constants.COMPANY;

public class CompanyLoader extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Loads the company with the matching vat number from the database
    public Company loadCompany(String vatNum) {
        Company company = null;
        try {
            String query = "SELECT * FROM company WHERE vat_num = '%s';".formatted(vatNum);
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                company = createCompanyModel(result);
                setSuccessful();
            } else {
                setUnsuccessful();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
        return company;
    }

    // Loads all the companies from the database and adds them to an arraylist
    public Map<String, Company> loadAllCompanies() {
        Map<String, Company> companies = new HashMap<>();
        try {
            String query = "SELECT * FROM company;";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Company company = createCompanyModel(result);
                companies.put(company.getVatNum(), company);
                System.out.println(companies);
            }
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
        return companies;
    }

    // Creates an object of the company class
    private Company createCompanyModel(ResultSet result) throws SQLException {
        String vatNumber = result.getString(COMPANY.VAT_NUM);
        String taxOffice = result.getString(COMPANY.TAX_OFFICE);
        String name = result.getString(COMPANY.NAME);
        String country = result.getString(COMPANY.COUNTRY);
        String city = result.getString(COMPANY.CITY);
        String street = result.getString(COMPANY.STREET);
        Integer streetNumber = result.getInt(COMPANY.STREET_NUM);
        Long phoneNumber = result.getLong(COMPANY.PHONE_NUM);
        return new Company(vatNumber, taxOffice, name, country, city, street, streetNumber, phoneNumber);
    }

}
