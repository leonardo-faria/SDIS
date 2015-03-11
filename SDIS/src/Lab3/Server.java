package Lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Hashtable;

public class Server {
	public static void main(String args[]) throws IOException {
		ServerSocket srvSocket = new ServerSocket(Integer.parseInt(args[0]));

		Hashtable<String, String> plates = new Hashtable<String, String>();
		while (true) {
			Socket echoSocket = srvSocket.accept();
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			byte[] buf = new byte[512];

			String query = in.readLine();

			System.out.println(query);

			String[] r = query.split("\\s");
			for (int i = 0; i < r.length; i++) {
				r[i] = r[i].trim();
			}
			String answer = null;
			buf = new byte[512];
			if (r[0].equals("lookup") && r.length == 2) {
				System.out.println("R[1] " + r[1] + ";" + plates.size());
				if (plates.get(r[1]) == null)
					answer = "ERROR";
				else
					answer = plates.get(r[1]);
			} else if (r[0].equals("register") && r.length == 3) {
				if (plates.get(r[1]) == null) {
					plates.put(r[1], r[2]);
					answer = "OK";
				} else
					answer = "ERROR";
			}

			out.println(answer);

		}

	}
}
