FROM ubuntu:latest

RUN apt update && apt install -y gcc libc6-dev php php-mysql

WORKDIR /db-loader

# Copy current directory on host "./db-loader" on container directory "/db-loader"
COPY . /db-loader

RUN chmod +x setup.sh

ENTRYPOINT ["/db-loader/setup.sh"]