package gr.upatras.ceid.application.database_models;

public class Field {

    // ----------------------------------------- Instance Field ----------------------------------------

    private final String title, description, parentTitle;

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Field(String title, String description, String parentTitle) {
        this.title = title;
        this.description = description;
        this.parentTitle = parentTitle;
    }

    // Overrides the toString method
    @Override
    public String toString() {
        return title;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getParentTitle() {
        return parentTitle;
    }

}
