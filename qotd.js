var net = require("net");
var fs = require("fs");

var quotes = [];

var server = net.createServer(function(socket) {
	console.log("Connection from " + socket.remoteAddress);
	socket.write(quotes[Math.floor(Math.random() * quotes.length)]);
	socket.end();
});

fs.readFile("./qotd.txt", function(err, buffer) {
	var lines = buffer.toString().split("\n");
	lines.forEach(function(line) {
		if(line !== "") {
			var parts = line.split("|");
			var quote = parts[0];
			var author = parts[1];
			quotes.push("\n" + quote + "\n\n- " + author + "\n\n");
		}
	});

	server.listen(17, function() {
		console.log("Server listening on port 17");
	});
});


