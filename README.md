# qotd-server
This is a TCP implementation of the [Quote of the Day protocol](https://tools.ietf.org/html/rfc865) in C++. It listens on port 17 and sends a random quote from `qotd.txt`. 

### Try it out
To run:

```bash
make qotd
sudo ./qotd # You need to use sudo so it can run on port 17
```

To try it out run:
```bash
nc localhost 17
```

### Live Demo
I also have a live demo at `hashanp.xyz`:
```bash
nc hashanp.xyz 17
```
