# Autos.com
Java Spring Boot

#home
http://localhost:6916/

#get all cars
http://localhost:6916/api/car

#fetch by id
http://localhost:6916/api/car/102  


#create (Enum status [ AVAILABLE, SOLD, DELETED]
---load--
{
  "carName": "Lexus RX",
  "model": "2023",
  "description": "A refined and comfortable luxury SUV.",
  "year": "2023",
  "status": "AVAILABLE",
  "made": "Japan"
}

#update by Id (Enum status [ AVAILABLE, SOLD, DELETED]
---load--
{
  "carName": "Lexus RX",
  "model": "2024",
  "description": "A refined and comfortable luxury SUV.",
  "year": "2023",
  "status": "SOLD",
  "made": "Japan"
}


#Get by name
http://localhost:6916/api/car/name/honda civic

#Get car by it made
http://localhost:6916/api/car/made/USA

#Get by /made/name/year
http://localhost:6916/api/car/usa/Honda Civic/2021

#Delete by id
http://localhost:6916/api/car/105
