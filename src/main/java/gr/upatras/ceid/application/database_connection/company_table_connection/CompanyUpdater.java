package gr.upatras.ceid.application.database_connection.company_table_connection;

import application.database_connection.DatabaseConnector;
import application.database_models.Company;

import java.sql.SQLException;

public class CompanyUpdater extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Updates the specified company in the database
    public void updateCompany(Company company, String country, String city, String street, Integer streetNum, Long phoneNum) {
        if (existsInDatabase("company", "vat_num", company.getVatNum())) {
            try {
                String query = "UPDATE company SET country = '%s', city = '%s', street = '%s', street_num = '%s', phone_num = '%s' WHERE vat_num = '%s';".formatted(country, city, street, streetNum, phoneNum, company.getVatNum());
                statement.executeUpdate(query);
                company.setAll(country, city,  street, streetNum, phoneNum);
                setSuccessful();
            } catch (SQLException exception) {
                exception.printStackTrace();
                setUnsuccessful();
            } finally {
                closeConnection();
            }
        }
    }

}
