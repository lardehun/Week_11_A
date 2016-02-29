package serverStuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ObjectServer {
	static ServerMode mode;

	public static Object load() {
		FileInputStream fileIn = null;
		Object e = null;
		try {
			fileIn = new FileInputStream("c:\\test\\test.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
	        e = (Object) in.readObject();
	        in.close();
	        fileIn.close();
			
		} catch (IOException | ClassNotFoundException e1) {
			System.out.println("File not found?.");
			e1.printStackTrace();
		}
		return e;
	}
	
	public static void save(Object object) throws IOException {
		File f = new File("c:\\test\\test.ser");
		if(!f.exists()) { 
			f.createNewFile();
		}
		FileOutputStream fout = new FileOutputStream("c:\\test\\test.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		try {
			oos.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
		oos.close();
		System.out.println("Done");
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket serverS = null;
		try {
			serverS = new ServerSocket(6666);
			System.out.println("Connected. \n");
		} catch (IOException e) {
			System.err.println("Could not listen on port: 6666.");
			e.printStackTrace();
			System.exit(1);
		}
		
		Socket clientS = null;
		try {
			clientS = serverS.accept();
			System.out.println("Accepted.");
		} catch (IOException e) {
			System.err.println("Accept failed.");
			e.printStackTrace();
		}
		
		System.out.println("Request Accepted.");
		ObjectInputStream inputStream = new ObjectInputStream(clientS.getInputStream());
		ObjectOutputStream outputStream = new ObjectOutputStream(clientS.getOutputStream());
		
		boolean serverRunning = true;
		while (serverRunning) {
			Object objectFromInput = null;
			try {
				objectFromInput = inputStream.readObject();
			} catch (java.net.SocketException e) {
				e.printStackTrace();
				System.out.println("Client ShutDown.");
				break;
			}
			
				if (objectFromInput instanceof Command) {
					if (objectFromInput == Command.GET) {
						mode = ServerMode.LOAD;
						outputStream.writeObject(load());
						outputStream.flush();
						continue;
						
					}
					else if (objectFromInput == Command.PUT) {
						mode = ServerMode.SAVE;
						Object dataToSave = inputStream.readObject();
						save(dataToSave);
						System.out.println("Object saved.");
						continue;
					}
					else if (objectFromInput == Command.EXIT) {
						System.out.println("Server Shutdown.");
						clientS.close();
						serverS.close();
						serverRunning = false;
					}
				}
		}
		inputStream.close();
		outputStream.close();
	}

}
