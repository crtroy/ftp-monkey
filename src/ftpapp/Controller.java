package ftpapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import model.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;


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

    @FXML
    private TextArea txt_log;

    public Session session;

    protected FTPClient ftp;

    @FXML
    private void connectAction(ActionEvent ae) {
        ftp = new FTPClient();
        try {

            if (txt_servername.getText().isEmpty()) {
                txt_log.appendText("Error: Server name needed\n");
                throw new IOException();
            }
            if (txt_port.getText().isEmpty()) {
                txt_log.appendText("Error: Valid port number needed\n");
                throw new IOException();
            }
            if (txt_username.getText().isEmpty()) {
                txt_log.appendText("Error: Username needed\n");
                throw new IOException();
            }
            if (txt_password.getText().isEmpty()) {
                txt_log.appendText("Error: Password needed\n");
                throw new IOException();
            }

            session = new Session(ftp, txt_username.getText(), txt_password.getText(), txt_servername.getText(), Integer.parseInt((txt_port.getText())), txt_log);
            if (session.login()) {
                txt_login_status.setText("Connected!");
                circle_login_status.setFill(Color.GREEN);
            }
        } catch (IOException e) {

        }

//            session = new Session(ftp, "danielng", txt_password.getText(), "127.0.0.1", 21, txt_log);

    }

    @FXML
    private void disconnectAction(ActionEvent ae) {
        try {
            if(txt_login_status.getText().equals("Not Connected")) {
                throw new Exception();
            }
            ftp.disconnect();
            txt_log.appendText("Disconnected\n");
            txt_login_status.setText("Not Connected");
            circle_login_status.setFill(Color.RED);
        }
        catch (Exception e) {
            listenForScroll(txt_log);
            txt_log.appendText("Error: Not connected\n");
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

    private void listenForScroll(TextArea ta) {
        txt_log.textProperty().addListener(new ChangeListener<Object>() {
                @Override
                public void changed(ObservableValue<?> observable, Object oldValue,
                                    Object newValue) {
                    txt_log.setScrollTop(Double.MAX_VALUE);
                }
            });
    }
}
