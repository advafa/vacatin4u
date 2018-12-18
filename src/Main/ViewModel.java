/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import App.Order;
import App.Package;
import App.User;
import Model.Model;
import View.AddPackageView.AddPackageController;
import View.AddProductView.AddProductController;
import View.BuyerVacationDetails.BuyerVacationDetailsController;
import View.PackageDescriptionView.PackageDescriptionView;
import View.SearchView.SearchViewController;
import View.SignInScreenView.SignInController;
import View.SignUpScreenView.SignUpController;
import View.UserViewScreen.ProductEntry;
import View.UserViewScreen.UserViewController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ViewModel extends Application {

    //init xy offsets
    private double xOffset = 0;
    private double yOffset = 0;
    private Scene signInScene;
    private Scene signUpScene;
    private Model model;
    private Stage stage;
    private Scene userViewScene;
    private Scene addPackageScene;
    private AddPackageController addPackageController;
    private UserViewController userViewController;
    private AddProductController addProductLoaderController;
    private Scene addProductScene;
    private User user = null;
    private Package aPackage;
    private Scene PackageDescriptionView;
    private PackageDescriptionView packageDescriptionViewController;
    private Scene searchView;
    private SearchViewController searchViewController;
    private Scene buyerVacationDetail;
    private BuyerVacationDetailsController buyerVacationDetailViewController;

    private Order orderforSeller;
    private Order orderforBuyer;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader signUpLoader = new FXMLLoader(getClass().getResource("../View/SignUpScreenView/SignUpScreen.fxml"));
        Parent signUpRoot = (Parent) signUpLoader.load();

        FXMLLoader signInLoader = new FXMLLoader(getClass().getResource("../View/SignInScreenView/SignInScreen.fxml"));
        Parent signInRoot = (Parent) signInLoader.load();

        FXMLLoader userViewLoader = new FXMLLoader(getClass().getResource("../View/UserViewScreen/UserView.fxml"));
        Parent userViewRoot = (Parent) userViewLoader.load();

        FXMLLoader addPackageLoader = new FXMLLoader(getClass().getResource("../View/AddPackageView/AddPackage.fxml"));
        Parent addPackageRoot = (Parent) addPackageLoader.load();

        FXMLLoader addProductLoader = new FXMLLoader(getClass().getResource("../View/AddProductView/AddProduct.fxml"));
        Parent addProductRoot = (Parent) addProductLoader.load();

        FXMLLoader PackageDescriptionViewLoader = new FXMLLoader(getClass().getResource("../View/PackageDescriptionView/PackageDescriptionView.fxml"));
        Parent packageDescriptionRoot = (Parent) PackageDescriptionViewLoader.load();

        FXMLLoader searchViewLoader = new FXMLLoader(getClass().getResource("../View/SearchView/SearchScreen.fxml"));
        Parent searchViewRoot = (Parent) searchViewLoader.load();

        FXMLLoader buyerVacationDetailViewLoader = new FXMLLoader(getClass().getResource("../View/BuyerVacationDetails/BuyerVacationDetailsView.fxml"));
        Parent buyerVacationDetailViewRoot = (Parent) buyerVacationDetailViewLoader.load();

        this.stage = stage;
        this.stage.initStyle(StageStyle.UNDECORATED);

        //set mouse pressed
        setDraggable(stage, signUpRoot);
        setDraggable(stage, signInRoot);
        setDraggable(stage, userViewRoot);
        setDraggable(stage, addPackageRoot);
        setDraggable(stage, addProductRoot);
        setDraggable(stage, packageDescriptionRoot);
        setDraggable(stage, searchViewRoot);
        setDraggable(stage, buyerVacationDetailViewRoot);

        signUpScene = new Scene(signUpRoot);
        signInScene = new Scene(signInRoot);
        userViewScene = new Scene(userViewRoot);
        addPackageScene = new Scene(addPackageRoot);
        addProductScene = new Scene(addProductRoot);
        PackageDescriptionView = new Scene(packageDescriptionRoot);
        searchView = new Scene(searchViewRoot);
        buyerVacationDetail = new Scene(buyerVacationDetailViewRoot);

        Model model = new Model();
        setModel(model);
        SignUpController controller = signUpLoader.getController();
        controller.setViewModel(this);

        SignInController signInController = signInLoader.getController();
        signInController.setViewModel(this);

        userViewController = userViewLoader.getController();
        userViewController.setViewModel(this);

        addPackageController = addPackageLoader.getController();
        addPackageController.setViewModel(this);

        addProductLoaderController = addProductLoader.getController();
        addProductLoaderController.setViewModel(this);

        packageDescriptionViewController = PackageDescriptionViewLoader.getController();
        packageDescriptionViewController.setViewModel(this);

        searchViewController = searchViewLoader.getController();
        searchViewController.setViewModel(this);

        buyerVacationDetailViewController = buyerVacationDetailViewLoader.getController();
        buyerVacationDetailViewController.setViewModel(this);

//        stage.setScene(signInScene);
        stage.setScene(searchView);
        stage.show();
    }

    public void addUser(User user) {
        model.addUser(user);
    }

    public User getUser() {
        return this.user;
    }

    public Boolean isUserExists(User user) {
        return model.isUserExists(user);
    }

    public Boolean loadUser(String email, String pass) {
        User u = new User(email, pass);
        if (model.isUserExists(u)) {
            this.user = model.loadUser(u);
            return true;
        }
        return false;

    }


    public void goToSearchView() {
        stage.setScene(searchView);
    }

    public void goToDetails() {
        stage.setScene(vaca);
    }

    public void goToSignIn() {
        user = null;
        stage.setScene(signInScene);
    }


    //*****************Update************************
    public void setUser(User user) {
        model.UpdateUser(user);
    }

    public void setSellerStatus(Order ord, boolean sellerStatus) {
        model.UpdateOrdersSellerStatus(ord, sellerStatus);
    }

    ;

    public void setBuyerStatus(Order ord, boolean buyerStatus) {
        model.UpdateOrdersBuyerStatus(ord, buyerStatus);
    }

    public void setVacationStatus(int vacation_id, boolean vac_status) {
        model.UpdatVacationStatus(vacation_id, vac_status);
    }


    ///**************delete
    public void deleteUser(User user) {
        model.deleteUser(user);
    }

    ///************************Get

    public List<Order> getOrdersByBuyer_email() {
        return model.getOrdersByBuyer_email(user.getEmail());
    }

    //******************************

    public void setDraggable(Stage stage, Parent parent) {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //set mouse drag
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void goToSignUp() {
        stage.setScene(signUpScene);
    }


    public void goToUserView() {
        userViewController.loadUserData(user);
        userViewController.setUser(user);
        stage.setScene(userViewScene);

    }

    public void goToAddPackage() {
        stage.setScene(addPackageScene);
    }

    public void goToAddProduct() {
        stage.setScene(addProductScene);
    }

    public void addVacation(Package vacation) {
        model.addPackage(vacation);
    }


    public void savePackage() {
        if (aPackage != null && aPackage.getProducts().size() > 0) {
            model.addPackage(aPackage);
            userViewController.addToTable(aPackage);
        }
        aPackage = null;
    }

    public void loadUser(User u) {
        this.user = u;
    }


    public void discartPackage() {
        aPackage = null;
    }


    public List<String> getAllCategories() {
        return model.getAllCategories();
    }

    public List<String> getAllPackageCancellationPolicy() {
        return model.getAllPackageCancelationPoliciy();
    }

    public void searchPackagesBy(List<Package> packagesList) {
        packageDescriptionViewController.addPackagesToTable(packagesList);
        if (user != null)
            packageDescriptionViewController.setUserLoggedIn();
        else
            packageDescriptionViewController.setUserLoggedOut();
        stage.setScene(PackageDescriptionView);
    }

    public List<Package> searchPackagesByDate(LocalDate startDateValue, LocalDate endDateValue) {
        return model.getPackagesBy(startDateValue, endDateValue);
    }

    public List<Package> getPackagesByCategory(String category) {
        return model.getPackagesByCategory(category);
    }


    public void goToPackageDescriptionView() {
        stage.setScene(PackageDescriptionView);
    }

    public void loguotUser() {
        this.user = null;
    }

    public void addRentOrder(ProductEntry clickedProductRow) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate startDate = LocalDate.parse(clickedProductRow.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(clickedProductRow.getEndDate(), formatter);
        Order o = new Order(clickedProductRow.getOwnerEmail(), user.getEmail(), startDate, endDate, clickedProductRow.getPrice(), clickedProductRow.getPackageID(), "Rented");
        model.addOrder(o);
    }

    public void addTradeOrder(ProductEntry clickedProductRow) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate startDate = LocalDate.parse(clickedProductRow.getStartDate(), formatter);
        LocalDate endDate = LocalDate.parse(clickedProductRow.getEndDate(), formatter);
        Order o = new Order(clickedProductRow.getOwnerEmail(), user.getEmail(), startDate, endDate, clickedProductRow.getPrice(), clickedProductRow.getPackageID(), "Traded");
        model.addOrder(o);
    }

    public List<Package> getUnOrderdPackagesOfUser() {
        return model.getUnOrderedUserPackages(user.getEmail());
    }


    public List<Package> getPackagesByAddress(String city, String neighborhood, String street) {
        return model.getPackagesByAddress(new Address(city, neighborhood, street));
    }


    ///***********8
    public void popAlerterror(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    public void popAlertinfo(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    public void goToBuyerVacations() {
    }

    public void goToPayPal() {
    }

    public String getUserNameByEmail(String email) {
        model.getUserNameByEmail();
    }

    public void goToBuyerVacationDetails(Order clickedRow) {
        int vacationID = clickedRow.getVacation_id();
        Package vac = model.getPackageByPackageId(vacationID);
        buyerVacationDetailViewController.setVacation(vac);
        stage.setScene(buyerVacationDetail);
    }
}


//    public void setUserLoggedOut(){
//        rentBtn.setDisable(true);
//        tradeBtn.setDisable(true);
//        userViewBtn.setDisable(true);
//        loginBtn.setDisable(false);
//    }
