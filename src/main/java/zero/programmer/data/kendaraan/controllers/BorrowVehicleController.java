package zero.programmer.data.kendaraan.controllers;

import java.util.ArrayList;
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

import zero.programmer.data.kendaraan.entities.BorrowVehicle;
import zero.programmer.data.kendaraan.error.DriverIsOnDutyException;
import zero.programmer.data.kendaraan.error.NotFoundException;
import zero.programmer.data.kendaraan.error.VehicleIsBorrowException;
import zero.programmer.data.kendaraan.models.BorrowVehicleData;
import zero.programmer.data.kendaraan.services.BorrowVehicleService;
import zero.programmer.data.kendaraan.models.ResponseData;

@RestController
@RequestMapping("/api/borrow-vehicle")
public class BorrowVehicleController {
    
    @Autowired
    private BorrowVehicleService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<BorrowVehicle>> createBorrowVehicle(@Valid @RequestBody BorrowVehicleData borrowVehicleData, Errors errors) throws NotFoundException, VehicleIsBorrowException, DriverIsOnDutyException{

        List<String> messages = new ArrayList<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseData<>(
                    400,
                    "BAD REQUEST",
                    messages,
                    null
                )
            );
        }

        return ResponseEntity.ok().body(
            new ResponseData<BorrowVehicle>(
                200,
                "OK",
                null,
                service.createBorrowVehicle(borrowVehicleData)
            )
        );
    }

}
