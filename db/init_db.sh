#!/bin/bash
set -e

PGPASSWORD=threesmart psql -U threesmart -p 5432 -d threesmart -f /docker-entrypoint-initdb.d/initial_schema.sql
