# sprinboot-docker-haproxy
# Projeto utilizando docker, sprinboot, mysql, haproxy, testando a escalabilidade aplicacao no docker
Objetivo consiste em criar 3 instancias da app springboot, conectada com banco mysql
e como loadbalancer o haproxy. 
todas aplicações rodam em container no docker e se comunicam através de uma rede customizada criada no docker.

Escalando a aplicacao passo a passo 
  - PASSO 1 : baixar e CRIAR UMA IMAGEM MYSQL DOCKER
	docker pull mysql:8.0.0
  
  - PASSO 2 : CRIAR UM VOLUME PARA O CONTAINER MYSQL
	docker volume create --my-vol my-vol
	
  - PASSO 3: CRIAR REDE CUSTOMIZADA DOCKER
    docker network create frontend
	docker network create backend
  
  - PASSO 3 :   CRIAR E SUBIR O CONTAINER DO MYSQL.
	docker container run -d --name mysql -v my-vol:/var/lib/mysql --network=backend -e MYSQL_ROOT_PASSWORD=root -e bind-address=0.0.0.0 mysql:8.0.0
  
  - PASSO 4 : BUILDAR PROJETO Maven
	mvn package 
  
  - PASSO 5 : CRIA A IMAGEM DO PROJETO SPRING BOOT NO DOCKER
	docker build -t product-app:5.0 .
 
  - PASSO 6 : CRIA CONTAINERS DESSA IMAGEM DO PROJETO 
	docker create --name produtosapp1 -e DBHOST=mysql --network backend product-app:5.0
	docker create --name produtosapp2 -e DBHOST=mysql --network backend product-app:5.0
	docker create --name produtosapp3 -e DBHOST=mysql --network backend product-app:5.0

  - PASSO 7 : CONECTAR OS CONTAINERS A REDE FRONTEND DO DOCKER 
	docker network connect frontend produtosapp1
	docker network connect frontend produtosapp2
	docker network connect frontend produtosapp2
	
 - PASSO 8 :  STARTAR OS CONTAINERS
	docker start produtosapp1 produtosapp2 produtosapp3
  
 - PASSO 9 : CRIAR O CONTAINER DO HAPROXY, DO DIRETORIO ONDE TEM O ARQUIVO CONF.
	CASO NAO TENHA A IMAGEM NO DOCKER BAIXAR "DOCKER PULL ...."
	docker container run -d --name loadbalancer --network frontend -v "${pwd}/haproxy.cfg:/usr/local/etc/haproxy/haproxy.cfg" -p 80:80 haproxy:1.7.0

