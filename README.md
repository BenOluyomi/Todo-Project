# To-do Project
This project is about the creation of a To do list program specifically around task management. 
This project works through interacting with a webpage to manipulate data within a SQL database inside of a local instance. Within the database there are the following tables;

- tasks
- subjects

Subjects and tasks can be created, and tasks can be assigned to subjects, it aims to achieve fool CRUD functionality. Create, Read, Update and Delete for both tables.
## Getting Started

###How to use this program

The Home/About tab on the webpage has instructions on how to use the program once up and running.


To run the program, you must use a version of java 14 or higher to run the .jar file in the repository. To execute this .jar the following command is required.

```
'java -jar nameoffile.jar' and the program should begin if the gcp instance is up.
```


###Rebuild the jar
Maven installation
https://maven.apache.org/install.html

https://maven.apache.org/download.cgi?Preferred=ftp://ftp.osuosl.org/pub/apache/
The links above contain instructions on how to install maven followed by the download link itself.

In order to rebuild the jar files to run, the following cammands need to be input into the a cli where your pom.xml files is located.

```
mvn clean 
```
```
mvn package
```
this should provide you with the jar files in your directory.

###Testing

This code can be tested using sonarQube, Selenium or JUnit.

## Authors
* **Benjamin Oluyomi** 