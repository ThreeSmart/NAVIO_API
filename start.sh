set +e

# sync changes
sudo git pull origin master

# remove container
sudo docker stop navio-backend
sudo docker rm navio-backend

# update image
sudo docker rmi navio_backend
sudo docker build -t navio_backend .

# start new container
sudo docker run -d -p 7801:7801 --name navio-backend navio_backend