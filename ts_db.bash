set +e

docker stop employee-management
docker rm employee-management

docker run -d -p 5433:5432 -e POSTGRES_USER=threesmart -e POSTGRES_DB=employee_management -e POSTGRES_PASSWORD=threesmart --name employee-management postgres
