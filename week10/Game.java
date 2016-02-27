package week10;

import java.util.List;

public class Game extends Product implements Buyable,java.io.Serializable{
	boolean preOrder;
	List<Person> staff;
	int price;
	
	
	public Game(boolean preOrder, List<Person> staff, int price,String title,Person person) {
		super(title,person);
		this.preOrder = preOrder;
		this.staff = staff;
		this.price = price;
	}

	
	public boolean isPreOrder() {
		return preOrder;
	}


	public void setPreOrder(boolean preOrder) {
		this.preOrder = preOrder;
	}


	public List<Person> getStaff() {
		return staff;
	}


	public void setStaff(List<Person> staff) {
		this.staff = staff;
	}

	@Override
	public int getPrice() {
		if (preOrder) {
			return (int) (price - price*0.2);
		}
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	@Override
	public long getInvestment() {
		int investment = 0;
		for (Person person : staff) {
			investment += person.getSalary();
		}
		return investment;
	}
	
	
}
