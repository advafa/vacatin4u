package View.ProfileView;

import java.net.URL;
import java.util.ResourceBundle;

import App.User;
import Main.ViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import java.time.LocalDate;


public class EditProfileController implements Initializable {

    @FXML
    public javafx.scene.control.TextField first_name;
    public javafx.scene.control.TextField last_name;
    public javafx.scene.control.TextField email;
    public javafx.scene.control.TextField password;
    public javafx.scene.control.TextField confirm_password;
    public javafx.scene.control.DatePicker birth_date;
    public javafx.scene.control.TextField city;

    private ViewModel viewModel;
    private User user;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        user= viewModel.getUser();
        if(this.viewModel.isUserExists(user)){
            this.first_name.setText(user.getFirst_name());
            this.last_name.setText(user.getLast_name());
            this.email.setText(user.getEmail());
            this.password.setText(user.getPassword());
            this.confirm_password.setText(user.getPassword());
            this.birth_date.setValue(user.getBirth_date());
            this.city.setText(user.getCity());
        }
        else{

            popAlerterror("Please Sign in!");
        }

    }


    public void handleUpdateUser(MouseEvent mouseEvent) {
        user = new User(first_name.getText(), last_name.getText(), email.getText(), password.getText(), birth_date.getValue(), city.getText());

        if (validateform()) {
            viewModel.setUser(user);
            popAlertinfo("You profile successfully update!");
            reset(mouseEvent);
            viewModel.goToSearchView();
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


    public void reset(MouseEvent mouseEvent) {
        first_name.setText("");
        last_name.setText("");
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


}