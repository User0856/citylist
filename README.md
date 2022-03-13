# CityList

## To run the application

Simplest way to run the application is by using Docker. Docker must be installed, launched and terminal must have privileges to run docker commands.

in teminal, run commands:

`docker pull user0856/restclient-web`

`docker run -p 80:80 user0856/restclient-web`

in new terminal tab/window, run commands:

`docker pull user0856/cityist-backend-container`

`docker run -p 8080:8080 user0856/cityist-backend-container`

## Use the application

In browser, go to `localhost:80`

**Credentials**: 

login: `user`, password: `user` (can view and search)

login: `admin`, password: `admin` (can view, search and edit cities)

## About the project

**Backend:**

- Spring Boot
- Spring Web
- Spring Data JPA
- Spting Security
- H2 Database
- Gradle
- Junit/Mockito

**Frontend:**

- Angular
- Bootstrap


