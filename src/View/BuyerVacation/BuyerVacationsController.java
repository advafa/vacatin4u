package View.BuyerVacation;

import App.Order;
import App.Package;
import App.User;
import Main.ViewModel;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class BuyerVacationsController implements Initializable {

//true=approve, false=decline
    /// getUserNameByEmail

    public TableView<Order> SaleRequstTable;
    public TableColumn<Order, String> colVacationId;
    public TableColumn<Order, String> colSellerName;
    public TableColumn<Order, String> colRequestStatus;
    private ObservableList<Order> SaleRequst;

//    public Button details;
//    public Button buy;

    private Order clickedRow;

    private Boolean chooseRow = false;

    private ViewModel viewModel;
    private User user;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        user = viewModel.getUser();
        if (this.viewModel.isUserExists(user)) {


            colVacationId.setCellValueFactory(new PropertyValueFactory<>("startDate"));
            colSellerName.setCellValueFactory(new PropertyValueFactory<>("endDate"));
            colRequestStatus.setCellValueFactory(new PropertyValueFactory<>("description"));

            SaleRequst = FXCollections.observableArrayList();

            SaleRequstTable.setRowFactory(tv -> {
                TableRow<Order> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

                        this.clickedRow = row.getItem();
                    }
                });
                return row;
            });

        } else {
            this.viewModel.popAlertinfo("Please Sign in!");
            this.viewModel.goToSignIn();
        }


    }


    public void goToDetails(MouseEvent mouseEvent) {
        viewModel.goToBuyerVacationDetails(this.clickedRow);

    }

    public void goToPay(MouseEvent mouseEvent) {
    }



    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void loadBuyerVacations(User user) {
        SaleRequstTable.setItems(FXCollections.observableArrayList());
        SaleRequst = FXCollections.observableArrayList();
        List<Order> SaleRequstList = viewModel.getOrdersByBuyer_email();
        for (Order ord : SaleRequstList) {
            SaleRequst.add(ord);
        }
        SaleRequstTable.setItems(SaleRequst);
    }

    public void setUser(User user) {
        this.user = user;
    }

}


