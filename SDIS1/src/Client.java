import java.io.IOException;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		byte[] buf;
		if (args[2].equals("LOOKUP"))
			buf = (args[2] + " " + args[3]).getBytes();
		else if (args[0].equals("REGISTER"))
			buf = (args[2] + " " + args[3] + " " + args[4]).getBytes();
		else {
			System.out.println("not valid");
			socket.close();
			return;
		}
		InetAddress address = InetAddress.getByName(args[0]);
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
				Integer.parseInt(args[1]));
		socket.send(packet);
		System.out.println(new String(packet.getData()));
		buf = new byte[512];
		DatagramPacket recieved = new DatagramPacket(buf, buf.length);
		socket.receive(recieved);
		System.out.println(new String(recieved.getData()));
		
		socket.close();
	}

}
