set +e

docker stop threesmart
docker rm threesmart

docker run -d -p 5434:5432 \
              -e POSTGRES_USER=threesmart \
              -e POSTGRES_DB=threesmart \
              -e POSTGRES_PASSWORD=threesmart \
              --name threesmart-local \
              postgres