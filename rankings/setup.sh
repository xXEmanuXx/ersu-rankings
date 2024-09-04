#!/bin/bash

export DB_USER=$(cat "$DB_USER_FILE")
export DB_PASSWORD=$(cat "$DB_PASSWORD_FILE")

while [ ! -f /data/db_loader_done ]; do
  echo "Waiting for db-loader to complete..."
  sleep 5
done

exec java -jar /rankings.jar