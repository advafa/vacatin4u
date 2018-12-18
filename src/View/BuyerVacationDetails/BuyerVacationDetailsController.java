package View.BuyerVacationDetails;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.util.ResourceBundle;

import App.User;
import App.Package;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;




public class BuyerVacationDetailsController implements Initializable {

    @FXML
    public javafx.scene.control.Label check_in;
    public javafx.scene.control.Label check_out;
    public javafx.scene.control.Label from;
    public javafx.scene.control.Label to;
    public javafx.scene.control.Label airline;
    public javafx.scene.control.Label backf;
    public javafx.scene.control.Label handbag;
    public javafx.scene.control.Label checkbag;
    public javafx.scene.control.Label conectin;
    public javafx.scene.control.Label vac_type;
    public javafx.scene.control.Label tic_type;
    public javafx.scene.control.Label hotel_in;
    public javafx.scene.control.Label hotel_type;
    public javafx.scene.control.Label hotel_raiting;
    public javafx.scene.control.Label num;
    public javafx.scene.control.Label price;
    public javafx.scene.control.Label off;
    public javafx.scene.control.Label sellerStatus;
    public javafx.scene.control.Label vac_status;

    private ViewModel viewModel;
    private User user;
    private Package vacation;
    private Boolean status;

    public Button buy;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if(this.viewModel.isUserExists(user)){

            this.check_in.setText(vacation.toStringCheckin());
            this.check_out.setText(vacation.toStringCheckout());
            this.from.setText(vacation.getFrom());
            this.to.setText(vacation.getto());
            this.airline.setText(vacation.getAirline());

            if(vacation.getBackFlight()){
                this.backf.setText("Back flight Included");}
            else{this.backf.setText("Back flight *NOT* Included");}

            this.handbag.setText(vacation.getHand_bag());
            this.checkbag.setText(vacation.getChecked_bag());
            this.conectin.setText(vacation.getConnec_flight());
            this.vac_type.setText(vacation.getVacation_type());
            this.tic_type.setText(vacation.getTicket_type());


            if(vacation.getHotel()){
                this.hotel_in.setText("Hotel Included:");
                this.hotel_type.setText(vacation.getHotel_type());
                this.hotel_raiting.setText("Hotel Rating: "+ vacation.getHotel_raiting()+" Stars");
            }else{
                this.hotel_in.setText("Hotel *NOT* Included");
                this.hotel_type.setText("");
                this.hotel_raiting.setText("");
            }


            this.num.setText(""+vacation.getNum_of_tickets());
            this.price.setText(vacation.getSale_price()+"$");
            this.off.setText(vacation.getOff()+"%");

            if(vacation.getVacation_status()){
                this.vac_status.setText("Available for sale");}
            else {this.vac_status.setText("Sold out");}

            if(this.status){
            this.sellerStatus.setText("Approved");
            buy.setDisable(false);}
            else{
                this.sellerStatus.setText("Declined");
                buy.setDisable(true);
            }


        }
        else{
            viewModel.popAlerterror("Please Sign in!");
        }
    }


    public void Back (MouseEvent mouseEvent){viewModel.goToBuyerVacations();}

    public void BuyNow (MouseEvent mouseEvent) {

        if (vacation.getVacation_status() && status) {
            viewModel.goToPayPal();
        } else {
            viewModel.popAlerterror("This vacation is not available4U!");
            viewModel.goToBuyerVacations();
        }
    }

    public void setViewModel(ViewModel viewModel) { this.viewModel = viewModel; }
    public  void setVacation(Package vacation){this.vacation=vacation;}
    public  void setUser(User user){this.user=user;}
    public void setSellerStatus(Boolean status){this.status=status;}


//*********  Menu Functions **************///

    private void exitApp(MouseEvent event) {
        System.exit(0);
    }

    public void goToSignIn(MouseEvent mouseEvent) {
        viewModel.goToSignIn();
    }

    public void goToSearch(MouseEvent mouseEvent) {
        viewModel.goToSearchView();
    }


}
