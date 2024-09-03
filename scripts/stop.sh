#!/bin/bash

echo Clearing db-loader flag file
docker run -d --name tmp -v db-data:/data busybox sleep 3600
docker exec tmp rm -f /data/db_loader_done
docker stop tmp
docker rm tmp

docker compose down -v