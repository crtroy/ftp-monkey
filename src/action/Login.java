package action;

import javafx.fxml.FXML;
import javafx.scene.control.*;

// Login contains the logic involved with connecting to an FTP server
public class Login implements Action {

    @FXML
    public TextField username;
    public boolean run() {

        /* TODO: Check input. Connect to server using credentials. Return true if successful

         */
        checkInput();
        testConnection();
        System.out.println(username);

        return true;
    }

    public void checkInput() {
        System.out.println("Checking input...");
    }

    public void testConnection() {
        System.out.println("Testing connection...");
    }

}
