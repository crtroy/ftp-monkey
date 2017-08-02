package model;

import org.apache.commons.net.ftp.FTPClient;

import javax.security.auth.login.LoginException;

public class Get {
    protected FTPClient ftp;

    public Get(FTPClient ftpAdd) throws LoginException {
        if (ftp == null) {
           System.out.println("Error: Must have valid login");
           throw new LoginException();
        }
        ftp = ftpAdd;
        System.out.println("Creating Get Class");
    }
}
