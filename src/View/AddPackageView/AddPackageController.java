package View.AddPackageView;

import App.User;
import App.Package;
import Main.ViewModel;
import View.AbstractController;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddPackageController implements Initializable {


    private String seller_email;

    private DatePicker checkin;
    private DatePicker checkout;

    private ComboBox<String> from;
    private ComboBox<String> to;

    private JFXTextField airline;
    private javafx.scene.control.CheckBox back_flight;
    private ComboBox<String> hand_bag;
    private ComboBox<String> checked_bag;
    private JFXTextField num_of_tickets;
    private JFXTextField original_price;
    private JFXTextField sale_price;


    private ComboBox<String> connec_flight;
    private ComboBox<String> vacation_type;
    private ComboBox<String> ticket_type;
    private ComboBox<String> hotel_type;
    private ComboBox<String> hotel_raiting;

    private javafx.scene.control.CheckBox hotel;
    private javafx.scene.control.CheckBox separately;

    private ViewModel viewModel;
    private User user;

    private Package vacation;


    private int num;
    private int org_p;
    private int sal_p;
    private int off;
    int hotelR;


//int foo = Integer.parseInt(myString);



    private DatePicker end_date;
    private DatePicker start_date;
    ComboBox<String> package_cancelation_policiy;

    public void initialize(URL url, ResourceBundle rb) {

        user= viewModel.getUser();
        if(!this.viewModel.isUserExists(user)){
            this.viewModel.popAlerterror("Please Sign in!");
            this.viewModel.goToSignIn();
        }

    }

public void addVacation(MouseEvent mouseEvent){
try {
    org_p=Integer.parseInt(original_price.getText());
    sal_p=Integer.parseInt(sale_price.getText());
    off=100*(org_p-sal_p)/org_p;
}
catch (Exception e) {
    viewModel.popAlertinfo("Prices must be integer");
}
try{
    num=Integer.parseInt(num_of_tickets.getText());}
catch (Exception e) {
    viewModel.popAlertinfo("Number of tickets must be integer");
}
    if(hotel.isSelected()){
    try{
        hotelR=Integer.getInteger(hotel_raiting.getValue());}
    catch (Exception e) {
        viewModel.popAlertinfo("Hotel raiting of must be integer");
    }} else { hotelR=-1;}

    vacation=new Package (user.getEmail(), from.getValue(),to.getValue() ,checkin.getValue(), checkout.getValue(),
            airline.getText(),back_flight.isSelected(),hand_bag.getValue(),checked_bag.getValue(),
            connec_flight.getValue(), vacation_type.getValue(), ticket_type.getValue(), hotel.isSelected(),
            hotel_type.getValue(),hotelR, num,
            separately.isSelected(), org_p,sal_p,off);


        if(validateform()){
            this.viewModel.addVacation(vacation);
            this.viewModel.popAlertinfo("Your Vacation successfully saved!");
        }
}

    private boolean validateform() {
        if (!validateNotEmpty()) {
            this.viewModel.popAlertinfo("One or more fields is empty!");
            return false;
        }
        if(org_p>sal_p){
            this.viewModel.popAlertinfo("Sale price must by smallest than Original price!");
            return false;
        }
        if(!this.checkin.getValue().isAfter(LocalDate.now().plusDays(1))){
            this.viewModel.popAlertinfo("Invalidate Checkin date. It must be at least one day after today");
        return false;}
        if(!this.checkout.getValue().isAfter(this.checkin.getValue())){
            this.viewModel.popAlertinfo("Invalidate Checkout date. It must be after checkin date");
            return false;
        }
        if(!this.checkout.getValue().isAfter(LocalDate.now())) {
            this.viewModel.popAlertinfo("Invalidate Checkout date. Checkout date already past!");
            return false;
        }
        return true;
    }





    private boolean validateNotEmpty() {
        if (from.getValue() == null || to.getValue()==null) {
            return false;
        }

        if (airline.getText() == null || airline.getText().trim().isEmpty()) {
            return false;
        }


        if (hand_bag.getValue() == null || checked_bag.getValue()==null) {
            return false;
        }

        if (num_of_tickets.getText() == null || num_of_tickets.getText().trim().isEmpty()) {
            return false;
        }

        if (original_price.getText() == null || original_price.getText().trim().isEmpty()) {
            return false;
        }
        if (sale_price.getText() == null || sale_price.getText().trim().isEmpty()) {
            return false;
        }

        if (checkin.getValue() == null || checkout.getValue()==null) {
            return false;
        }
        if (connec_flight.getValue() == null || vacation_type.getValue()==null || ticket_type.getValue() == null) {
            return false;
        }

        if(hotel.isSelected()){
            if (hotel_type.getValue() == null || hotel_raiting.getValue()==null) {
                return false;
            }
        }
        return true;
    }

    public void setViewModel(ViewModel viewModel) {this.viewModel = viewModel; }

}