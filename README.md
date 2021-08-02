# API Specification

## Create Vehicle

Request : 
- Method : POST
- Endpoint : '/api/vehicles'
- Header : 
    - Content-Type : application/json
    - Accept : application/json
- Body :
```json
{
	"registrationNumber" : "string",
	"name" : "string",
	"merk" : "string",
	"chassisNumber" : "string",
	"machineNumber" : "string",
	"policeNumber" : "string",
	"purchaseDate" : "date",
	"acquisitionValue" : "long",
	"location" : "string",
	"condition" : "string",
	"isBorrow" : "boolean"
}
```

Response : 
```json
{
    "code": "integer",
    "status": "string",
    "messages": [
        "string"
    ],
    "data": {
        "registrationNumber" : "string",
        "name" : "string",
        "merk" : "string",
        "chassisNumber" : "string",
        "machineNumber" : "string",
        "policeNumber" : "string",
        "purchaseDate" : "date",
        "acquisitionValue" : "long",
        "location" : "string",
        "condition" : "string",
        "isBorrow" : "boolean"
    }
}
```

## Get Vehicle

Request : 
- Method : GET
- Endpoint : '/api/vehicles/{registrationNumber}'
- Header :
    - Accept : application/json

Response :
```json
{
    "code": "integer",
    "status": "string",
    "messages": [
        "string"
    ],
    "data": {
        "registrationNumber" : "string",
        "name" : "string",
        "merk" : "string",
        "chassisNumber" : "string",
        "machineNumber" : "string",
        "policeNumber" : "string",
        "purchaseDate" : "date",
        "acquisitionValue" : "long",
        "location" : "string",
        "condition" : "string",
        "isBorrow" : "boolean"
    }
}
```

## Update Vehicle

Request : 
- Method : PUT
- Endpoint : '/api/vehicles'
- Header : 
    - Content-Type : application/json
    - Accept : application/json
- Body :
```json
{
	"registrationNumber" : "string",
	"name" : "string",
	"merk" : "string",
	"chassisNumber" : "string",
	"machineNumber" : "string",
	"policeNumber" : "string",
	"purchaseDate" : "date",
	"acquisitionValue" : "long",
	"location" : "string",
	"condition" : "string",
	"isBorrow" : "boolean"
}
```

Response : 
```json
{
    "code": "integer",
    "status": "string",
    "messages": [
        "string"
    ],
    "data": {
        "registrationNumber" : "string",
        "name" : "string",
        "merk" : "string",
        "chassisNumber" : "string",
        "machineNumber" : "string",
        "policeNumber" : "string",
        "purchaseDate" : "date",
        "acquisitionValue" : "long",
        "location" : "string",
        "condition" : "string",
        "isBorrow" : "boolean"
    }
}
```

## List Vehicle

Request :
- Method : GET
- Endpoint : '/api/vehicles'
- Header :
    - Accept : application/json

Response :
```json
{
    "code": "integer",
    "status": "string",
    "messages": [
        "string"
    ],
    "data": [
        {
            "registrationNumber" : "string",
            "name" : "string",
            "merk" : "string",
            "chassisNumber" : "string",
            "machineNumber" : "string",
            "policeNumber" : "string",
            "purchaseDate" : "date",
            "acquisitionValue" : "long",
            "location" : "string",
            "condition" : "string",
            "isBorrow" : "boolean"
        },
        {
            "registrationNumber" : "string",
            "name" : "string",
            "merk" : "string",
            "chassisNumber" : "string",
            "machineNumber" : "string",
            "policeNumber" : "string",
            "purchaseDate" : "date",
            "acquisitionValue" : "long",
            "location" : "string",
            "condition" : "string",
            "isBorrow" : "boolean"
        }
    ]
}
```

## Delete Vehicle

Request :
- Method : DELETE
- Endpoint : '/api/vehicles/{registrationNumber}
- Header :
    - Accept : application/json

Response :
```json
{
    "code": "integer",
    "status": "string",
    "messages": [
        "string"
    ],
    "data": null
}
```