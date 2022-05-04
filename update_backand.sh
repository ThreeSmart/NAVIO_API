set +e

sudo git pull origin master

sudo docker stop threesmart-backend
sudo docker rm threesmart-backend
sudo docker rmi navio_api_backend

sudo docker-compose up -d