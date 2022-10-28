- Apache Maven Compiler Plugin
![](img/apache-maven-compiler-plugin.png)

Within pom.xml (the main pom) we have the build section
and within build section we have 'pluginManagement' and 'plugins'.
So by default all sub-modules will have this plugin 
![](img/main-pom-1.png)
So let's focus on Maven Compiler Plugin
Changing from 16-version to 17-version</br>
Also we specify the version
![](img/main-pom-2.png)

So open up 'amqp' module then open up within pom.xml and by default
like others sub-modules will have this 'maven-compiler-plugin'</br>
And we don't need  these properties -> 

![](img/amqp-pom.png)

What we need is to specify the packaging for every sub-module
![](img/amqp-pom-jar.png)
Let's do the same with other sub-modules  'clients'
![](img/clients-pom-1.png)
![](img/clients-pom-2.png)

So now if you want to generate the jar for these two modules, open the maven tab
![](img/clients-package-mvn.png)

Next we will take all of our microservices and package them using
the spring boot maven plugin


- Spring Boot Maven Plugin
![](img/spring-boot-maven-plugin.png)

In main pom.xml we know about this plugin which is included in every single module
![](img/maven-compiler-plugin.png)
We might be wondering if we package up the springboot application with this 
plugin then we might be able to run the application.So Spring Boot gives us the Spring Boot 
Maven Plugin 
![](img/spring-boot-maven-plugin-2.png)
Packaging Executable Archives
![](img/packaging-exe-archive-1.png)
So if we open the main pom, we can see we are defining the plugin ourselves.
What we would like to do is just to include the face 
![](img/pom-executions-repackage.png)
The goal is repackage : so basically after packaging the application using 
'maven-compiler-plugin' then it goes, and then it repackages so that it can basically run the Spring Boot
Application.Without this we won't be able to run the applications.
</br>
Let's open our microservices and let's start with api gateway microservice 'apigw'
![](img/api-gw-pom.png)
We do the same thing in each pom.xml file for every microservice

- Installing Root and Individual Modules with maven 

If we open up for example maven and try to compile customer , it gives me an error 
![](img/mvn-cusromer-compile-error.png)
The reason why this is not finding them, because we did not run yet maven install.
Explanation : if we go to the folder .m2/repository/com -> currently we don't see anything wit lalik-services 
![](img/terminal-m2-1.png)
<br>
![](img/terminal-m2-2.png)

This is why in IntelliJ , it says that customer cannot run because the amqp:jar and clients:jar  
are missing.

whatever you want to start a new project, basically you open the root project then lifecycle and 
we preform  clean first  and install.
![](img/mvn-root-clean.png)
</br>
![](img/mvn-root-install.png)
So now if we go back to the terminal we have in 'com' folder 'lalik' folder and inside we have our services.
![](img/terminal-m2-lalik-srvices-1.png)
![](img/terminal-m2-lalik-srvices-2.png)
Let see inside lalik/amqp and we have 1.0-SNAPSHOT
![](img/terminal-m2-lalik-srvices-3.png)

So whatever we run the 'install'  it actually also packaging into 'jar'
Where is this 'jar' ? 
![](img/packaged-modules.png)

Let's run our microservices.There is 2 ways to run our microservices via terminal . 
First is using the spring boot maven plugin
![](img/spring-boot-mavn-plug-run.png)

And the second is using  the traditional 'java -jar'
![](img/run-cmd-java-jar-eureka.png)
![](img/run-cmd-java-jar-eureka-2.png)
So like this we  can run all the microservices via terminal.