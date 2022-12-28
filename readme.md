# Customer YAMLs
Within k8s folder and minikube folder . let's have a new folder 'services' and within
let's have 'customer'

![](img/customer-service-yaml.png)
Let's create in customer new file deployment.yml

![](img/customer-deployment-yaml.png)
![](img/customer-deployment-yaml-2.png)

Let's apply the yml that we have added
Inside  the k8s folder
![](img/customer-kubectl-apply.png)
I receive this , I think because  nothing is deployed now in k8s
![](img/customer-kubectl-apply-error.png)
I have tried to write minikube ip
![](img/minikube-ip.png)
So I have launch minikube cluster with command _minikube start_
![](img/minikube-start.png)
I have tried to apply customer , but I have incorrectly run the command 
![](img/customer-kubectl-apply-2.png)
If we say _kubectl get pod_
![](img/kubctl get pod.png)
With this command 
![](img/kubectl-describe.png)
we have some errors
![](img/crashloop-backoff.png)
When I try to see logs 
![](img/customer-logs.png)
![](img/customer-logs-error.png)
I don't know why , but i have tried to delete pod customer 
![](img/customer-pod-delete.png)
Then after when I try to see actual pods , I have a new instance o customer running
without errors.
![](img/customer-get-pods.png)

# Fraud and Notification YAMLs
![](img/cmd-c-cmd-v-customer.png)
![](img/fraud-deployment-yml.png)
![](img/fraud-service-yml.png)
<br>And the same thing with notification - > type NodePort

Let/s go to minikube folder
![](img/kubectl-apply-fraud-notif-customer.png)
I have all three microservices not running , it gives me error .
So I stop the minikube 
![](img/minikube-stop.png)
And Restart it
![](img/restart-minikube.png)
After few moments we have CrashLoopBackOff status for all 3 services
![](img/kubectl-get-pods-crashloopbackoff.png)

I have tried a lot of things , and the conclusion is,that when
postgres was uploaded with incorrect password , the only way is to redeploy with 
correct password 
![](img/configmap-postgres-error.png)
I have asked on amigoscode page - > but it didn't worked
![](img/facebook-question.png)
1st - delete minikube
![](img/minikube-delete.png)

2nd - ![](img/minikube-start-2.png)
3rd - Apply bootstraps
![](img/kubectl-apply-postgres-bootstrap.png)
![](img/kubectl-apply-rabbitmq-bootstrap.png)
![](img/kubectl-apply-zipkin-bootstrap.png)
Get pods 
![](img/kubectl-get-pods.png)
4th - Apply service/customer
![](img/kubectl-apply-customer.png)
Because we do not have yet the 3 db customer,notification and fraud , when we logs
into customer , we do not have connection to db 
![](img/logs-customer.png)
![](img/kubctl-delete-customer.png)
![](img/kubctl-delete-customer-2.png)
Enter psql postgres
![](img/kubectl-psql.png)
![](img/create-databases.png)
Reapply service/customer
![](img/reapply-customer.png)
Now when we log into customer all it's ok 
![](img/logs-customer-2.png)
![](img/logs-customer-3.png)
Apply fraud and notification
![](img/kubectl-apply-fraud-notofication.png)
![](img/get-pods-all-running-ok.png)
Now when all is running without errors
We have to look at he services 
We have fraud,customer,notification : fraud and notification ,these are internal ,
they can be accessed by internal ports, but customer is type LoadBalancer as well 
as Zipkin.
![](img/kubectl-get-svc.png)
If we want access our LoadBalancer , we can just say _minikube tunnel_
![](img/minikube-tunnel.png)

After entering the password because port 80 needs privileges, 
lets open up Postman , and we can send the request to customer on port 80.
Before we were accessing on 8080 , but because we've changed its service to listen 
on port 80, we have to say _localhost/api/v1/customers_
![](img/postman-k8s-customer.png)
This time the request should go through all microservices
Let's also check Zipkin on http://127.0.0.1:9411/zipkin/
![](img/zipkin-k8s-1.png)
![](img/zipkin-k8s-2.png)
![](img/zipkin-k8s-3.png)
To stop tunnel for customer and zipkin _ctrl C_ 
![](img/stop-tunnel.png)
