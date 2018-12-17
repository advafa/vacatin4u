package View.SignUpScreenView;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import App.User;
import Main.ViewModel;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;


public class SignUpController implements Initializable {

    @FXML
    public JFXTextField first_name;
    public JFXTextField last_name;
    public JFXTextField email;
    public JFXPasswordField password;
    public JFXPasswordField confirm_password;
    public DatePicker birth_date;
    public JFXTextField city;

    private ViewModel viewModel;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void handleAddUser(MouseEvent mouseEvent) {
        user = new User(first_name.getText(), last_name.getText(), email.getText(), password.getText(), birth_date.getValue(), city.getText());

        if (validateform()) {
            viewModel.addUser(user);
            popAlertinfo("Registration successfully completed!");
            resetFields(mouseEvent);
            viewModel.goToSignIn();
        }

    }


    private boolean validateform() {
        if (!validateNotEmpty()) {
            popAlerterror("One or more fields is empty!");
            return false;
        }

        if (!confirm_password.getText().equals(password.getText())) {
            popAlerterror("The passwords don't match!");
            return false;
        }

        if (password.getText().length() != 8) {
            popAlerterror("The passwords have to contain 8 characters!");
            return false;
        }
        if ((birth_date.getValue().getYear() - LocalDate.now().getYear() < 18)) {
            popAlerterror("You are too young!");
            return false;
        }

        if (viewModel.isUserExists(user)) {
            popAlerterror("This email already exist!");
            return false;
        }
        if (!first_name.getText().chars().allMatch(Character::isLetter)) {
            popAlerterror("First Name have contain only letters!");
            return false;
        }

        if (!last_name.getText().chars().allMatch(Character::isLetter)) {
            popAlerterror("Last Name have contain only letters!");
            return false;
        }
        return true;
    }


    private boolean validateNotEmpty() {
        if (first_name.getText() == null || first_name.getText().trim().isEmpty()) {
            return false;
        }

        if (last_name.getText() == null || last_name.getText().trim().isEmpty()) {
            return false;
        }

        if (email.getText() == null || email.getText().trim().isEmpty()) {
            return false;
        }


        if (password.getText() == null || password.getText().trim().isEmpty()) {
            return false;
        }

        if (confirm_password.getText() == null || confirm_password.getText().trim().isEmpty()) {
            return false;
        }

        if (city.getText() == null || city.getText().trim().isEmpty()) {
            return false;
        }

        if (birth_date.getValue() == null) {
            return false;
        }
        return true;
    }


    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void resetFields(MouseEvent mouseEvent) {
        first_name.setText("");
        last_name.setText("");
        email.setText("");
        password.setText("");
        confirm_password.setText("");
        city.setText("");
        birth_date.setValue(LocalDate.now());
    }


    private void popAlertinfo(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

    private void popAlerterror(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("error Dialog");
        alert.setHeaderText(text);
        alert.showAndWait();
    }

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

    public void goTosignOut(MouseEvent mouseEvent) {
        this.user = null;
        viewModel.loguotUser();
        viewModel.goToSearchView();
    }

    public void deleteUser(){
        User currentUser= viewModel.getUser();
        if(this.viewModel.isUserExists(currentUser)){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to delete ypur proflie?");
            Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            this.viewModel.deleteUser(currentUser);
            this.viewModel.goToSearchView();
        }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Please Sign in!");
            alert.showAndWait();

        }

    }

}