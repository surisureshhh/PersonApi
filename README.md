# PersonApi
EMBL Test
  
steps to run the Project:

1. **clone the project from https://github.com/surisureshhh/PersonApi.git
2. import the project into IDE(eclipse) as maven project.
3. make sure you have JDK set on Java Build path in IDE(eclipse).
4. once building workspace is done, right click on the project, click on run and click on maven build (give goals as clean install).
5. once maven build is successfull, right click on project again, click on run and click on springbootapplication.

***Created a web front end that interacts with API:
you can open UI by using following link:

-> http://www.localhost:8080/index.html
you can perform add/edit/delete and get operations in the UI

***Implemented authentication method using spring security

***test functionality:
 you can test rest api in postman, ARC, soapUI tools
 
 post(create person record in the table): localhost:8080/api/v1/persons/
 payload sample:
             {
                    "id": 1,
                    "first_name": "suresh",
                    "last_name": "pasupuleti",
                    "age": "34",
                    "favourite_colour": "blue",
                    "hobby": [
                        "cricket,tennis"
                    ]
                }
    
    or 
          {
      "person": [
      {
      "first_name": "John",
      "last_name": "Smith",
      "age": "29",
      "favourite_colour": "red",
      "hobby": ["shopping","football"]
      },
      {
      "first_name": "Sarah",
      "last_name": "Connor",
      "age": "54",
      "favourite_colour": "blue",
      "hobby": ["chess"]
      }
      ]
      }

    put(update existing person record): localhost:8080/api/v1/persons/4

      sample payload :
      {
              "id": 1,
              "first_name": "suresh",
              "last_name": "pasupuleti",
              "age": "34",
              "favourite_colour": "blue",
              "hobby": [
                  "cricket,tennis"
              ]
          }
    
    get(get a single person record using person id): localhost:8080/api/v1/persons/4
    
    get(get all person records ): localhost:8080/api/v1/persons/
    
    delete(delete a single person record using person id): localhost:8080/api/v1/persons/4
    
    delete(delete all person records ): localhost:8080/api/v1/persons/
    
    
    
    **
    *****
  **Junits also covered part of the application
