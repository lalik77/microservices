# Docker Images and Containers
You take your code and build Docker Image.
From this Docker Image you can run a container. 
![](img/docker-images-and-containers.jpeg)
From  one Docker Image you can run multiples containers
![](img/docker-images-and-containers-2.jpeg)

What is Docker Image
![](img/docker-images.jpeg)

What is Container
![](img/container.jpeg)
Let's go inside the container.
So within a container it contains everything what your application needs.
It can contain the OS, tools and the software.
![](img/containers-2.jpeg)

# Docker Architecture
  Docker follows Client-Server architecture, which includes the three main components that are Docker Client,
  Docker Host, and Docker Registry.
![](img/docker-architecture.jpeg)

# Docker Registries
  A Docker registry is a storage and distribution system for named Docker images. 
The same image might have multiple different versions, identified by their tags.
Pull command - pulls an image to our local machine(to the Host)
Push command - take an image and push to Docker Hub(any register) 
![](img/docker-registries.jpeg)
Docker Hub - most popular docker registry, then we have other Docker Registries such as ECR 
(Amazon Elastic Container Registry) or GCR (Google Container Registry).Then we have also GitHub packages

- Docker Hub
It's showing my repositories(empty)
![](img/docker-hub-repo.png)
Amigoscode DocekrHub repo example
![](img/amigoscode-dockerhub-repo.jpeg)

# Spring Boot Maven Plugin and Jib
![](img/service-jar-image.png)
We take out microservices and put them into a jar which we than we want to build 
an imageand then from this amage we can run the application itself.
There are a couple ways than we can build Docker images:
- Using Spring Boot Maven Plugin
![](img/packaging-oci-images.png)
- Jib
![](img/jib.png)
The benefit of using Jib and also Spring Boot Maven Plugin is that there
is no need to write our own Docker files and calling docker build/push

# Jib Maven Plugin
We will configure the main .pom file
![](img/jib-maven-plug-config.png)
![](img/jib-maven-plugin-config-2.png)
![](img/jib-maven-plugin-config-3.png)

