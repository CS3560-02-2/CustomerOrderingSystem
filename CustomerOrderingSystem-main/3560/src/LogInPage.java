import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;


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
    void enterData(MouseEvent event) throws IOException {
        //debugging text input
        System.out.println(enterUser.getText());
        System.out.println(enterPass.getText());

    }
}
