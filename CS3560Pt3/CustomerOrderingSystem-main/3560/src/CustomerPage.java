import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Table;

public class CustomerPage implements Initializable{
        private Scene scene;
        private Stage stage;
        String test;

        @FXML
        private TableView custTable;
    

    @Override
    public void initialize(URL location, ResourceBundle resources){
        

        TableColumn name = new TableColumn("Name");
        TableColumn address = new TableColumn("Address");
        TableColumn phoneNumber = new TableColumn("Phone Number");
        TableColumn creditCard = new TableColumn("Credit Card");

        custTable.getColumns().addAll(name,address,phoneNumber,creditCard);


        final ObservableList<customer> data = FXCollections.observableArrayList();
    
        try{
            String sql = "SELECT custID, firstName, address, phoneNumber, creditCardNumber FROM customer";
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            
                int i = 0;
                while(rs.next()){
                    if (App.loggedIn == rs.getInt("custID")) {
                    data.add(new customer(rs.getString("firstName"),rs.getString("address"),rs.getString("phoneNumber"),rs.getString("creditCardNumber")));
                    name.setCellValueFactory(new PropertyValueFactory<customer,String>("name"));
                    address.setCellValueFactory(new PropertyValueFactory<customer,String>("address"));
                    phoneNumber.setCellValueFactory(new PropertyValueFactory<customer,String>("phoneNumber"));
                    creditCard.setCellValueFactory(new PropertyValueFactory<customer, String>("creditCardNumber"));  
                    // System.out.println(data.get(i).getName()); 
                    // i++;
                    }

                }

            //this puts the table fully together
            custTable.setItems(data);

            System.out.println("SUCESS!");


            //this closes the connection to the server
            conn.close();

            //this will catch if the SQL database is connected to
        }catch (SQLException ex){
           System.out.println(ex.getMessage());
           System.out.println("FAILURE!");
        }
    }
    
    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToUpdatePage(ActionEvent event) throws IOException  {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateAccountPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
