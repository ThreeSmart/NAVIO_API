set +e

git pull origin master

docker stop talenty-backend
docker rm talenty-backend
docker rmi navio_api_backend

docker-compose up -d
