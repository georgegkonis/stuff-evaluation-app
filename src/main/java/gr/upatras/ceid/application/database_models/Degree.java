package gr.upatras.ceid.application.database_models;

import java.time.Year;

public class Degree {

    // ------------------------------------------- Enum Field ------------------------------------------

    public enum Level {
        ASSOCIATE, BACHELORS, MASTERS, DOCTORAL
    }

    // ----------------------------------------- Instance Field ----------------------------------------

    private final String title, institution;
    private final Level level;

    private Float grade;
    private Year gradYear;

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Degree(String title, String institution, Level level) {
        this.title = title;
        this.institution = institution;
        this.level = level;
    }

    // Constructor
    public Degree(String title, String institution, Level level, Float grade, Year gradYear) {
        this.title = title;
        this.institution = institution;
        this.level = level;
        this.grade = grade;
        this.gradYear = gradYear;
    }

    // Overrides the toString method
    @Override
    public String toString() {
        return title + " | " + level;
    }

    // ----------------------------------------- Setter Methods ----------------------------------------

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public void setGradYear(Year gradYear) {
        this.gradYear = gradYear;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public String getTitle() {
        return title;
    }

    public String getInstitution() {
        return institution;
    }

    public Level getLevel() {
        return level;
    }

    public Float getGrade() {
        return grade;
    }

    public Year getGradYear() {
        return gradYear;
    }

}
