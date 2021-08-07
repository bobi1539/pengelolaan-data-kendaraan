# API Specification

## Authentication

All api must use this authentication

Request :
- Header :
    X-Api-Key : "your secret api key"

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
    "messages": null,
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
    "messages": null,
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

## Create User

Request : 
- Method : POST
- Endpoint : '/api/users'
- Header : 
    - Content-Type : application/json
    - Accept : application/json
- Body :
```json
{
	"username" : "string",
	"password" : "string",
	"fullName" : "string",
	"employeeNumber" : "string",
	"position" : "string",
	"workUnit" : "string",
	"roleId" : "enum (ADMIN, USER, KARYAWAN)"
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
        "username" : "string",
        "password" : "string",
        "fullName" : "string",
        "employeeNumber" : "string",
        "position" : "string",
        "workUnit" : "string",
        "roleId" : "enum (ADMIN, USER, KARYAWAN)"
    }
}
```

## Get User

Request : 
- Method : GET
- Endpoint : '/api/users/{username}'
- Header :
    - Accept : application/json

Response :
```json
{
    "code": "integer",
    "status": "string",
    "messages": null,
    "data": {
        "username" : "string",
        "password" : "string",
        "fullName" : "string",
        "employeeNumber" : "string",
        "position" : "string",
        "workUnit" : "string",
        "roleId" : "enum (ADMIN, USER, KARYAWAN)"
    }
}
```

## Update User

Request : 
- Method : PUT
- Endpoint : '/api/users'
- Header : 
    - Content-Type : application/json
    - Accept : application/json
- Body :
```json
{
	"username" : "string",
    "password" : "string",
    "fullName" : "string",
    "employeeNumber" : "string",
    "position" : "string",
    "workUnit" : "string",
    "roleId" : "enum (ADMIN, USER, KARYAWAN)"
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
        "username" : "string",
        "password" : "string",
        "fullName" : "string",
        "employeeNumber" : "string",
        "position" : "string",
        "workUnit" : "string",
        "roleId" : "enum (ADMIN, USER, KARYAWAN)"
    }
}
```

## List User

Request :
- Method : GET
- Endpoint : '/api/users'
- Header :
    - Accept : application/json

Response :
```json
{
    "code": "integer",
    "status": "string",
    "messages": null,
    "data": [
        {
            "username" : "string",
            "password" : "string",
            "fullName" : "string",
            "employeeNumber" : "string",
            "position" : "string",
            "workUnit" : "string",
            "roleId" : "enum (ADMIN, USER, KARYAWAN)"
        },
        {
            "username" : "string",
            "password" : "string",
            "fullName" : "string",
            "employeeNumber" : "string",
            "position" : "string",
            "workUnit" : "string",
            "roleId" : "enum (ADMIN, USER, KARYAWAN)"
        }
    ]
}
```

## Delete User

Request :
- Method : DELETE
- Endpoint : '/api/users/{username}
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