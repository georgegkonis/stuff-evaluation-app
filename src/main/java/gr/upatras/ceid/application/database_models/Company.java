package gr.upatras.ceid.application.database_models;

public class Company {

    // ----------------------------------------- Instance Field ----------------------------------------

    private final String vatNum, taxOffice, name;

    private String street, city, country;
    private Integer streetNum;
    private Long phoneNum;

    // -------------------------------------------- Methods --------------------------------------------

    // Constructor
    public Company(String vatNum, String taxOffice, String name, String country, String city, String street, Integer streetNum, Long phoneNum) {
        this.vatNum = vatNum;
        this.taxOffice = taxOffice;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
        this.phoneNum = phoneNum;
    }

    // Overrides the toString method to return the name of the company
    @Override
    public String toString() {
        return name;
    }

    // ----------------------------------------- Setter Methods ----------------------------------------

    public void setAll(String country, String city, String street, Integer streetNum, Long phoneNum) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
        this.phoneNum = phoneNum;
    }

    // ----------------------------------------- Getter Methods ----------------------------------------

    public String getVatNum() {
        return vatNum;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Integer getStreetNum() {
        return streetNum;
    }

    public Long getPhoneNum() {
        return phoneNum;
    }

}
