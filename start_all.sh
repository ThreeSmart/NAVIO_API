#!/bin/sh

set -e

sudo git pull origin master

sudo docker-compose up -d

until PGPASSWORD=threesmart psql -U threesmart -p 5433 -h localhost -d threesmart --no-password -c '\q'; do
  echo "Postgres is unavailable - sleeping"
  sleep 1
done

echo "Postgres is up - starting backend"
sudo docker start threesmart-backend
