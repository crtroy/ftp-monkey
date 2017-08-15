package model;

import javafx.scene.control.TextArea;
import org.apache.commons.net.ftp.FTPClient;
import java.io.IOException;

public class Session {
    protected FTPClient ftp;

    private boolean loginSuccess;

    public Session(FTPClient ftp, String username, String password, String host, int port, TextArea txt_log) {

        try {
            ftp.connect(host, port);
            if (!ftp.login(username, password)) {
                txt_log.appendText("Error: Bad credentials\n");
                loginSuccess = false;
            } else {
                txt_log.appendText("Login successful...connected!\n");
                loginSuccess = true;
            }
        } catch (IOException e) {
            txt_log.appendText("Error: Could not connect\n");
        }

    }

    public boolean login() {
        return loginSuccess;
    }



}
