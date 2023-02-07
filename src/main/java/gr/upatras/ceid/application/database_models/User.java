package gr.upatras.ceid.application.database_models;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public abstract class User {

    // ------------------------------------------- Enum Field ------------------------------------------

    public enum UserType {
        ADMIN, MANAGER, EVALUATOR, EMPLOYEE
    }

    // ----------------------------------------- Instance Field ----------------------------------------

    protected final String username;
    protected final Date regDate;
    protected final Time regTime;

    protected String password, firstName, lastName, email;
    protected UserType userType;

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public User(String username, String password, String firstName, String lastName, String email, Timestamp regTimestamp) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

        String dateTime = String.valueOf(regTimestamp).substring(0, 19);
        String[] parts = dateTime.split(" ");
        regDate = Date.valueOf(parts[0]);
        regTime = Time.valueOf(parts[1]);
    }

    // ----------------------------------------- Setter Methods ----------------------------------------

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public Date getRegDate() {
        return regDate;
    }

    public Time getRegTime() {
        return regTime;
    }

}
