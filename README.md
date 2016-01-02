# qotd-server
This is a TCP implementation of the [Quote of the Day protocol](https://tools.ietf.org/html/rfc865) in C++, Node.js and Java. They listen on port 17 and send a random quote from `qotd.txt`. 

### Try it out
To run the C++ version:

```bash
make qotd
sudo ./qotd # You need to use sudo so it can run on port 17
```

To run the Node.js version:
```bash
sudo node qotd.js # You need to use sudo so it can run on port 17
```

To run the Java version:
```bash
javac qotd.java
sudo java qotd
```

There also exists a multi-threaded Java version:
```bash
javac MultiThreadedQOTD.java
sudo java MultiThreadedQOTD
```

To try it out run:
```bash
nc localhost 17
```

### Live Demo
I also have a live demo of the C++ version at `hashanp.xyz`:
```bash
nc hashanp.xyz 17
```
