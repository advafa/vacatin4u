package App;
import java.time.LocalDate;

public class Order {

    private String seller_email;
    private String buyer_email;
    private int vacation_id;
    private Boolean seller_status;//true=approve, false=decline
    private Boolean buyer_status;//true=paid, false=submit

    public Order(String seller_email, String buyer_email, int vacation_id, Boolean buyer_status){
        this.seller_email=seller_email;
        this.buyer_email=buyer_email;
        this.vacation_id=vacation_id;
        this.buyer_status=buyer_status;
    }


    //Get Functions
    public String getSeller_email(){return this.seller_email;}
    public String getBuyer_email(){return this.buyer_email;}
    public int getVacation_id(){return this.vacation_id;}
    public Boolean getSeller_status(){return this.seller_status;}//true=approve, false=decline
    public Boolean getBuyer_status(){return this.buyer_status;}//true=paid, false=submit

    //Set Functions
    public void setSeller_email(String seller_email) {this.seller_email = seller_email;}
    public void setBuyer_email(String buyer_email) {this.buyer_email = buyer_email;}
    public void setVacation_id(int vacation_id) {this.vacation_id = vacation_id;}
    public void setBuyer_status(Boolean buyer_status) {this.buyer_status = buyer_status; }
    public void setSeller_status(Boolean seller_status) {this.seller_status = seller_status;}

}
