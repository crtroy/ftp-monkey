package test;

import javafx.scene.control.TextArea;
import model.Session;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class SessionTest {
    @Test(expected = Exception.class )
    public void testSession() {
        FTPClient ftp = null;
        String username = null;
        String password = null;
        String host = null;
        int port = 0;
        TextArea txt_log = null;
        Session session = new Session(ftp, username, password, host, port, txt_log);

    }
    @Test
    public void login() throws Exception {

    }

}