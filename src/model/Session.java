package model;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

public class Session {
    protected FTPClient ftp;

    public boolean loginSuccess;

    public Session(FTPClient ftp, String username, String password, String host, int port) {
//        ftp = new FTPClient();

        try {
            ftp.connect(host, port);
            if (!ftp.login(username, password)) {
                System.out.println("Login error!");
                loginSuccess = false;
                System.exit(1);
            }
            System.out.println("Login successful");
            loginSuccess = true;
        } catch (IOException e) {
            System.out.println("Exception in Session");
            //e.printStackTrace();
        }

    }

    public boolean login() {
        return loginSuccess;
    }



}
