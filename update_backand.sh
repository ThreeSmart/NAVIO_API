set +e

git pull origin master

docker stop threesmart-backend
docker rm threesmart-backend
docker rmi navio_api_backend

docker-compose up -d