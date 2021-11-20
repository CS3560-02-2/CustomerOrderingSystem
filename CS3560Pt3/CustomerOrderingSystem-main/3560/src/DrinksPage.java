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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class DrinksPage implements Initializable{
    private Scene scene;
    private Stage stage;
    String test;

    @FXML
    private TableView drinkTable;

    @FXML
    private TextField searchBar;

    @Override
    public void initialize(URL location, ResourceBundle resources){


        TableColumn name = new TableColumn("Food Name");
        TableColumn price = new TableColumn("Food Price");
        TableColumn image = new TableColumn("Food Image");
        //TableColumn type = new TableColumn("Food Type");

        drinkTable.getColumns().addAll(name,price,image);


        final ObservableList<food> data = FXCollections.observableArrayList();
    
        try{
            //foodTable = new TableView<>();

            String sql = "SELECT foodName, foodPrice, foodImage, foodType FROM food";
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


          
            
            while(rs.next()){
                //this if statement ensures only drinks are displayed
                if(rs.getString("foodType").equalsIgnoreCase("drink")){
                    // THIS DISPLAYS THE IMAGE!!!!!!
                    Image newImage = new Image(rs.getBlob("foodImage").getBinaryStream(), 100, 100, false, false);
                    ImageView display = new ImageView();
                    display.setImage(newImage);
                    //this gets the data
                    data.add(new food(rs.getString("foodName"),rs.getFloat("foodPrice"),display,rs.getString("foodType")));
                    image.setCellValueFactory(new PropertyValueFactory<food,Blob>("image"));
                    name.setCellValueFactory(new PropertyValueFactory<food,String>("name"));
                    price.setCellValueFactory(new PropertyValueFactory<food,Float>("price"));
                    //type.setCellValueFactory(new PropertyValueFactory<food, String>("type"));
                }
                
            }

            //this puts the table fully together
            drinkTable.setItems(data);

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
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchThrough(ActionEvent event) {
        final ObservableList<food> data = FXCollections.observableArrayList();
        System.out.println(searchBar.getText());
        try{
            //foodTable = new TableView<>();

            String sql = "SELECT foodName, foodPrice, foodImage, foodType FROM food";
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                //this if statement ensures only food is displayed
                if(rs.getString("foodType").equalsIgnoreCase("drink")){
                    
                    if(rs.getString("foodName").toLowerCase().contains(searchBar.getText().toLowerCase())) {
                        // THIS DISPLAYS THE IMAGE!!!!!!
                        Image newImage = new Image(rs.getBlob("foodImage").getBinaryStream(), 100, 100, false, false);
                        ImageView display = new ImageView();
                        display.setImage(newImage);
                        //this gets the data
                        data.add(new food(rs.getString("foodName"),rs.getFloat("foodPrice"),display,rs.getString("foodType")));
                        //the image is unique
                        System.out.println(rs.getString("foodName"));
                    }
                }             
            }

            //this puts the table fully together
            drinkTable.setItems(data);

            //this closes the connection to the server
            conn.close();

            //this will catch if the SQL database is connected to
        }catch (SQLException ex){
           System.out.println(ex.getMessage());
           System.out.println("FAILURE!");
        }

    }
}