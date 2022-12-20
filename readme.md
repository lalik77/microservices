
Before begin - we have to watch XIII - Kubernetes AKA k8s videos 
- running KubeCluster Locally - video 5 - minikube ...
- in this course we will use minikube
- Installation : Docker + Minikube<br><br>
Video 6 - Minikube installation
  ![](img/minikube-installation.png)
  ![](img/minikube-installation-2.png)
  ![](img/minikube-installation-3.png)
[link](https://minikube.sigs.k8s.io/docs/start/)
- Preferences before change it to 6 gb

- if we say minikube start --help
  ![](img/minikube-start-help-memory-1.png)
  ![](img/docker-preferences-bydefault.png)
  ![](img/minikube-start-help-memory-1.png)
- we say minikube start --memory=4g
  ![](img/minikube-start-mwmory-4g.png)
  ![](img/minikube-start-memory-4g-finish-intsllation.png)
  ![](img/minikube-ip.png)
<br> 
Video 7 - Installing kubctl
![](img/install-kubectl-1.png)
![](img/install-kubectl-2.png)
![](img/install-kubectl-3.png)
We need to make binary kubctl executable
![](img/install-kubectl-4.png)
![](img/install-kubectl-5.png)
And we need to move the kubctl binary to a file location in our system path
![](img/install-kubectl-6.png)
![](img/install-kubectl-7.png)
  Video 8 - Kubernetes video Hello World
<br>
![](img/kubectl-pod-command-creation.png)
![](img/kubectl-get-pods.png)
![](img/kubectl-forwarding-1.png)
- Let's open the browser
![](img/kubectl-forwarding-2.png)
now if we want to delete 
![](img/kubectl-delete-pods.png)
  Video 9 - Pods (Smallest unit)<br>
  Video 11 - Services - never use port-forward(Only testing)
          -instead use service . It has a stable ip address
  Video 12 - Service Discovery

# 1 - Intellij k8s Plugin
![](img/kubernetes-plugin.png)
# 2 - Deploying Postgres, Rabbit and Zipkin
- never deploy postgres on production inside a 
kubernetes cluster
# 3 - Postgres YAMLS
![](img/postgres-yamls.png)
# 4 Postgres Running in k8s
inside minikube folder
![](img/kubctl-apply-postgres.png)
![](img/kubctl-apply-postgres-2.png)
Let's investigate logs
![](img/postgres-logs.png)
We will create our databases (customer, fraud and notification) 
![](img/psql.png)
![](img/psql-inside-1.png)
![](img/creating-databases.png)
![](img/creating-databases-2.png)
Conclusion : we have created databases that our microservices will need, but never do it in production , 
only for testing;

# 5 Exercice
# 6 Exercice solution
Technically it's not the correct way to deploy them (rabbitmq an zipkin) to kubernetes
if we are running in production;
![](img/rabbit-zipkin-on-k8s.png)
![](img/kubectl-apply-zipkin.png)
![](img/kubectl-apply-zipkin-2.png)
![](img/kubectl-apply-rabbitmq-1.png)
![](img/kubectl-apply-rabbitmq-2.png)
![](img/get-pods.png)
Logs
kubectl logs rabbitmq-0
kubectl logs zipkin-0
kubectl get all
![](img/kubectl-get-all.png)
Now we will launch some services
- Rabbit MQ
![](img/minikube-service-url-rabbitmq.png)
Open browser and past in it http://127.0.0.1:56194 <br>
user - guest and password - guest 
- ![](img/rabbitmq-browser.png)
- Zipkin
![](img/minikube-service-url-zipkin.png)
 Open browser and past in it http://127.0.0.1:56194 <br>
![](img/zipkin-browser.png)
- Have a look - type is Loadbalancer and status is pending 
![](img/kubectl-get-services.png)
If we want to access our loadbalancer that we have within minikube, 
just type minikube tunnel
![](img/minikube-tunnel.png)
- That give me to access my service on the specified ports 9411
![](img/zipkin-not-pending.png)
![](img/zipkin-not-pending-2.png)