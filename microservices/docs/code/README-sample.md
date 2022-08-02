## PROJECT  agile-app

## REPO     https://github.com/gituser/project

- GIT gituser

---

# Agile app

`agile management tool`

# Run

- create mysql database: agile-app
- use the run configuration: AgileApp
- open http://localhost:8081/ in browser

# Test

run the unit and integration tests with:
```mvn -T 4 clean test```

---

# Plan

task management

documentation

- story mapping: /story-mapping.md
- backend tasks: /task-backend.md
- class diagram: /project mgm.drawio

---

# Infrastructure

## Technology stack

- software
  - intellij - IDE
  - git - versioning system
  - github - code repository
  - java skd 11 - development kit
  - mysql - database
  - mysql workbench - database interface

- backend
  - java - programming language
  - spring - dependency injection framework
  - spring data jpa - orm framework
  - spring security - security framework
  - junit 5 - testing framework
  - maven - build tool

- frontend
  - thymeleaf - templating engine
  - HTML - markup language
  - bootstrap - css framework
  - CSS - styling language
  - JS - web language

## Architecture

- html template
- controller (dto)
- service (dto)
- repository (entity)
- model

## Database

Data is stored in MySql v8 ecoded with UTF8MB4.

database model - [database-model.drawio]

## Version Control System

versioned the code in a local repository using Git

## Git conventions

conventions are defined at [conventions-git.md]

---

# Backend

## REST endpoints

Swagger 2 is used to document the endpoints. The swagger ui is located at:

http://localhost:8081/swagger-ui/#/

All rest controllers follow the API convention:

    POST    /{collection} (json body)
    GET     /{collection}
    GET     /{collection}/{id}
    PUT     /{collection}/{id} (json body)
    DELETE  /{collection}/{id}

## Embedded webserver

Spring is using Tomcat to serve the web application.

## Logging

Logback is configured at:

    /src/main/resources/logback.xml

There are two appenders defined:

- console appender
- file appender

The log files are located at:

    /logs/app.log

## Custom banner

You can change the custom banner by editing the file:

    /src/main/resources/banner.txt
