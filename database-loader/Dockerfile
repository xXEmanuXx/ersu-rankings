FROM ubuntu:latest

RUN apt update && apt install -y gcc php php-mysql

WORKDIR /db-loader

# Copy current directory on host "./database-loader" on container directory "/db-loader"
COPY . .

RUN chmod +x script.sh

CMD ["sh", "script.sh"]