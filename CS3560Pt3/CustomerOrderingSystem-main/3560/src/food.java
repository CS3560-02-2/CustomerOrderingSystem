import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class food 
{
    private SimpleIntegerProperty foodId;
	private SimpleStringProperty name;
    private SimpleFloatProperty price;
    private ImageView image;
    private SimpleStringProperty type;

    public food(int id, String name, Float price, ImageView display, String type){
        this.foodId = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleFloatProperty(price);
        this.image = display;
        this.type = new SimpleStringProperty(type);
    }


    public void setId(int id){
        this.foodId.set(id);
    }

    public int getId(){
        return foodId.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public String getName(){
        return name.get();
    }

    public void setPrice(Float price){
        this.price.set(price);
    }

    public Float getPrice(){
        return price.get();
    }

    public void setImage(ImageView image){
        this.image = image;
    }

    public ImageView getImage(){
        return image;
    }

    public void setType(String type){
        this.type.set(type);
    }

    public String getType(){
        return type.get();
    }
}