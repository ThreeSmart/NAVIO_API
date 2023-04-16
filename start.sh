set +e

sudo git pull origin master

sudo docker rmi navio_backend
sudo docker build -t navio_backend .

sudo docker run -p 7801:7801 --name navio-backend navio_backend