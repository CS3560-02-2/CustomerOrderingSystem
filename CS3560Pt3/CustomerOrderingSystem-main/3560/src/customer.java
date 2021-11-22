import java.math.BigInteger;
import javafx.beans.property.SimpleStringProperty;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;
import javafx.beans.property.SimpleFloatProperty;


//This is a customer class
public class customer 
{
	//Customer informations required at registration.
	int custID;
	SimpleStringProperty name;
	SimpleStringProperty address;
	SimpleStringProperty phoneNumber;
	SimpleStringProperty creditCardNumber;

    //Making a public customer
	public customer(String name, String address,  String number, String creditCard){
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.phoneNumber = new SimpleStringProperty(number);
        this.creditCardNumber = new SimpleStringProperty(creditCard);
    }

    public String getName(){
        return name.get();
    }

    public void setName(String name){
        this.name.set(name);
    }

    public String getAddress(){
        return address.get();
    }

    public void setAddress(String address){
        this.address.set(address);
    }

    public String getPhoneNumber(){
        return phoneNumber.get();
    }

    public void setPhoneNumber(String number){
        this.phoneNumber.set(number);
    }

    public String getCreditCardNumber(){
        return creditCardNumber.get();
    }

    public void setCreditCardNumber(String creditCard){
        this.creditCardNumber.set(creditCard);
    }

	//Adding an item from the menu to the cart, using the foodname object from food class into the cart object from order class.
	void addItemToCart(food foodname, int quantity, order cart)
	{
		
	}
	
	//Similar to the addItemToCart function, this will remove the 'foodname' from the 'cart'.
	void removeItemFromCart(food foodname, int quantity, order cart)
	{
		
	}
	
	//This function will call the cart object from order class.
	void OpenCart(order cart)
	{
		
	}
	
	//updateItemQuantity will let the customer to change the quantity of 'foodname' in the 'cart'
	void updateItemQuantity(food foodname, int quantity, order cart)
	{
		
	}
	
	//This function will call the 'menu' which is food class as a list.
	void returnToMenu()
	{
		
	}
	
	//Will proceed to payment.
	void checkOut()
	{
		
	}
}	