The [error](https://github.com/amigoscode/microservices/issues/12) that gives me the version used in the course. 

Now that we have the base configuration, next add in the configuration necessary to our sub modules.
(customer,apigw,fraud,eureka0server and notification)

# Jib Sub Module Configuration 

So for amqp as well as clients there is no need for them to include jib.
customer , apigw , eureka-server , fraud and notification -> we want to build a docker image for those.
So let's start with apigw. 
The rest of the configuration will be the same for all other modules.
So open up the pom.xml for apigw -> 
![](img/apigw-pom.png)

![](img/apigw-pom-profiles.png)

# Build And Push
From terminal inside apigw folder let's build and push , before i log out and after i login.
![](img/terminal-build-push.png)

![](img/build-success.png)
![](img/docker-hub-apigw-1.png)
![](img/docker-hub-apigw-2.png)

# Exercice
We saw that within 'apigw' we can run 
![](img/command-1.png)
Now this profile if i remove it and just say clean package 
![](img/command-2.png)
it will not push to dockerhub the image.
The exercice is - from the root of our microservices , ie 'lalik-services'
![](img/terminal.png)
if we type 'mvn clean package -P build-docker-image' it will just
going to build and push 'apigw'
What we want to do is to include the necessary changes for all our submodules
so that they can also be pushed in one go.

The solution is that we have to add this part from pom.xml file from 'apigw' module 
to other pom.xml files in other submodules(customer,fraud,eureka-server)
![](img/pom-profiles-build-section.png)
After we repeat in the terminal the same command for all project 
![](img/mvn-clean-package-profile-all.png)
![](img/mvn-clean-package-profile-all-2.png)
And we have now our images in dockerhub
![](img/dockerhub-our-microservices.png)

Next Step for us , is to learn how we are going to run containers from these images locally!

# Adding Eureka Server and ApiGW to Docker Compose

Now that we have a Docker image for all of our services, 
we have to open Docker compose file

![](img/docker-compose-1.png)

We are going to add in here all of our services.
So tha we can say docker compose up and all of our services will start as a 
Docker container.
Let's define 'apigw' as well as 'eureka-server' in our file
![](img/docker-compose-2.png)

Next let's learn about spring profiles as well as docker network

# Docker Network
If we try to start eureka-server and apigw  from images it will not work,
because if we open up the application.yml for both , have a look 

![](img/app-yaml-apigw.png)
Because apigw and eureka-server will run inside of container, 
localhost will not work,because there are two separate containers!
What we have to do instead - is use docker network!
Let's open docker compose file and scroll to the bottom .

![](img/docker-compose-network-1.png)

We already have one for postgres, we are going to create another one, 
and name it spring.Now within apigw and eureka-server ,  we can say
networks: -spring

![](img/docker-compose-network-2.png)

![](img/docker-compose-network-3.png)

Next let's configure files application.yml for boths in the next step with spring profiles!

# Spring Profiles

So the correct url that we have to use in order for having apigw talk to the 
eureka-server and zipkin container, this cannot be a localhost in application.yml
![](img/app-yaml-apigw.png)

Instead it has to be the container name.
So let's open the application.yml for apigw
and change localhost to corresponding container names
![](img/application-yml-apigw.png)

If we package basically  our application at this way , we are actually
losing the local way to run it. Let's revert changes and duplicate 
application.yml  

![](img/application-yml-duplicate-2.png)

Everything that comes after dash(-) , ie docker, this will be the profile!
And now inside of docker-compose.yml lets add ...

![](img/docker-compose-env.png)
Let's do the same thing for eureka-server (duplicate application.yml and add environment)
And with this - we are ready to fire up these two containers and test our changes.
Let's do this at the next step!

# ApiGw and Eureka Server docker containers

So because we have added a new file application-docker.yml , we have to build new images because
the current images does not contain application-docker.yml.
Let's run again 

![](img/command-1.png)

![](img/build-and-push-success-1.png)

Let's launch via docker composeup -d
![](img/docker-compose-command.png)
I have an error
![](img/error-after-docker-compose-command.png)
I found it - because login was incorrect  
![](img/error-docker-compose-1.png)
The second time i have another error after corrected the login
![](img/error-docker-compose-2.png)

I don't know why , I have removed in DockerHub all repo created , 
I have restored back the version like in the course for Jib
![](img/jib-version-back.png)
I have still error
![](img/error-docker-compose-3.png)

When I took a look in dockerhub , i found another thing

![](img/eureka-sever-mistake-1.png)

After renamed module correctly to eureka-server, the error was removed .

![](img/docker-compose-up-success.png)
![](img/docker-compose-up-success-2.png)

So let's check the logs for apigw and eureka-server
![](img/docker-logs-apigw.png)
![](img/docker-logs-apigw-2.png)
![](img/docker-logs-apigw-3.png)

So if we open up in our web browser localhost:8761
![](img/call-eureka-browser.png)

# Exercice 2
Add the necessary configuration to the docker-compose yml  and other configurations
in application-docker.yml 

docker-compose.yml have been changed and configured like int the video solution, but i 
had errors.
![](img/docker-compose-logs-1.png)
![](img/docker-compose-logs-2.png)

To fix it I have added tag for 14 version into docker-compose
![](img/postgres-tag-14-1.png)
![](img/docker-compose-logs-3.png)
![](img/docker-compose-logs-4.png)
Now we have our services running on docker and when we try wit postman , it's working

![](img/postman-success-docker.png)
docker -ps --format=$FORMAT
![](img/docker-ps-format.png)
Zipkin
![](img/zipkin-success.png)
Eureka Server 
![](img/eureka-server-success.png)
PgAdmin
![](img/pgadmin-notification.png)

So let's say i want to stop everything 
![](img/docker-compose-stop.png)
If we want to start let's say 
![](img/docker-compose-start.png)
Or if we want to get rid of everything 
![](img/docker-compose-down.png)

Docker compose is amazing to testing containers locally!

