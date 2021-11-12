import java.sql.Blob;

public class food 
{
	private String name;
    private float price;
    private Blob image;
    private String type;

    public food(String name, float price, Blob image, String type){
        this.name = name;
        this.price = price;
        this.image = image;
        this.type = type;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public float getPrice(){
        return price;
    }

    public void setImage(Blob image){
        this.image = image;
    }

    public Blob getImage(){
        return image;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}