package model;

import java.io.*;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import javax.security.auth.login.LoginException;


public class Get {
    protected FTPClient ftp;

    public Get(FTPClient ftpAdd) throws LoginException, IOException {
        if (ftpAdd == null) {
            System.out.println("Error: Must have valid login");
            throw new LoginException();
        }
        ftp = ftpAdd;
        System.out.println("Creating Get Class");

        //ftp.connect("192.168.1.5", 21);
        //ftp.connect("10.200.212.36", 21);
        //ftp.login("FTP-User", "12345");

        boolean transferSuccess;
        //enter passive mode
        ftp.enterLocalPassiveMode();
        //use binary file type transfer
        ftp.setFileType(FTP.BINARY_FILE_TYPE);

        //TODO
        //must get text from user
        String serverFile = "/ip.txt";

        //TODO
        //Destination where file will be saved
        File localDestination = new File("C:/Users/troyc/Desktop/FTP Holder/ip.txt");

        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localDestination));
        InputStream inputStream = ftp.retrieveFileStream(serverFile);
        byte[] inputStreamBytes = new byte[4096];
        int readBytes = -1;
        while ((readBytes = inputStream.read(inputStreamBytes)) != -1) {
            outputStream.write(inputStreamBytes, 0, readBytes);
        }
        transferSuccess = ftp.completePendingCommand();
        if (transferSuccess) {
            System.out.println(serverFile + " has been transferred successfully.");
        }
        outputStream.close();
        inputStream.close();

    }
}