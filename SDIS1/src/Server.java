import java.awt.print.Printable;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {
	public static void main(String[] args) throws IOException {
		ArrayList<Plate> plates = new ArrayList<Plate>();
		DatagramSocket socket = new DatagramSocket(Integer.parseInt(args[0]));
		byte[] buf = new byte[256];

		while (true) {
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			System.out.println(new String(packet.getData()));
			System.out.println();
			String[] r = new String(packet.getData()).split("\\s");
			if (r[0].equals("LOOKUP") && r.length == 2) {
				try {
					Plate.lookup(plates, r[1]);
				} catch (Exception e) {
					System.out.println("FAILE");
				}
			} else if (r[0].equals("REGISTER") && r.length == 3) {
				try {
					Plate.lookup(plates, r[1]);
					System.out.println("FAIL");
				} catch (Exception e) {
					plates.add(new Plate(r[2], r[1]));
				}
			}
			socket.receive(packet);
			System.out.println(new String(packet.getData()));
		}
	}
}
