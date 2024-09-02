#!/bin/sh

echo "Starting Maven package..."
cd rankings
./mvnw package -DskipTests
cd ..

if [ $? -ne 0 ]; then
    echo "Maven build failed. Exiting script."
    exit 1
fi

echo Copying initial files to persistent volume 'db-data'
docker create --name tmp -v db-data:/data hello-world:latest
docker cp ./data/initial tmp:/data
docker rm tmp

echo Starting docker containers
docker compose up -d --build