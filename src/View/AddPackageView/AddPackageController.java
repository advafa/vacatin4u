package View.AddPackageView;

import Main.ViewModel;
import View.AbstractController;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.List;

public class AddPackageController extends AbstractController{


    private String seller_email;
    private int vacation_id;

    private DatePicker checkin;
    private DatePicker checkout;

    private ComboBox<String> from;
    private ComboBox<String> to;

    private JFXTextField airline;
    private javafx.scene.control.CheckBox back_flight;
    private JFXTextField hand_bag;
    private JFXTextField checked_bag;
    private JFXTextField num_of_tickets;
    private JFXTextField original_price;
    private JFXTextField sale_price;
    private JFXTextField off;

    private ComboBox<String> connec_flight;
    private ComboBox<String> vacation_type;
    private ComboBox<String> ticket_type;
    private ComboBox<String> hotel_type;
    private ComboBox<String> hotel_raiting;

    private javafx.scene.control.CheckBox hotel;
    private javafx.scene.control.CheckBox separately;

    private ViewModel viewModel;





//int foo = Integer.parseInt(myString);



    private DatePicker end_date;
    private DatePicker start_date;
    ComboBox<String> package_cancelation_policiy;




    private boolean IsInputLegal() {
        if (!isEndDayLegal() || !isStartDayLegal()) {
            if (!isStartDayLegal()) {
                this.printMessageToUser("Start day is not legal. it most be not before today");
                return false;
            } else if (!isEndDayLegal()) {
                this.printMessageToUser("End day is not legal. it most be after start day");
                return false;
            }
        }

        return true;
    }


    public void setViewModel(ViewModel viewModel) {this.viewModel = viewModel; }


    public void addNewPackage(MouseEvent mouseEvent) {
        viewModel.savePackage();
        viewModel.goToUserView();
    }

    private boolean isEndDayLegal() {
        return this.end_date.getValue() != null && this.end_date.getValue().isAfter(this.start_date.getValue());
    }

    private boolean isStartDayLegal() {
        return this.end_date.getValue() != null && this.end_date.getValue() != null &&
                this.start_date.getValue().isAfter(LocalDate.now().minusDays(1));
    }

    private void printMessageToUser(String messageContent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Something went wrong");
        alert.setContentText(messageContent);
        alert.showAndWait();
    }


}
