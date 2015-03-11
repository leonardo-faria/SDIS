package Lab2;

import java.io.IOException;
import java.net.*;
import java.util.TimerTask;

public class MultiCasting extends TimerTask {
	byte[] buf;
	DatagramPacket packet;
	MulticastSocket ms;

	public MultiCasting(String args[]) throws IOException {
		buf = (args[0] + " " + InetAddress.getLocalHost().getHostName())
				.getBytes();
		ms = new MulticastSocket(Integer.parseInt(args[2]));
		ms.setTimeToLive(2);
		System.out.println(args[1]);
		packet = new DatagramPacket(buf, buf.length,
				InetAddress.getByName(args[1]), Integer.parseInt(args[2]));
	}

	@Override
	public void run(){
		System.out.println("Sending to port " + packet.getPort() + " on adr " + packet.getAddress());
		try {
			ms.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
