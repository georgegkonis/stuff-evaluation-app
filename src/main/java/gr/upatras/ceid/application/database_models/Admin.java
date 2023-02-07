package gr.upatras.ceid.application.database_models;

import java.sql.Timestamp;

public class Admin extends User {

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Admin(String username, String password, String firstName, String lastName, String email, Timestamp regDate) {
        super(username, password, firstName, lastName, email, regDate);
        super.userType = UserType.ADMIN;
    }

}
