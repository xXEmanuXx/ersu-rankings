#!/bin/bash

echo "Starting Maven package..."
cd ../rankings
./mvnw package -DskipTests
cd ../scripts

if [ $? -ne 0 ]; then
    echo "Maven build failed. Exiting script."
    exit 1
fi

echo "Creating persistent volumes"
docker volume create caddy-data
docker volume create db-data

echo "Copying initial files to persistent volume 'db-data'"
docker run --rm -v db-data:/data -v "$(pwd)/../data":/backup busybox sh -c "cp -r /backup/* /data"

echo "Starting docker containers"
docker compose up -d --build
