#!/bin/sh
set -e

sudo docker run --name navio-db \
       -v $(pwd)/db-data:/var/lib/postgresql/data \
       -v $(pwd)/db:/docker-entrypoint-initdb.d/ \
       -e POSTGRES_DB=navio \
       -e POSTGRES_USER=navio \
       -e POSTGRES_PASSWORD=navio-password \
       -p 5433:5432 -d postgres