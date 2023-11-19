# TConnect API
API for TConnect app
## Install JDK17
On debian/ubuntu type
```
sudo apt update
sudo apt upgrade
sudo apt install openjdk-17-jdk openjdk-17-jre
```
## Change Database data
In application.properties you can change db, user, password etc.
<pre>
|-- src
  |-- main
    |-- resources
      |-- application.properties
</pre>
## Run API
Go into project in terminal, then type
```
./mvnw spring-boot:run
```
