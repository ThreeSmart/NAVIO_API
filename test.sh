set +e

git pull origin master

docker stop talenty-frontend
docker rm   talenty-frontend
docker rmi  talenty_frontend

docker stop talenty-backend
docker rm   talenty-backend
docker rmi  talenty_backend

docker-compose up -d