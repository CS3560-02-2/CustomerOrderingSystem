import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class MainPage implements Initializable{
    private Scene scene;
    private Stage stage;
    
    @FXML
    private Text nameDisplay;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        if(App.loggedInName != null){
            nameDisplay.setText(App.loggedInName);
        }
    }


    @FXML
    void goToLogInPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToMenu(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotToAccountPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void goToAccountCreation(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("NewAccountPage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
