# CityList

## To run the application

Simplest way to run the application is by using Docker. Docker must be installed, launched and terminal must have privileges to run docker commands.

in teminal, run commands:

`docker pull user0856/citylist-frontend-container`

`docker run -p 80:80 user0856/citylist-frontend-container`

in new terminal tab/window, run commands:

`docker pull user0856/citylist-backend-container`

`docker run -p 8080:8080 user0856/citylist-backend-container`

## Use the application

In browser, go to `localhost:80`

**Credentials**: 

login: `user`, password: `user` (can view and search)

login: `admin`, password: `admin` (can view, search and edit cities)

**Search bar is at the bottom of the page**

<img width="641" alt="image" src="https://user-images.githubusercontent.com/42377378/158124649-1f07dee3-f675-4328-a904-00b442c283b6.png">

**Use pagination buttons to return home after search**

(**Home** button to be dded)

<img width="633" alt="image" src="https://user-images.githubusercontent.com/42377378/158124902-0d705df7-710c-40b7-b93c-5d289e291e86.png">

**Edit info**

Modal dialog to be replaced with a separate form with separate inputs

For now you are afered an input field filled with current data so it can easily be edited

<img width="443" alt="image" src="https://user-images.githubusercontent.com/42377378/158125391-b4c589b6-a467-43ec-8e3e-0d8a1879447a.png">

## About the project

**Further improvments might include:**

- api versioning
- imporved backend data validation
- additional model data layer between service and repository layers
- end-to-end tests
- jwt tokens or side auth services implementation

## Technologies used

**Backend:**

- Spring Boot
- Spring Web
- Spring Data JPA
- Spting Security
- H2 Database
- Gradle
- Junit 5 / Mockito

**Frontend:**

- Angular
- Bootstrap
