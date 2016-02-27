package week10;

import java.util.List;

public class Movie extends Product implements Buyable,java.io.Serializable{
	Genre genre;
	long duration;
	double rate;
	List<Person> cast;
	int price;
	
	
	public Movie(Genre genre, long duration, double rate, List<Person> cast, int price, String title,Person person) {
		super(title,person);
		this.genre = genre;
		this.duration = duration;
		this.rate = rate;
		this.cast = cast;
		this.price = price;
		
		
	}
	public Genre getGenre() {
		return genre;
		
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
		
	}
	public long getDuration() {
		return duration;
		
	}
	public void setDuration(long duration) {
		this.duration = duration;
		
	}
	public double getRate() {
		return rate;
		
	}
	public void setRate(double rate) {
		this.rate = rate;
		
	}
	public List<Person> getCast() {
		return cast;
		
	}
	public void setCast(List<Person> cast) {
		this.cast = cast;
		
	}
	@Override
	public int getPrice() {
		return price;
		
	}
	public void setPrice(int price) {
		this.price = price;
		
	}
	@Override
	public long getInvestment() {
		int investment = 0;
		for (Person person : cast) {
			investment += person.getSalary();
		}
		return investment;
	}
	
	
}
