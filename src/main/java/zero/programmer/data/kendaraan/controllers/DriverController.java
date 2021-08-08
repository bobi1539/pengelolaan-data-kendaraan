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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zero.programmer.data.kendaraan.entities.Driver;
import zero.programmer.data.kendaraan.models.ResponseData;
import zero.programmer.data.kendaraan.models.ResponseDataList;
import zero.programmer.data.kendaraan.services.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    
    @Autowired
    private DriverService driverService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<Driver>> createDriver(@Valid @RequestBody Driver driver, Errors errors){

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

        return createOrUpdate(
            driverService.createDriver(driver),
            errors,
            "Data dengan id driver : " + driver.getIdDriver() + " telah tersedia",
            "Data berhasil ditambahkan"
        );
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<Driver>> updateDriver(@Valid @RequestBody Driver driver, Errors errors){
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

        return createOrUpdate(
            driverService.updateDriver(driver), 
            errors, 
            "Data dengan id driver : " + driver.getIdDriver() + " tidak ditemukan", 
            "Data berhasil di ubah"
        );
    }

    @GetMapping("/{idDriver}")
    public ResponseEntity<ResponseData<Driver>> getDriver(@PathVariable("idDriver") String idDriver){

        Driver driver = driverService.getDriver(idDriver);
        if (driver == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseData<Driver>(
                    404,
                    "NOT FOUND",
                    Arrays.asList("Data dengan id driver : " + idDriver + " tidak ditemukan"),
                    null
                )
            );
        }

        return ResponseEntity.ok().body(
            new ResponseData<Driver>(
                200,
                "OK",
                null,
                driver
            )
        );

    }

    @GetMapping
    public ResponseEntity<ResponseDataList<Driver>> listDriver(){
        ResponseDataList<Driver> responseDataList = new ResponseDataList<>();
        List<Driver> drivers = driverService.listDriver();
        if (drivers == null){
            responseDataList.setCode(404);
            responseDataList.setStatus("NOT FOUND");
            responseDataList.getMessages().add("Data tidak ditemukan");
            responseDataList.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDataList);
        }
        responseDataList.setCode(200);
        responseDataList.setStatus("OK");
        responseDataList.setMessages(null);
        responseDataList.setData(drivers);
        return ResponseEntity.ok().body(responseDataList);
    }

    @DeleteMapping("{idDriver}")
    public ResponseEntity<ResponseData<Driver>> deleteDriver(@PathVariable("idDriver") String idDriver){
        String message = driverService.deleteDriver(idDriver);
        if (message == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseData<>(
                    404,
                    "NOT FOUND",
                    Arrays.asList("Data dengan id driver : " + idDriver + " tidak ditemukan"),
                    null
                )
            );
        }
        return ResponseEntity.ok().body(
            new ResponseData<Driver>(
                200,
                "OK",
                Arrays.asList(message),
                null
            )
        );
    }

    private ResponseEntity<ResponseData<Driver>> createOrUpdate(
        Driver driver,
        Errors errors,
        String message1,
        String message2
    ){
        if (driver == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseData<Driver>(
                    400,
                    "BAD REQUEST",
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
