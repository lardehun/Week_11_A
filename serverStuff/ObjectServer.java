package serverStuff;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
			File f = new File("c:\\test\\test.ser");
			if(!f.exists()) { 
				f.createNewFile();
			}
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
		boolean ServerRunning = true;
		while (ServerRunning) {
				
				ServerSocket serverS = null;
				try {
					serverS = new ServerSocket(12);
					System.out.println("Connected.");
				} catch (IOException e) {
					System.err.println("Could not listen on port: 12.");
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
				Object objectFromInput = inputStream.readObject();
				if (objectFromInput instanceof Command) {
					if (objectFromInput == Command.GET) {
						mode = ServerMode.LOAD;
						outputStream.writeObject(load());
						outputStream.flush();
						
					}
					else if (objectFromInput == Command.PUT) {
						mode = ServerMode.SAVE;
						save(objectFromInput);
						System.out.println("Object saved.");
					}
					else if (objectFromInput == Command.EXIT) {
						System.out.println("Server Shutdown.");
						clientS.close();
						serverS.close();
						ServerRunning = false;
					}
				}
				inputStream.close();
				outputStream.close();
		}
	}

}
