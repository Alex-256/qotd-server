import java.net.ServerSocket;
import java.net.Socket;
import java.net.BindException;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class QOTD {
	private static ArrayList<String> quotes = new ArrayList<String>();
	private static Random rand = new Random();

	public static void main(String[] args) {
		try {
			BufferedReader in = new BufferedReader(new FileReader("../qotd.txt"));
			String line;
			while((line = in.readLine()) != null) {
				String[] parts = line.split("\\|");
				String quote = parts[0];
				String author = parts[1];
				quotes.add("\n\"" + quote + "\"\n\n- " + author + "\n\n");
			}
			ServerSocket server = new ServerSocket(17);
			System.out.println("Server listening on port 17");
			while(true) {
				try {
					Socket connection = server.accept();
					System.out.println("Connection from " + connection.getInetAddress());
					Writer writer = new OutputStreamWriter(connection.getOutputStream(), "ASCII");
					writer.write(quotes.get(rand.nextInt(quotes.size())));
					writer.flush();
					connection.close();
				} catch(IOException e) {
					System.out.println(e);
				}
			}
		} catch(FileNotFoundException e) {
			System.out.println("Error: qotd.txt file not found");
		} catch(BindException e) {
			System.out.println("Couldn't bind to port 17. Did you run as sudo?");	
		} catch(IOException e) {
			System.out.println("Error: reading qotd.txt");
		}
	}
}

