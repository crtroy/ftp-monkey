package ftpapp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import javax.security.auth.login.LoginException;
import java.io.File;
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
    private ListView<String> view_remote;

    @FXML
    private ObservableList<String> remoteItems = FXCollections.observableArrayList();

    @FXML
    private ListView<String> view_local;

    @FXML
    private ObservableList<String> localItems = FXCollections.observableArrayList();

    @FXML
    private Button btn_disconnect;

    @FXML
    private TextArea txt_log;

    public Session session;
    public Get get;
    public Delete delete;

    protected FTPClient ftp;

    @FXML
    private void connectAction(ActionEvent ae) {
        ftp = new FTPClient();
        System.out.println(System.getProperty("user.home"));
        //System.out.println(ftp.changeWorkingDirectory(remoteFileDir));
        //ftp.changeWorkingDirectory(remoteFileDir);
        try {

            /*
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
            */

            //session = new Session(ftp, txt_username.getText(), txt_password.getText(), txt_servername.getText(), Integer.parseInt((txt_port.getText())), txt_log);
            session = new Session(ftp, "FTP-User", "12345", "10.200.212.36", 21, txt_log);

            if(false){
                throw new IOException();
            }
            if (session.login()) {
                txt_login_status.setText("Connected!");
                circle_login_status.setFill(Color.GREEN);
                remoteFileList();
                //localFileAndDirecotyList("C:\\Users\\Public");
                localFileAndDirecotyList(System.getProperty("user.home"));
            }
        } catch (IOException e) {

        }
    }

    @FXML
    private void disconnectAction(ActionEvent ae) {
        try {
            if (txt_login_status.getText().equals("Not Connected")) {
                throw new Exception();
            }
            ftp.disconnect();
            txt_log.appendText("Disconnected\n");
            txt_login_status.setText("Not Connected");
            circle_login_status.setFill(Color.RED);
        } catch (Exception e) {
            listenForScroll(txt_log);
            txt_log.appendText("Error: Not connected\n");
        }
    }

    @FXML
    private void downloadAction(ActionEvent ae) throws LoginException, IOException {
        try {
            ObservableList<String> localDirectoryList = view_local.getSelectionModel().getSelectedItems();
            ObservableList<String> remoteDirectoryList = view_remote.getSelectionModel().getSelectedItems();
            String localDirectory = localDirectoryList.get(0);
            String remoteDirectory = remoteDirectoryList.get(0);
            get = new Get(ftp, txt_log, localDirectory, remoteDirectory);
        }
        catch (Exception e) {
            listenForScroll(txt_log);
            txt_log.appendText("Error: File transfer did not complete. Please select valid file and directory from list.\n");
        }
    }

    @FXML
    private void uploadAction(ActionEvent ae) {

    }

    @FXML
    private void deleteRemoteAction(ActionEvent ae) {
        try {
            ObservableList<String> remoteDirectoryList = view_remote.getSelectionModel().getSelectedItems();
            String remoteDirectory = remoteDirectoryList.get(0);
            delete = new Delete(ftp, txt_log, remoteDirectory);
        }
        catch (Exception e) {
            listenForScroll(txt_log);
            txt_log.appendText("Error: File transfer did not complete. Please select valid file and directory from list.\n");
        }
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

    private void remoteFileList() {
        try {

            FTPFile[] filesArray = ftp.listFiles();

            for (FTPFile fileItem : filesArray) {
                String info = fileItem.getName();
                System.out.println(fileItem.getName());
                remoteItems.add(info);
            }
            view_remote.setItems(remoteItems);
            view_remote.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void localFileAndDirecotyList(String dirNsme) {
        File dirTolist = new File(dirNsme);

        //Get all of the files from a direcory
        File[] fileArray = dirTolist.listFiles();

        for (File fileObj : fileArray) {
            System.out.println(fileObj.getAbsolutePath());
            localItems.add(fileObj.getAbsolutePath());
            view_local.setItems(localItems);
        }
        view_local.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
