import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class PizzaAndSidesPage {
    private Scene scene;
    private Stage stage;
    String test;
    ObservableList<food> data = FXCollections.observableArrayList();

    @FXML
    private TableColumn<food, Blob> col_foodImage;

    @FXML
    private TableColumn<food, String> col_foodName;

    @FXML
    private TableColumn<food, Float> col_foodPrice;

    @FXML
    private TableColumn<food, String> col_foodType;

    @FXML
    private TableView<food> foodTable;

    public void initialize(URL location, ResourceBundle resources){
        
        try{
            foodTable = new TableView<>();

            String sql = "SELECT foodName, foodPrice, foodImage, foodType FROM food";
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                data.add(new food(rs.getString("foodName"),rs.getFloat("foodPrice"),rs.getBlob("foodImage"),rs.getString("foodType")));
            }

            col_foodImage.setCellValueFactory(new PropertyValueFactory<>("foodImage"));
            col_foodName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
            col_foodPrice.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
            col_foodType.setCellValueFactory(new PropertyValueFactory<>("foodType"));

            foodTable.setItems(data);

            conn.close();

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
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
    void loadShit(ActionEvent event) {

    }

}
