package lab4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	private Client() {}

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(args[0]);
			Hello stub = (Hello) registry.lookup(args[1]);
			String response = stub.sayHello();
			System.out.println("response: " + response);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}