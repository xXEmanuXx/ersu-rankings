@echo off

REM Perform git pull
git pull

REM Get the build version (commit hash)
FOR /F %%i IN ('git rev-parse HEAD') DO SET BUILD_VERSION=%%i

REM Get the current date and time in UTC
FOR /F "tokens=1-4 delims=:.," %%a IN ('powershell -Command "Get-Date -Format 'yyyy-MM-ddTHH:mm:ssZ'"') DO SET TIMESTAMP=%%a:%%b:%%c.%%dZ

REM Echo the build version
echo %TIMESTAMP%: Releasing new server version. %BUILD_VERSION%

echo %TIMESTAMP%: Building new server JAR
cd rankings
call mvnw.cmd package -DskipTests
cd ..

echo %TIMESTAMP%: Running build...

REM Remove old containers and build new ones
docker compose rm -f
docker compose build

REM Get the ID of the old container
FOR /F %%i IN ('docker ps -aqf "name=server"') DO SET OLD_CONTAINER=%%i

REM Scale the server up
echo %TIMESTAMP%: Scaling server up...
SET BUILD_VERSION=%BUILD_VERSION%
docker compose up -d --no-deps --scale server=2 --no-recreate server

REM Wait for 30 seconds
timeout /t 30 /nobreak

REM Scale the old server down
echo %TIMESTAMP%: Scaling old server down...
docker container rm -f %OLD_CONTAINER%
docker compose up -d --no-deps --scale server=1 --no-recreate server

echo %TIMESTAMP% Reloading caddy
for /f %%i in ('docker ps -aqf "name=caddy"') do set CADDY_CONTAINER=%%i
docker exec %CADDY_CONTAINER% caddy reload -c /etc/caddy/Caddyfile