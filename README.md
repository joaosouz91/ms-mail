## Instructions

exec application:

    ./mvnw clean install package -DskipTests && docker-compose up --build

send email:

    1) Open postman
    2) Create a request as following
    
        @URL  -> 
            
            METHOD POST: http://localhost:8080
        
        @BODY ->
           
            {
                "ownerRef": "user_test",
                "from": "joaovictor.souz@gmail.com",
                "to": "",
                "subject": "Test MS MAIL",
                "text": "test, test, test,test test, testtesttesttest, test."
            }
    
    3) Don't forget to set your email in the field "to"

check logs:

    docker-compose logs -f ms-mail

stop application:
    
    docker-compose down --volumes
