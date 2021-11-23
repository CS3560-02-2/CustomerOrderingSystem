import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class NewAccountPage {

    private Scene scene;
    private Stage stage;
    String test;



    @FXML
    private TextField enterFirstName;
        
    @FXML
    private TextField enterLastName;


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

        try {
            String sql = "INSERT INTO customer (firstName, lastName, address, city, zipcode, state, phoneNumber, "
            + "creditCardNumber, creditCardSec, username, pass) "
            + "VALUES ('" + enterFirstName.getText() + "','" + enterLastName.getText() + "','" + enterAddress.getText() + "','" 
            + enterCity.getText() + "','" +  enterZip.getText() + "','" + enterState.getText() + "','" +  enterPhoneNumber.getText() + "','" 
            + enterCreditCard.getText() + "','" +  enterCVV.getText() + "','" +  enterUserName.getText() + "','" +  enterPass.getText() + "')";
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmt = conn.createStatement();
            int newID = stmt.executeUpdate(sql);
            //rs.getString("firstName");
            sql = "SELECT custID FROM customer WHERE username = '" + enterUserName.getText() + "' and pass = '" + enterPass.getText() + "'";
            Statement finalCountdown = conn.createStatement();
            ResultSet rsFind = finalCountdown.executeQuery(sql);
            rsFind.next();
            App.loggedIn = rsFind.getInt("custID");
            App.loggedInName = enterFirstName.getText();
            
            conn.close();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
         }
    }
    
}
