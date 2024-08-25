#!/bin/sh

export DB_USER=$(cat "$DB_USER_FILE")
export DB_PASSWORD=$(cat "$DB_PASSWORD_FILE")

exec java -jar /rankings.jar