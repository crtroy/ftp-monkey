package ftpapp;

import model.Login;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {

    @FXML
    private Label loginStatusText;
    @FXML
    private Circle loginStatusCircle;
    @FXML
    private TextField host;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    @FXML
    private void loginButton(ActionEvent ae) {
        boolean loginSuccess = false;
        /* TODO Implement connection logic. Return true or false.
        *  Call the login class.
        *  */
        Login log = new Login();
        loginSuccess = log.run();
        loginSuccess = true;
        if(loginSuccess) {
            loginStatusText.setText("Connected!");
            loginStatusCircle.setFill(Color.GREEN);
        }
    }

    @FXML
    private void getButton(ActionEvent ae) {
        /* TODO: User has clicked 'get' button. Implement logic for getting remote file.
         *  */
    }

    @FXML
    private void putButton(ActionEvent ae) {
        /* TODO: Implement logic for putting local files to remote server */
    }

    @FXML
    private void deleteButton(ActionEvent ae) {
        /* TODO: Impleement logic for deleting remote file */
    }
}
