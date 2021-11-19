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

public class PizzaAndSidesPage implements Initializable{
    private Scene scene;
    private Stage stage;
    String test;

    @FXML
    private TableView foodTable;

    //this should add all the data to a method that can be read
    //ObservableList<food> data = FXCollections.observableArrayList();
    
    // @FXML
    // private TableColumn<food, Blob> col_foodImage;

    // @FXML
    // private TableColumn<food, String> col_foodName;

    // @FXML
    // private TableColumn<food, Float> col_foodPrice;

    // @FXML
    // private TableColumn<food, String> col_foodType;


    @Override
    public void initialize(URL location, ResourceBundle resources){


        TableColumn name = new TableColumn("Food Name");
        TableColumn price = new TableColumn("Food Price");
        TableColumn image = new TableColumn("Food Image");
        TableColumn type = new TableColumn("Food Type");

        foodTable.getColumns().addAll(name,price,image,type);


        final ObservableList<food> data = FXCollections.observableArrayList();
    
        try{
            //foodTable = new TableView<>();

            String sql = "SELECT foodName, foodPrice, foodImage, foodType FROM food";
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


          
            
            while(rs.next()){
                //this if statement ensures only food is displayed
                if(rs.getString("foodType").equalsIgnoreCase("food")){
                    data.add(new food(rs.getString("foodName"),rs.getFloat("foodPrice"),rs.getBlob("foodImage"),rs.getString("foodType")));
                    image.setCellValueFactory(new PropertyValueFactory<food,Blob>("image"));
                    name.setCellValueFactory(new PropertyValueFactory<food,String>("name"));
                    price.setCellValueFactory(new PropertyValueFactory<food,Float>("price"));
                    type.setCellValueFactory(new PropertyValueFactory<food, String>("type"));
                }
                //this was a debugging bit of code that shall be left in case it is needed
                //System.out.println(rs.getString("foodName"));
                
            }


            //this puts the table fully together
            foodTable.setItems(data);

            //this closes the connection to the server
            conn.close();

            //this will catch if the SQL database is connected to
        }catch (SQLException ex){
           System.out.println(ex.getMessage());
           System.out.println("FAILURE!");
        }
       

        //below is a previous 


        //will add all the columns with info into the tables
        //foodTable.getColumns().addAll(col_foodName,col_foodPrice,col_foodImage,col_foodType);

        

        //try{
            //foodTable = new TableView<>();

            // String sql = "SELECT foodName, foodPrice, foodImage, foodType FROM food";
            // Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            // Statement stmt = conn.createStatement();
            // ResultSet rs = stmt.executeQuery(sql);

            // while(rs.next()){
            //     data.add(new food(rs.getString("foodName"),rs.getFloat("foodPrice"),rs.getBlob("foodImage"),rs.getString("foodType")));
            // }

            // col_foodImage.setCellValueFactory(new PropertyValueFactory<food,Blob>("foodImage"));
            // col_foodName.setCellValueFactory(new PropertyValueFactory<food,String>("foodName"));
            // col_foodPrice.setCellValueFactory(new PropertyValueFactory<food,Float>("foodPrice"));
            // col_foodType.setCellValueFactory(new PropertyValueFactory<food, String>("foodType"));

            // foodTable.setItems(data);

            // conn.close();

        // }catch (SQLException ex){
        //    System.out.println(ex.getMessage());
        //    System.out.println("FAILURE!");
        // }
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
    void loadShit(ActionEvent event) {
        //
    }

}
