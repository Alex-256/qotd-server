import scala.io.Source
import scala.util.Random
import java.net.ServerSocket
import java.io.{OutputStreamWriter, IOException}
import java.util.concurrent.{Executors, ExecutorService}

object QOTD {
	val rand = new Random()
	val pool = Executors.newFixedThreadPool(20)

	def main(args: Array[String]) {
		val quotes = Source.fromFile("../qotd.txt").getLines().map(line  => {
			val parts = line.split("\\|")
			val quote = parts(0)
			val author = parts(1)
			"\n\"" + quote + "\"\n\n- " + author + "\n\n"
		}).toList

		try {
			val server = new ServerSocket(17)
			println("Server listening on port 17")
			while(true) {
				try {
					val conn = server.accept();
					val t = new Runnable {
						def run() {
							println("Connection from " + conn.getInetAddress())
							val writer = new OutputStreamWriter(conn.getOutputStream(), "ASCII")
							writer.write(quotes(rand.nextInt(quotes.length)))
							writer.flush()
							conn.close()
						}
					}
					pool.execute(t)
				} catch {
					case e: IOException => println(e)
				}
			}
		} catch {
			case e: IOException => println(e)
		}
	}
}
