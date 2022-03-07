# Movie API Project 

## Overview 
This repository contains the implementation of Movie API. 
The main aim of this application is to allow users to get details of movies based on the inputted criteria such as title, 
description, rating, release date, Genre etc. 

## Design
The UML diagrams for the application is in the UML folder which gives an overview of the classes and screens implemented. 
The application is designed using below technologies:
1. Front end UI is developed using React js 
2. Middleware API creation is done using Java,SpringBoot and Spring Data JPA 
3. Postgres Database has been used for storing the data

## Implementation

The Movie API is designed in such a way that a user who want to get details of any movies from the database can register using 
the registration page. The registered user can login to the application using their emailid and password and send an 
authentication request to get an authorization token which is valid for a specific amount of time. The generated token can be
used to send requests for any of the API data that the user want to access.

The application has following modules: 
1. User 
   This module provides API related to  user operations such as User registration and authentication based on his emailid and password. The user should
   be able to register and login to application with the help of user endpoints. User should also be able to update his details like
   email id and password if required. 
2. Movie 
   This module provides API related to Movies. A registered user should be able to access the movies API and get data based on
   few attributes such as movie title, description, release date, rating, genre, original language,poster,adult,runtime.A User can 
   get movie data based on a single attribute or multiple attributes by using the movie endpoints.
3. Admin
   This module provides API related to admin operations which includes both managing users and movies. An admin should be able to add 
   a new user, update the role of a user, update user details and delete user details. Admin should also be able to add a new movie, 
   update the details of existing movie and delete a movie with the help of admin endpoints.

The source of movie database is the external API called TMDB API which has been consumed to get the data into movie database. 
An external API called Twilio API is used to send an sms notification to the registered user when he requests for some data in 
the application. 

The details of how to access these end points is provided at the url below: 
http://localhost:8080/swagger-ui/index.html

## Instructions to run 


## Future Improvements 


