package gr.upatras.ceid.application.database_connection.company_table_connection;

import application.database_connection.DatabaseConnector;

import java.sql.SQLException;

public class CompanyRegistrar extends DatabaseConnector {

    // -------------------------------------------- Methods --------------------------------------------

    // Registers a new company in the database
    public void registerCompany(String vatNum, String taxOffice, String name, String country, String city, String street, Integer streetNum, Long phoneNum) {
        try {
            String query = "INSERT INTO company (vat_num, name, tax_office, country, city, street, street_num, phone_num) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');".formatted(vatNum, name, taxOffice, country, city, street, streetNum, phoneNum);
            statement.executeUpdate(query);
            setSuccessful();
        } catch (SQLException exception) {
            exception.printStackTrace();
            setUnsuccessful();
        } finally {
            closeConnection();
        }
    }

}
