package ftpapp;

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

    public Session session;

    protected FTPClient ftp;

    @FXML
    private void connectAction(ActionEvent ae) {
        ftp = new FTPClient();
        System.out.println(System.getProperty("user.home"));
        //System.out.println(ftp.changeWorkingDirectory(remoteFileDir));
        //ftp.changeWorkingDirectory(remoteFileDir);
        try {
            session = new Session(ftp, txt_username.getText(), txt_password.getText(), txt_servername.getText(), Integer.parseInt((txt_port.getText())));

            if (session.login()) {
                txt_login_status.setText("Connected!");
                circle_login_status.setFill(Color.GREEN);
                remoteFileList();
                //localFileAndDirecotyList("C:\\Users\\Public");
                localFileAndDirecotyList(System.getProperty("user.home"));
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

    private void remoteFileList()  {
        try {

            FTPFile [] filesArray = ftp.listFiles();

            for (FTPFile fileItem : filesArray){
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

    private void localFileAndDirecotyList(String dirNsme){
        File dirTolist = new File(dirNsme);

        //Get all of the files from a direcory
        File [] fileArray = dirTolist.listFiles();

        for(File fileObj  :  fileArray){
            System.out.println(fileObj.getAbsolutePath());
            localItems.add(fileObj.getAbsolutePath());
            view_local.setItems(localItems);
        }
        view_local.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
