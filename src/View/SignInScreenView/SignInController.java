package View.SignInScreenView;

import java.net.URL;
import java.util.ResourceBundle;

import Main.ViewModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class SignInController implements Initializable {

    public TextField email;
    public PasswordField password;
    private ViewModel viewModel;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setViewModel(ViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void goToSignUp(MouseEvent mouseEvent) {
        viewModel.goToSignUp();
    }


    public void goToUserView(MouseEvent mouseEvent) {
        if(viewModel.loadUser(email.getText(), password.getText())) {
            viewModel.goToSearchView();
        }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("error Dialog");
                alert.setHeaderText("Email or password are incorrect");
                alert.setContentText("You can sign up it free!");
                alert.showAndWait();
            }
        }
    }
