1.- Git clone repo.

2.- Execute

```sh
mvn clean package
```
All tests will be executed

3.- Build docker image

```sh
docker build .
```

4.- Run docker with environment variables 
```sh
 docker run -e URL_DATASOURCE=jdbc:mariadb://<db_ip>:3306/<db> -e SERVER_PORT=<server_port> -e DATABASE_PWD=<db_password> -e DATABASE_USER=<db_user> <image>
```