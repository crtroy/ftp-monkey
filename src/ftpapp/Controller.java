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
    private Circle circle_login_status;

    @FXML
    private Label txt_login_status;

    @FXML
    private TextField txt_servername;

    @FXML
    private TextField txt_port;

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_disconnect;

    public Session session;

    protected FTPClient ftp;

    @FXML
    private void connectAction(ActionEvent ae) {
        ftp = new FTPClient();
        try {
            session = new Session(ftp, txt_username.getText(), txt_password.getText(), txt_servername.getText(), Integer.parseInt((txt_port.getText())));

            if (session.login()) {
                txt_login_status.setText("Connected!");
                circle_login_status.setFill(Color.GREEN);
            }
        }
        catch (Exception e) {

            System.out.println("Error: Bad login credentials");
        }
    }

    @FXML
    private void disconnectAction(ActionEvent ae) {
        try {
            ftp.disconnect();
            System.out.println("Disconnecting");
            txt_login_status.setText("Not Connected");
            circle_login_status.setFill(Color.RED);
        }
        catch (Exception e) {
            System.out.println("Error: No connection to disconnect");
        }
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
