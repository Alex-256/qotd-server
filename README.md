# qotd-server
This is a TCP implementation of the [Quote of the Day protocol](https://tools.ietf.org/html/rfc865) in C++, Node.js, Java, Python3 and Scala. They listen on port 17 and send a random quote from `qotd.txt`. 

You need to use sudo so it can run on port 17.

### Try it out
To run the C++ version:

```bash
make qotd
sudo ./qotd
```

To run the Node.js version:
```bash
sudo node qotd.js
```

To run the Java version:
```bash
javac QOTD.java
sudo java QOTD
```

To run the Python version:
```bash
chmod +x qotd.py
sudo ./qotd.py
```
or
```bash
sudo python3 qotd.py
```
You must have already installed python3.

There also exists a multi-threaded Java version:
```bash
javac MultiThreadedQOTD.java
sudo java MultiThreadedQOTD
```

To run the Scala version:
```bash
scala qotd.scala
```

To try it out run:
```bash
nc localhost 17
```
or
```bash
telnet localhost 17
```
