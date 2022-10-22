- Add dependency 'spring-cloud-starter-sleuth' to customer, eureka-server, fraud, notification 

![](img/sleuth.png)

- Zipkin container 
![](img/docker-compose-zipkin.png)
![](img/zipkin-container.png)

- docker-zipkin-logs
![](img/docker-zipkin-logs.png)
![](img/zipkin-ui.png)

- Add 'spring-cloud-sleuth-zipkin' dependency to customer, eureka-server, fraud and notification modules
If spring-cloud-sleuth-zipkin is available then the app will generate and report Zipkin-compatible traces via HTTP.
![](img/spring-cloud-sleuth-zipkin.png)

- Configure the location of the service using spring.zipkin.baseUrl in application.yml file for each microservice

![](img/zipkin-base-url.png)

- After run each microservice and sending request in postman 
![](img/postman-request.png)
we have zipkin in action with total of spans and with trace id etc
![](img/zipkin-in-action.png)