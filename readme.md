# 1 - Disabling Eureka 
Kubernetes offers service discovery for free , we have one less service to manage.
Disabling Eureka in customer , fraud and notification services
![](img/eureka-enable-false.png)

# 2 - Refactor Feign Clients
![](img/clients-default-kube.png)
![](img/notif-url.png)
![](img/fraud-url.png)

for customer , fraud and notification 
![](img/properties-sources.png)

# 3 - Adding SPRING_PROFILES_ACTIVE=default

We open docker-compose.yml file and first we comment eureka ans api gateway parts
![](img/docker-compose-yml-1.png)
We also temporally comment in docker-compose.yml file customer , fraud and notification
![](img/docker-compose-yml-2.png)

We want just to start with docker-compose rabbitmq , zipkin pgadmin and postgres.
Within the root let's just say<br>
![](img/docker-compose-up.png)

Now let's run from Idea customer , fraud and notification.
It gives us an error , Nelson thinks that it's a bug and we have to sau in IntelliJ ,
that we want default profile. 
![](img/error-running-customer-idea.png)
![](img/seting-up-env-var-default.png)
For fraud and notification we do the same thing
Now all three services are running from idea
![](img/3-services-running.png)
And pgAdmin with zipkin and RabbitMQ are also running from docker-compose.yml
![](img/3-services-docker-compose-running.png)
In local development we send request directly to customer with postman port 8080
![](img/postman-customer.png)

# 4 - Kube profile

We also have to have profile with kube suffix or the similar file within customer,fraud and 
notification
![](img/application-kube-yml.png)

# 5 - Building new Images and Testing Docker Compose

The final thing that we have to do is to make sure that we can run our microservices with docker.
Let's actually make sure that we can basically test things with Docker.
For example if we have a new joiners in our team , then they can jus run a set of containers and 
have the entire application stack working without no effort.
Inside of client -> resources , we need to create clients-docker.properties
![](img/clients-docker-properties.png)

Let's open up the docker profile and uncomment customer , fraud and notification that we have 
made before temporally and remove the section depends on eureka etc 
Now let's test it out , open up the terminal , let's build a new image with 
![](img/mvn-build-docker-image.png)
![](img/mvn-build-docker-image-build-success.png)
So we have new images, now we can pull those images with command <br>
_docker compose pull_<br>
Now we say docker compose up -d
![](img/docker-compose-up 2.png)

Now let's open up Postman and send directly request to customer which is on 8080
![](img/postman-customer-2.png)
Stop containers
![](img/docker-compose-down.png)