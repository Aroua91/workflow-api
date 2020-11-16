# WORKFLOW Management RESTful API

RESTful API for workflow management

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

you need to install:

```
-Maven
-MySQL database
-download MySQL driver and add it to under /jre/lib/ext
-Jdk 1.8

```

### Project installation

To launch the application you need to update configuration

Under workflow-api/workflow-ws/application.properties update the configuration:

```
point to your database
choose the server port
set logo files repository path on 'ncq.logo.repo.path'
set workflows page size on 'ncq.page.size'

```
Initialize your database:

```
Create the database then

Execute the script:

  db/create.sql
Or let Spring boot create database structure (spring.jpa.hibernate.ddl-auto is configured to 'update' it can be modified to 'create')
```

Then build the project

```
mvn clean install

```
Copy application.properties file near the war on workflow-api/workflow-ws/target
Under the directory workflow-api/workflow-ws/target launch

```
java -jar workflow-ws-1.0-SNAPSHOT.war

```
## Authors

* **Aroua SOUABNI** 

