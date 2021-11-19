import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.util.ResourceBundle;


public class NewAccountPage {
        private Scene scene;
        private Stage stage;
        String test;




    @FXML
    private TextField enterAddress;

    @FXML
    private TextField enterCVV;

    @FXML
    private TextField enterCity;

    @FXML
    private TextField enterCreditCard;

    @FXML
    private TextField enterName;

    @FXML
    private TextField enterPass;

    @FXML
    private TextField enterPhoneNumber;

    @FXML
    private TextField enterState;

    @FXML
    private TextField enterUserName;

    @FXML
    private TextField enterZip;
    
    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void saveUserData(ActionEvent event) {
        //debugging text input
        System.out.println(enterName.getText());
        System.out.println(enterAddress.getText());
        System.out.println(enterCity.getText());
        System.out.println(enterZip.getText());
        System.out.println(enterState.getText());
        System.out.println(enterPhoneNumber.getText());
        System.out.println(enterCreditCard.getText());
        System.out.println(enterCVV.getText());
        System.out.println(enterUserName.getText());
        System.out.println(enterPass.getText());
    }
    
}
