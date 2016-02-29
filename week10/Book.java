package week10;

public class Book extends Product{
	Person author;
	
	
	public Book(String title, Person person, Person author) {
		super(title, person);
		this.author = author;
	}

	
	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}


	@Override
	public long getInvestment() {
		int investment = 0;
		investment += author.getSalary();
		return investment;
	}
	
	@Override
	public String toString() {
		return "Title: " + this.title + "\n" + "Author: " + this.author;
	}
}
