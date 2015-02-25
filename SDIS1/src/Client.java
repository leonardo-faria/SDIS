import java.io.IOException;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		byte[] buf;
		if (args[0].equals("LOOKUP"))
			buf = (args[0] + " " + args[1]).getBytes();
		else
			buf = (args[0] + " " + args[1] + " " + args[2]).getBytes();
		InetAddress address = InetAddress.getLocalHost();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
				8000);
		socket.send(packet);
		socket.close();System.out.println(new String(packet.getData()));
	}

}
