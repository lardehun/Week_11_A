package week10;

import java.io.Serializable;

public abstract class Product implements Serializable{
	String id;
	String title;
	Person person;
	
	public Product(String title, Person person) {
		this.title = title;
		this.person = person;
		this.id = IdGenerator.generate(this);
	}

	public String getTitle() {
		return title;
	}

	public Person getPerson() {
		return person;
	}
	
	public abstract long getInvestment();
	
}
