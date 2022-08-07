#!/bin/sh

set -e

docker-compose up -d

until PGPASSWORD=threesmart psql -U threesmart -p 5433 -h localhost -d threesmart --no-password -c '\q'; do
  echo "Postgres is unavailable - sleeping"
  sleep 1
done

echo "Postgres is up - starting backend"
docker start threesmart-backend
