FROM gliderlabs/alpine
RUN apk add --no-cache build-base
ADD . /app
RUN g++ -o /app/C++/qotd /app/C++/qotd.cc
CMD ["/app/C++/qotd", "/app/qotd.txt"]
EXPOSE 17
