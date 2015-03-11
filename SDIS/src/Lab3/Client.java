package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	public static void main(String args[]) throws IOException {

		Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
		byte[] buf;
		if (args[2].equals("LOOKUP"))
			buf = (args[2] + " " + args[3]).getBytes();
		else if (args[2].equals("REGISTER"))
			buf = (args[2] + " " + args[3] + " " + args[4]).getBytes();
		else {
			System.out.println("not valid");
			socket.close();
			return;
		}
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));

		out.println(new String(buf));

		buf = new byte[512];

		System.out.println(in.readLine());


		socket.close();
	}
}
