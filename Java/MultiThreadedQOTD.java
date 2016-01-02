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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedQOTD {
	private static ArrayList<String> quotes = new ArrayList<String>();
	private static Random rand = new Random();
	private static ExecutorService pool = Executors.newFixedThreadPool(20);

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader("./qotd.txt"));
			String line;
			while((line = in.readLine()) != null) {
				String[] parts = line.split("\\|");
				String quote = parts[0];
				String author = parts[1];
				quotes.add("\n\"" + quote + "\"\n\n- " + author + "\n\n");
			}
			server = new ServerSocket(17);
			System.out.println("Server listening on port 17");
		} catch(FileNotFoundException e) {
			System.out.println("Error: qotd.txt file not found");
			System.exit(-1);
		} catch(BindException e) {
			System.out.println("Couldn't bind to port 17. Did you run as sudo?");	
			System.exit(-1);
		} catch(IOException e) {
			System.out.println("Error: reading qotd.txt");
			System.exit(-1);
		}
		while(true) {
			try {
				final Socket connection = server.accept();
				Runnable task = () -> {
					try {
						System.out.println("Connection from " + connection.getInetAddress());
						Writer writer = new OutputStreamWriter(connection.getOutputStream(), "ASCII");
						writer.write(quotes.get(rand.nextInt(quotes.size())));
						writer.flush();
					} catch(IOException e) {
						System.out.println(e);
					} finally {
						try {
							connection.close();
						} catch(IOException e) {
							System.out.println(e);
						}
					}
				};
				pool.execute(task);
			} catch(IOException e) {
				System.out.println(e);
			}
		}
	}
}
