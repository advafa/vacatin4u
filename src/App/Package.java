package App;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Package {

    private static int vacation_count;
    private boolean vacation_status;

    private String seller_email;
    private int vacation_id;

    private String from;
    private String to;
    private LocalDate checkin;
    private LocalDate checkout;

    private String airline;
    private boolean back_flight;

    private String hand_bag;
    private String checked_bag;

    private String connec_flight;

    private String vacation_type;
    private String ticket_type;

    private boolean hotel;
    private String hotel_type;
    private int hotel_raiting;

    private int num_of_tickets;
    private boolean separately;

    private int original_price;
    private int sale_price;
    private int off;





    public Package(String seller_email, int vacation_id, String from, String to,
                    LocalDate checkin, LocalDate checkout, String airline, boolean back_flight,
                     String hand_bag, String checked_bag, String connec_flight, String vacation_type,
                   String ticket_type, boolean hotel, String hotel_type, int hotel_raiting, int num_of_tickets,
                    boolean separately, int original_price, int sale_price, int off){

        this.vacation_status=true;//true=available for sale, false=sold out

        this.seller_email= seller_email;
        this.vacation_id=vacation_id;

        this.from= from;
        this.to= to;
        this.checkin= checkin;
        this.checkout=checkout;

        this.airline= airline;
        this.back_flight= back_flight;

        this.hand_bag= hand_bag;
        this.checked_bag= checked_bag;

        this.connec_flight= connec_flight;

        this.vacation_type= vacation_type;
        this.ticket_type= ticket_type;

        this.hotel= hotel;
        this.hotel_type= hotel_type;
        this.hotel_raiting= hotel_raiting;

        this.num_of_tickets= num_of_tickets;
        this.separately= separately;

        this.original_price= original_price;
        this.sale_price= sale_price;
        this.off= off;
        this.vacation_count=this.vacation_count++;
    }

    public Package(String seller_email, int vacation_id, String checkin, String checkout) {

        this.seller_email=seller_email;
        this.vacation_id=vacation_id;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.checkin = LocalDate.parse(checkin, formatter);
        this.checkout = LocalDate.parse(checkout, formatter);;
    }

    public Package(String from, String to,String checkin, String checkout) {

        this.from=from;
        this.to=to;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.checkin = LocalDate.parse(checkin, formatter);
        this.checkout = LocalDate.parse(checkout, formatter);
    }


    public Package(String from, String to,LocalDate checkin, LocalDate checkout) {

        this.from=from;
        this.to=to;
        this.checkin = checkin;
        this.checkout = checkout;
    }


    //Get Functions
    public boolean getVacation_status(){return this.vacation_status;}
    public String getSeller(){return this.seller_email;}
    public int getVacation_id(){return this.vacation_id;}
    public String getFrom(){return this.from;}
    public String getto(){return this.to;}
    public LocalDate getCheckin(){return this.checkin;}
    public LocalDate getCheckout(){return this.checkout;}
    public String getAirline(){return this.airline;}
    public boolean getBackFlight(){return this.back_flight;}
    public String getHand_bag(){return this.hand_bag;}
    public String getChecked_bag(){return this.checked_bag;}
    public String getConnec_flight(){return this.connec_flight;}
    public String getVacation_type(){return this.vacation_type;}
    public String getTicket_type(){return this.ticket_type;}
    public boolean getHotel(){return this.hotel;}
    public String getHotel_type(){return this.hotel_type;}
    public int getHotel_raiting(){return this.hotel_raiting;}
    public int getNum_of_tickets(){return this.num_of_tickets;}
    public boolean getSeparately(){return this.separately;}
    public int getOriginal_price(){return this.original_price;}
    public int getSale_price(){return this.sale_price;}
    public  int getOff(){return this.off;}


    //Set Functions

    public void setVacation_status(boolean vacation_status){this.vacation_status=vacation_status;}
    public void setSeller(String seller_email){this.seller_email= seller_email;}
    public void setVacation_id(int vacation_id){this.vacation_id=vacation_id;}
    public void setFrom(String from){this.from= from;}
    public void setToo(String to){this.to= to;}
    public void setCheckin(LocalDate checkin){this.checkin= checkin;}
    public void setCheckout(LocalDate checkout){this.checkout=checkout;}
    public void setAirline(String airline){this.airline= airline;}
    public void setBackFlight(boolean back_flight){this.back_flight= back_flight;}
    public void setHand_bag(String hand_bag){this.hand_bag= hand_bag;}
    public void setChecked_bag(String checked_bag){this.checked_bag= checked_bag;}
    public void setConnec_flight(String connec_flight){this.connec_flight= connec_flight;}
    public void setVacation_type(String vacation_type){this.vacation_type= vacation_type;}
    public void setTicket_type(String ticket_type){this.ticket_type= ticket_type;}
    public void setHotel(boolean hotel){this.hotel= hotel;}
    public void setHotel_type(String hotel_type){this.hotel_type= hotel_type;}
    public void setHotel_raiting(int hotel_raiting){ this.hotel_raiting= hotel_raiting;}
    public void setNum_of_tickets(int num_of_tickets){this.num_of_tickets= num_of_tickets;}
    public void setSeparately(boolean separately){this.separately= separately;}
    public void setOriginal_price(int original_price){this.original_price= original_price;}
    public void setSale_price(int sale_price){this.sale_price= sale_price;}
    public  void setOff(int off){this.off= off;}


    //toString Functions
    public String toStringCheckin () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return this.checkin.format(formatter);
    }

    public String toStringCheckout () {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return this.checkout.format(formatter);
    }

    }