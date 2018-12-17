package App;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class User {

    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private LocalDate birth_date;
    private String city;
    private LocalDate sign_up_date;

    public User(String first_name, String last_name, String email, String password, LocalDate birth_date,String city) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.birth_date= birth_date;
        this.city=city;
        this.sign_up_date= LocalDate.now();
    }

    public User(String first_name, String last_name, String email, String password, LocalDate birth_date,String city,LocalDate sign_up_date) {

        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.birth_date= birth_date;
        this.city=city;
        this.sign_up_date= sign_up_date;
    }

    public User(String email, String password) {

        this.email = email;
        this.password = password;

    }


        public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirth_date(){return birth_date;}

    public void setBirth_date(LocalDate birth_date){this.birth_date=birth_date;}

    public String getCity(){ return city;}

    public void setCity(String city){this.city=city;}

    public LocalDate getSignup_date(){return sign_up_date;}

    public String toStringSignup_date () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return this.sign_up_date.format(formatter);
    }


    public String toStringBirth_date () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return this.birth_date.format(formatter);
    }
}
