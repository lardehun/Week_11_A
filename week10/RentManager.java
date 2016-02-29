package week10;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import serverStuff.Command;


public class RentManager {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		
//		Person jani = new Person("Sajt","János",Gender.MALE,1000);
//		Person geri = new Person("Sajt","Gergö",Gender.MALE,1100);
//		Person barbi = new Person("Sajt","Barbara",Gender.FEMALE,900);
//		
//		List<Person> gameStaff1 = new ArrayList<Person>();
//		gameStaff1.add(geri);
//		gameStaff1.add(jani);
//		
//		List<Person> gameStaff2 = new ArrayList<Person>();
//		gameStaff2.add(geri);
//		gameStaff2.add(barbi);
//		
//		Product game1 = new Game(true,gameStaff1,100,"TheGame1",jani);
//		Product game2 = new Game(false,gameStaff2,200,"TheGame2",jani);
//		
//		System.out.println(game1.getInvestment());
//		System.out.println(game2.getInvestment());
//		System.out.println(game1.id);
//		System.out.println(game2.id);
//		
//		
//		
//		
//		Person moviePerson1 = new Person("Haragosi","János",Gender.MALE,3000);
//		Person moviePerson2 = new Person("Haragosi","Gergö",Gender.MALE,3100);
//		Person moviePerson3 = new Person("Haragosi","Barbara",Gender.FEMALE,2900);
//		
//		List<Person> movieCast1 = new ArrayList<Person>();
//		movieCast1.add(moviePerson1);
//		movieCast1.add(moviePerson2);
//		
//		List<Person> movieCast2 = new ArrayList<Person>();
//		movieCast2.add(moviePerson2);
//		movieCast2.add(moviePerson3);
//		
//		Product movie1 = new Movie(Genre.ACTION,120,99,movieCast1,3000,"TheMovie1",moviePerson1);
//		Product movie2 = new Movie(Genre.ACTION,120,99,movieCast2,3000,"TheMovie2",moviePerson2);
//		
//		System.out.println(movie1.getInvestment());
//		System.out.println(movie2.getInvestment());
//		System.out.println(movie1.id);
//		System.out.println(movie2.id);
//		
//		
//		
//		
		Person bookPerson1 = new Person("Haragosi","Attila",Gender.MALE,10000);
		Person bookPerson2 = new Person("Haragosi","Péter",Gender.MALE,30100);
//		
		Product book1 = new Book("HarryPottera",bookPerson1,bookPerson2);
//		Product book2 = new Book("HarryPottera2",bookPerson2,bookPerson2);
//		
//		System.out.println(book1.getInvestment());
//		System.out.println(book2.getInvestment());
//		System.out.println(book1.id);
//		System.out.println(book2.id);
//		
//		
//		
//		List<Buyable> sumPriceDemo = new ArrayList<Buyable>();
//		sumPriceDemo.add((Buyable) movie1);
//		sumPriceDemo.add((Buyable) game1);
//		
//		System.out.println(sumPrice(sumPriceDemo));
		
		clientServer(book1);
//	
//	}
//	
//	public static int sumPrice(List<Buyable> list) {
//		int total = 0;
//		for (Buyable buyable : list) {
//			total += buyable.getPrice();
//		}
//		return total;
//	}
//	
	}

	@SuppressWarnings("resource")
	private static void clientServer(Object objectToSave)
			throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException {
		Socket clientS = new Socket("127.0.0.1",80);
		System.out.println("Connected.");
		ObjectOutputStream clientOut = new ObjectOutputStream(clientS.getOutputStream());
		ObjectInputStream clientIn = new ObjectInputStream(clientS.getInputStream());
		boolean running = true;
		while (running) {
			System.out.println("1.GET, 2.PUT, 3.EXIT \n");
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();
			switch (input) {
			
				case "1":
					System.out.println("Sending GET command.\n");
					clientOut.writeObject(Command.GET);
					clientOut.flush();
					Object loaded = clientIn.readObject();
					System.out.println(loaded.toString());
					break;
					
				case "2":
					System.out.println("Sending PUT command.\n");
					clientOut.writeObject(Command.PUT);
					Thread.sleep(1000);
					clientOut.writeObject(objectToSave);
					clientOut.flush();
					break;
					
				case "3":
					System.out.println("Sending EXIT command.\n");
					clientOut.writeObject(Command.EXIT);
					clientOut.flush();
					running = false;
			}
		}
			
		clientOut.close();
		clientIn.close();
		clientS.close();
	}
}
