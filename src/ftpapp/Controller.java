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
    private void connectAction(ActionEvent ae) {
        ftp = new FTPClient();
        session = new Session(ftp, username.getText(), password.getText(), host.getText(), 21);
        if(session.login()) {
            loginStatusText.setText("Connected!");
            loginStatusCircle.setFill(Color.GREEN);
        }
    }

    @FXML
    private void disconnectAction(ActionEvent ae) {

    }

    @FXML
    private void downloadAction(ActionEvent ae) {

    }

    @FXML
    private void uploadAction(ActionEvent ae) {

    }


    @FXML
    private void deleteRemoteAction(ActionEvent ae) {
        
    }
}
