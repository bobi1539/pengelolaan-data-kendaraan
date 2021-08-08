package zero.programmer.data.kendaraan.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zero.programmer.data.kendaraan.entities.Driver;
import zero.programmer.data.kendaraan.models.ResponseData;
import zero.programmer.data.kendaraan.services.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    
    @Autowired
    private DriverService driverService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<Driver>> createDriver(@Valid @RequestBody Driver driver, Errors errors){
        return createOrUpdate(
            driverService.createDriver(driver),
            errors,
            "Data dengan id driver : " + driver.getIdDriver() + " telah tersedia",
            "Data berhasil ditambahkan"
        );
    }

    private ResponseEntity<ResponseData<Driver>> createOrUpdate(
        Driver driver,
        Errors errors,
        String message1,
        String message2
    ){
        List<String> messageList = new ArrayList<>();
        if (errors.hasErrors()){
            for(ObjectError error : errors.getAllErrors()){
                messageList.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseData<Driver>(
                    400,
                    "BAD REQUEST",
                    messageList,
                    null
                )
            );
        }

        if (driver == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseData<Driver>(
                    404,
                    "NOT FOUND",
                    Arrays.asList(message1),
                    null
                )  
            );
        }
        return ResponseEntity.ok().body(
            new ResponseData<Driver>(
                200,
                "OK",
                Arrays.asList(message2),
                driver
            )
        );
    }

}
