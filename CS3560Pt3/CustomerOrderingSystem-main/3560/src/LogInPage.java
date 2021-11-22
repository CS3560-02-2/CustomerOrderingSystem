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


public class LogInPage {
        private Scene scene;
        private Stage stage;
        String test;

    @FXML
    private TextField enterPass;

    @FXML
    private TextField enterUser;

    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void enterData(ActionEvent event) throws IOException {
        //debugging text input
        //System.out.println(enterUser.getText());
        //System.out.println(enterPass.getText());
        try {
            String sql = "SELECT custID, firstName, username, pass FROM customer";
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //debugging text
                //System.out.println(rs.getString("username"));
                //checks to see if username and pass are valid
                if(enterUser.getText().equals(rs.getString("username")) && enterPass.getText().equals(rs.getString("pass"))){
                    System.out.println("CORRECT");
                    App.loggedIn = rs.getInt("custID");
                    App.loggedInName = rs.getString("firstName");
                    System.out.println(App.loggedInName);                              
                    break;
                }else{
                    System.out.println("INCORRECT");
                }
            }
            conn.close();
            
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("FAILURE!");
         }

         

    }
}
