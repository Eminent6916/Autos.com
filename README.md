# Autos.com
## Java Spring Boot
--port 6916
#home
:6916/

#get all cars - GET
:6916/api/car

#fetch by id - GET
:6916/api/car/:ID 


#create (Enum status [ AVAILABLE, SOLD, DELETED] - POST
:6916/api/car
___body___
{
  "carName": "Lexus RX",
  "model": "2023",
  "description": "A refined and comfortable luxury SUV.",
  "year": "2023",
  "status": "AVAILABLE",
  "made": "Japan"
}

#update by Id (Enum status [ AVAILABLE, SOLD, DELETED] - PUT
:6916/api/car/:ID
___body___
{
  "carName": "Lexus RX",
  "model": "2024",
  "description": "A refined and comfortable luxury SUV.",
  "year": "2023",
  "status": "SOLD",
  "made": "Japan"
}


#Get by name
:6916/api/car/name/:name

#Get car by it made
:6916/api/car/made/:made

#Get by made, name and year
:6916/api/car/:made/:name/:year

#Delete by id - DELETE
:6916/api/car/:ID
