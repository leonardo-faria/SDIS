package Lab1;

import java.net.*;
import java.util.TimerTask;

public class Broadcast extends TimerTask {

	public void run() {

		byte[] buf = (args[0] + " " + InetAddress.getLocalHost().getHostName())
				.getBytes();
		@SuppressWarnings("resource")
		MulticastSocket ms = new MulticastSocket(Integer.parseInt(args[2]));
		ms.setTimeToLive(2);
		DatagramPacket packet = new DatagramPacket(buf, buf.length,
				InetAddress.getByName(args[1]), Integer.parseInt(args[2]));
		ms.send(packet);
	}
	
}
