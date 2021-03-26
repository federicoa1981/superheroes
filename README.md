#Introduction
This is a rest api to allow Superheroes crud and to filtering by name.
It was implemented using spring-boot 2.x
Features:
- Query All superheroes.
- Query superhero by id.
- Query superheroes filtering by a part of name . Example : If we send man, then return : Spiderman, Superman, Wonderwoman
- Add superhero
- Modify superhero
- Remove superhero

It include integration tests.
The superheroes are persisted in memory database (h2) . 
You can read swagger for documentation.

## Compile 
mvn clean package

##Run
mvn spring-boot:run

#Swagger Documentation
http://localhost:8080/swagger-ui.html

