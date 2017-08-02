package ftpapp;

import model.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.apache.commons.net.ftp.FTPClient;

import javax.security.auth.login.LoginException;

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

    public Session session;

    protected FTPClient ftp;

    @FXML
    private void loginButton(ActionEvent ae) {
        ftp = new FTPClient();
        session = new Session(ftp, username.getText(), password.getText(), host.getText(), 21);
        if(session.login()) {
            loginStatusText.setText("Connected!");
            loginStatusCircle.setFill(Color.GREEN);
        }
    }

    @FXML
    private void getButton(ActionEvent ae) {
        try {
            Get get = new Get(ftp);
        } catch (LoginException e) {
            /* User clicked get button but hasn't logged in */
        }
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
