package Lab2;

import java.io.IOException;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		byte[] buf = new byte[256];

		MulticastSocket ms = new MulticastSocket(Integer.parseInt(args[1]));
		ms.joinGroup(InetAddress.getByName(args[0]));
		DatagramPacket msgPacket = new DatagramPacket(buf, buf.length);
		ms.receive(msgPacket);
		String msg = new String(buf, 0, buf.length);
		System.out.println("Socket 1 received msg: " + msg);
		String[] r = new String(msgPacket.getData()).split("\\s");
		System.out.println(r[0]+"|"+r[1]);
		if (args[2].equals("LOOKUP"))
			buf = (args[2] + " " + args[3]).getBytes();
		else if (args[2].equals("REGISTER"))
			buf = (args[2] + " " + args[3] + " " + args[4]).getBytes();
		else {
			System.out.println("not valid");
			socket.close();
			return;
		}
		InetAddress address = InetAddress.getByName(r[1]);
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
				Integer.parseInt(r[0]));
		socket.send(packet);
		System.out.println(new String(packet.getData()));
		buf = new byte[512];
		DatagramPacket recieved = new DatagramPacket(buf, buf.length);
		socket.receive(recieved);
		System.out.println(new String(recieved.getData()));

		socket.close();
	}

}