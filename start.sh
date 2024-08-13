echo Copying initial files to persistent volume 'db-data'
docker create --name tmp -v db-data:/data hello-world:latest
docker cp ./data/initial tmp:/data
docker rm tmp

echo Starting docker containers
docker compose up -d --build