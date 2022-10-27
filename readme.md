- RabbitMQ conatainer : using docker we will edit the docker compose file
![](img/rabbit-mq-docker-compose.png)
![](img/pulling-container-1.png)
![](img/pulling-container-2.png)
![](img/docker-ps.png)
![](img/rabbitmq-management-1.png)
![](img/rabbitmq-management-2.png)


- AMQPTemplate and JacksonConverter :

 1 . Create new module amqp

 2 . Create a config class 'RabbitMQConfig'

 3 . AMQPTemplate and JacksonConverter

![](img/Config.png)



- SimpleRabbitListener : After we have configured the rabbit template that allows 
us to send messages, now in order for app to consume messages 
from the queues , we have to set up the listener (SimpleRabbitListener)
This is the setup required for us to send and receive messages from queues.
Obviously this is not enough because we haven't set up the exchange nor the queues.
We didn't actually bind the exchange to  particular queue.
![](img/SimpleRabbitListenerContainerFactory.png)

- We will bind the exchange to queue. Adding those dependencies 
to 'customer' and 'notification' microservices. 
![](img/dependencies-customer-notification.png)
Add to application yaml file in 'notification' module  
![](img/app-yml-rabbitmq.png)
Add config class 'NotificationConfig' in 'notification' module  
![](img/NotificationConfig.png)

- Topic Exchange , Queue and binding together
![](img/topic-exchange-binding-queue.png)     

- Message producer : in 'amqp' module we have to create a new class as component 'RabbitMQMessageProducer'
![](img/mesage-producer.png)
- Publishing messages: within 'notification' module in main class
we will bring in the 'RabbitMQMessageProducer' from 'amqp' module to test how it works and edit application yml
file to configure port connection rabbitmq: addresses: localhost:5672 (number like it's configured in docker)
![](img/notification-app.png)

![](img/app-yml-notification.png)

After running 'eureka-server' and 'notification' we will see in action
the queue in the rabbitMQ UI (Video) 

[![Watch the video](https://img.youtube.com/vi/Yat4ClMGqHA/maxresdefault.jpg)](https://youtu.be/Yat4ClMGqHA)


- Customer microservice publishing message to queue :
Edit application yml file in customer microservice to configure 
port for rabbit mq ( localhost:5672 (number like it's configured in docker) )
![](img/customer-app-yml-rabbit-port.png)

Edit customer main app 
![](img/customer-main-app.png)

Edit 'CustomerService' class
![](img/customer-service.png)

After running all microservices and sending request from postman (Video)
[![Watch the video](https://img.youtube.com/vi/0-TWug7JECU/maxresdefault.jpg)](https://youtu.be/0-TWug7JECU)

- @RabbitListener
In notification module let create new module 'rabbitmq' and new class as component 'NotificationConsumer' 
![](img/notification-consumer.png)

We have from previous postman request a message in queue pending.
![](img/rabbit-mq-in-queue-1.png)

After re-run notification service the message was picked from the queue 
![](img/rabbit-mq-message-picked.png)
