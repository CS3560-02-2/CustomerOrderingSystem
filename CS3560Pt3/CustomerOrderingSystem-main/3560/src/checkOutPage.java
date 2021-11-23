import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
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
import java.sql.Date;

public class checkOutPage implements Initializable{
    private Scene scene;
    private Stage stage;
    String test;
    private float priceTotal;
    
    @FXML
    private TableView foodTable;

    @FXML
    private Text priceTag;


    @Override
    public void initialize(URL location, ResourceBundle resources){


        priceTotal = 0;
        TableColumn name = new TableColumn("Food Name");
        TableColumn price = new TableColumn("Food Price");
        TableColumn image = new TableColumn("Food Image");

        foodTable.getColumns().addAll(name,price,image);


        final ObservableList<food> data = FXCollections.observableArrayList();
    
        try{

            String sqlCart = "SELECT idfood, quantity FROM shoppingcart WHERE custID = " + App.loggedIn;
            Connection connCart = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmtCart = connCart.createStatement();
            ResultSet rsCart = stmtCart.executeQuery(sqlCart);

            //System.out.println("FIRST QUEREY DONE");


            while(rsCart.next()){
                //this if statement ensures only food is displayed
                String sql = "SELECT idfood, foodName, foodPrice, foodImage, foodType FROM food WHERE idfood = " + rsCart.getInt("idfood");
                Statement stmt = connCart.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //System.out.println("SECOND QUEREY DONE");

                if (rs.next()){
                    for(int i = 0; i < rsCart.getInt("quantity"); i++){
                        //System.out.println(rsCart.getInt("quantity"));
                        //System.out.println(rs.getString("foodName"));
                        
                        // THIS DISPLAYS THE IMAGE!!!!!!
                        Image newImage = new Image(rs.getBlob("foodImage").getBinaryStream(), 100, 100, false, false);
                        ImageView display = new ImageView();
                        display.setImage(newImage);
                        //this gets the data
                        data.add(new food(rs.getInt("idfood"),rs.getString("foodName"),rs.getFloat("foodPrice"),display,rs.getString("foodType")));
                        //the image is unique
    
                        image.setCellValueFactory(new PropertyValueFactory<food,Blob>("image"));
                        name.setCellValueFactory(new PropertyValueFactory<food,String>("name"));
                        price.setCellValueFactory(new PropertyValueFactory<food,Float>("price"));
                        
                        
                        priceTotal += rs.getFloat("foodPrice");
                    }
                    
                }
                //this was a debugging bit of code that shall be left in case it is needed
                //System.out.println(rs.getString("foodName"));
                
            }

            //this puts the table fully together
            foodTable.setItems(data);

            //this closes the connection to the server
            connCart.close();
            priceTag.setText("$" + priceTotal);

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

    //this orders the food
    @FXML
    void runOrder(ActionEvent event) {
        Date temp = new Date(System.currentTimeMillis());
        try {
            String sql = "INSERT INTO foodorder (custID, orderPrice, orderDate) "
            + "VALUES ('" + App.loggedIn + "','" + priceTotal + "','" + temp + "')";
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);  
            sql = "SELECT MAX(orderID) FROM foodorder ";
            System.out.println("PRIMARY SUCESS");
            Statement finalCountdown = conn.createStatement();
            ResultSet rsFind = finalCountdown.executeQuery(sql);
            rsFind.next();
            int newID = rsFind.getInt("MAX(orderID)");
            conn.close();
            System.out.println(newID);
            clearTable(newID, temp);  
            refreshTable();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
         }
    }
    
    @FXML
    void itemDelete(MouseEvent event) {
        food temp = (food)foodTable.getSelectionModel().selectedItemProperty().get();
        //System.out.println("ITEM SELECTED!");
        foodTable.getSelectionModel().selectedItemProperty().get();
        //System.out.println(temp.getId());
        System.out.println(foodTable.getSelectionModel().getSelectedIndex());
        if (App.loggedIn > 0 ) {

            try {

                //this adds to the quantity
                    String sql = "SELECT quantity FROM shoppingCart WHERE custID = " + App.loggedIn + " AND  idfood = " + temp.getId();
                    Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    rs.next();
                    int amount = rs.getInt(1) - 1;
                    System.out.println(amount);

                    if (amount == 0) {
                        sql = "DELETE FROM shoppingCart WHERE custID = "+ App.loggedIn + " AND  idfood = " + temp.getId();
                        stmt.executeUpdate(sql);
                    } else {
                        sql = "UPDATE shoppingCart SET quantity = " + amount + " WHERE custID = "+ App.loggedIn + " AND  idfood = " + temp.getId();
                        stmt.executeUpdate(sql);
                    }
                    
                    conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            refreshTable();

        }else{
            System.out.println("User not logged in.");
        }
        //initialize(location, resources);
    }

    //cool function refreashes the table
    public void refreshTable(){
        priceTotal = 0;
        final ObservableList<food> data = FXCollections.observableArrayList();
    
        try{

            String sqlCart = "SELECT idfood, quantity FROM shoppingcart WHERE custID = " + App.loggedIn;
            Connection connCart = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmtCart = connCart.createStatement();
            ResultSet rsCart = stmtCart.executeQuery(sqlCart);

            //System.out.println("FIRST QUEREY DONE");


            while(rsCart.next()){
                //this if statement ensures only food is displayed
                String sql = "SELECT idfood, foodName, foodPrice, foodImage, foodType FROM food WHERE idfood = " + rsCart.getInt("idfood");
                Statement stmt = connCart.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                //System.out.println("SECOND QUEREY DONE");

                if (rs.next()){
                    for(int i = 0; i < rsCart.getInt("quantity"); i++){
                        //System.out.println(rsCart.getInt("quantity"));
                        //System.out.println(rs.getString("foodName"));
                        
                        // THIS DISPLAYS THE IMAGE!!!!!!
                        Image newImage = new Image(rs.getBlob("foodImage").getBinaryStream(), 100, 100, false, false);
                        ImageView display = new ImageView();
                        display.setImage(newImage);
                        //this gets the data
                        data.add(new food(rs.getInt("idfood"),rs.getString("foodName"),rs.getFloat("foodPrice"),display,rs.getString("foodType")));
                        //the image is unique
                        priceTotal += rs.getFloat("foodPrice");
    
                    }
                    
                }
                //this was a debugging bit of code that shall be left in case it is needed
                //System.out.println(rs.getString("foodName"));
                
            }

            //this puts the table fully together
            foodTable.setItems(data);

            //this closes the connection to the server
            connCart.close();

            priceTag.setText("$" + priceTotal);

            //this will catch if the SQL database is connected to
        }catch (SQLException ex){
           System.out.println(ex.getMessage());
           System.out.println("FAILURE!");
        }
    }

    //cool function refreashes the table
    public void clearTable(int orderID, Date day){
        priceTotal = 0;
        final ObservableList<food> data = FXCollections.observableArrayList();
    
        try{

            String sqlCart = "SELECT idfood, quantity FROM shoppingcart WHERE custID = " + App.loggedIn;
            Connection connCart = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mysqlcs3560", "sqluser", "password");
            Statement stmtCart = connCart.createStatement();
            ResultSet rsCart = stmtCart.executeQuery(sqlCart);

            System.out.println("FIRST QUEREY DONE");


            while(rsCart.next()){
                //this if statement ensures only food is displayed
                String sql = "INSERT INTO itemrequest (orderID, idfood, requestDate, orderStatus, quantity) "
                + "VALUES ('" + orderID + "','" + rsCart.getInt("idfood") + "','" + day + "', 'REQUESTED'," + rsCart.getInt("quantity") + ")";
                Statement stmt = connCart.createStatement();
                int rs = stmt.executeUpdate(sql);
                System.out.println("SECOND QUEREY DONE");

                sql = "DELETE FROM shoppingCart WHERE custID = "+ App.loggedIn + " AND  idfood = " + rsCart.getInt("idfood");
                stmt.executeUpdate(sql);
                System.out.println("THIRD QUEREY DONE");
                                
            }

            //this puts the table fully together
            foodTable.setItems(data);

            //this closes the connection to the server
            connCart.close();

            priceTotal = 0;
            priceTag.setText("$ 0.00");

            //this will catch if the SQL database is connected to
        }catch (SQLException ex){
           System.out.println(ex.getMessage());
           System.out.println("FAILURE!");
        }
    }
}
