import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class Menu {

    @FXML
    void btnToDrinkMenu(MouseEvent event) {
        System.out.println("Drink Menu Clicked");
    }

    @FXML
    void btnToPizzaMenu(MouseEvent event) {
        System.out.println("Food Menu Clicked");
    }

}


