package gr.upatras.ceid.application;

public final class Constants {

    /*
    public static final class USER {
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
        public static final String REG_DATE = "reg_date";
        public static final String USER_TYPE = "user_type";
    }

    public static final class EMPLOYEE {
        public static final String USERNAME = "user_username";
        public static final String COMPANY = "company_vat_number";
        public static final String EXP_YEARS = "exp_years";
        public static final String RESUME = "resume";
        public static final String AWARDS = "awards";
        public static final String CERTIFICATES = "certificates";
        public static final String REFERENCES = "references";
    }

    public static final class MANAGER {
        public static final String USERNAME = "user_username";
        public static final String COMPANY = "company_vat_number";
        public static final String EXP_YEARS = "exp_years";
    }

    public static final class EVALUATOR {
        public static final String USERNAME = "user_username";
        public static final String COMPANY = "company_vat_number";
        public static final String EXP_YEARS = "exp_years";
    }

    public static final class ADMIN {
        public static final String USERNAME = "user_username";
    }

    public static final class EVALUATION {
        public static final String ID_NUM = "id_num";
        public static final String JOB_ID_NUM = "position_id_num";
        public static final String EMPLOYEE_USERNAME = "employee_username";
        public static final String COMMENT = "comment";
        public static final String EVALUATOR_GRADE = "evaluator_grade";
        public static final String MANAGER_GRADE = "manager_grade";
        public static final String RESUME_GRADE = "resume_grade";
        public static final String FINAL_GRADE = "final_grade";
        public static final String STATUS = "status";
    }

    public static final class POSITION_FIELD {
        public static final String JOB_ID_NUM = "job_id_num";
        public static final String FIELD_TITLE = "field_title";
    }

    public static final class EMPLOYEE_POSITION {
        public static final String EMPLOYEE_USERNAME = "employee_username";
        public static final String JOB_ID_NUM = "job_id_num";
    }
     */

    public static final class COMPANY {
        public static final String VAT_NUM = "vat_num";
        public static final String NAME = "name";
        public static final String TAX_OFFICE = "tax_office";
        public static final String COUNTRY = "country";
        public static final String CITY = "city";
        public static final String STREET = "street";
        public static final String STREET_NUM = "street_num";
        public static final String PHONE_NUM = "phone_num";
    }

    public static final class POSITION {
        public static final String ID_NUM = "id_num";
        public static final String COMPANY_VAT_NUM = "company_vat_num";
        public static final String EVALUATOR_USERNAME = "evaluator_username";
        public static final String TITLE = "title";
        public static final String HEADQUARTERS = "headquarters";
        public static final String SALARY = "salary";
        public static final String START_DATE = "start_date";
        public static final String SUBMISSION_DATE = "submission_date";
        public static final String ANNOUNCEMENT_DATE = "announcement_timestamp";
    }

    public static final class FIELD {
        public static final String TITLE = "title";
        public static final String PARENT_TITLE = "parent_title";
        public static final String DESCRIPTION = "description";
    }

    public static final class PROJECT {
        public static final String ID_NUM = "id_num";
        public static final String EMPLOYEE_USERNAME = "employee_username";
        public static final String DESCRIPTION = "description";
        public static final String URL = "url";
    }

    public static final class DEGREE {
        public static final String TITLE = "title";
        public static final String INSTITUTION = "institution";
        public static final String LEVEL = "level";
    }

    public static final class EMPLOYEE_DEGREE {
        public static final String EMPLOYEE_USERNAME = "employee_username";
        public static final String DEGREE_TITLE = "degree_title";
        public static final String DEGREE_INSTITUTION = "degree_institution";
        public static final String GRAD_YEAR = "grad_year";
        public static final String GRADE = "grade";
    }

    public static final class EMPLOYEE_LANGUAGE {
        public static final String EMPLOYEE_USERNAME = "employee_username";
        public static final String LANGUAGE = "language";
    }

    public static final class ACTION_LOG {
        public static final String ID_NUM = "id_num";
        public static final String USERNAME = "user_username";
        public static final String TIMESTAMP = "timestamp";
        public static final String SUCCESS = "success";
        public static final String ACTION_TYPE = "action_type";
        public static final String TABLE_NAME = "table_name";
    }
}

























