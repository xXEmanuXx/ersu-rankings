@echo off

echo Starting Maven package...
cd rankings
call ./mvnw.cmd package -DskipTests
cd ..
if ERRORLEVEL 1 (
    echo Maven build failed. Exiting script.
    exit /b 1
)

echo Copying initial files to persistent volume 'db-data'
docker create --name tmp -v db-data:/data hello-world:latest
docker cp ./data/initial tmp:/data
docker rm tmp

echo Starting docker containers
docker compose up -d --build