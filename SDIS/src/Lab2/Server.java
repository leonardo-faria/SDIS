package Lab2;

import java.io.IOException;
import java.net.*;
import java.util.Hashtable;
import java.util.Timer;

public class Server {
	public static void main(String args[]) throws IOException {
		Timer timer = new Timer();
		MultiCasting mc = new MultiCasting(args);
		timer.schedule(mc, 0, 1000);
		run(args);
	}

	public static void run(String args[]) throws IOException {
		Hashtable<String, String> plates = new Hashtable<String, String>();
		@SuppressWarnings("resource")
		DatagramSocket socket = new DatagramSocket(Integer.parseInt(args[0]));

		String answer = new String();
		while (true) {
			byte[] buf = new byte[512];
			DatagramPacket recieved = new DatagramPacket(buf, buf.length);
			socket.receive(recieved);
			System.out.println(new String(recieved.getData()));
			String[] r = new String(recieved.getData()).split("\\s");
			for (int i = 0; i < r.length; i++) {
				r[i] = r[i].trim();
			}
			buf = new byte[512];
			if (r[0].equals("LOOKUP") && r.length == 2) {
				System.out.println("R[1] " + r[1] + ";" + plates.size());
				if (plates.get(r[1]) == null)
					answer = "ERROR";
				else
					answer = plates.get(r[1]);
			} else if (r[0].equals("REGISTER") && r.length == 3) {
				if (plates.get(r[1]) == null) {
					plates.put(r[1], r[2]);
					answer = "OK";
				} else
					answer = "ERROR";
			}
			DatagramPacket sent = new DatagramPacket(answer.getBytes(),
					answer.getBytes().length, recieved.getSocketAddress());
			socket.send(sent);
		}
	}
}
