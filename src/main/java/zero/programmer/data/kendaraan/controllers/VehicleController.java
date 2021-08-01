package zero.programmer.data.kendaraan.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.models.ResponseData;
import zero.programmer.data.kendaraan.models.ResponseListVehicle;
import zero.programmer.data.kendaraan.models.VehicleData;
import zero.programmer.data.kendaraan.services.VehicleService;

@RestController
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping(path = "/api/vehicle", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<VehicleData>> create(@Valid @RequestBody VehicleData vehicleData,
            Errors errors) {
        return createOrUpdate(vehicleData, errors, "Data berhasil ditambahkan");
    }

    @GetMapping(path = "/api/vehicle/{registrationNumber}")
    public ResponseEntity<ResponseData<VehicleData>> getVehicle(
            @PathVariable("registrationNumber") String registrationNumber) {

        ResponseData<VehicleData> responseData = new ResponseData<>();

        VehicleData vehicleData = vehicleService.getVehicle(registrationNumber);
        if (vehicleData == null) {
            responseData.setCode(404);
            responseData.setStatus("NOT FOUND");
            responseData.getMessages().add("Data tidak ditemukan");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        responseData.setCode(200);
        responseData.setStatus("OK");
        responseData.setMessages(null);
        responseData.setData(vehicleData);
        return ResponseEntity.ok().body(responseData);
    }

    @PutMapping(path = "/api/vehicle", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<VehicleData>> update(@Valid @RequestBody VehicleData vehicleData,
            Errors errors) {
        return createOrUpdate(vehicleData, errors, "Data berhasil diubah");
    }

    @GetMapping(path = "/api/vehicle")
    public ResponseListVehicle<Vehicle> listVehicle(){
        ResponseListVehicle<Vehicle> responseListVehicle = new ResponseListVehicle<>();

        List<Vehicle> vehicles = vehicleService.listVehicle();
        if (vehicles.isEmpty()){
            responseListVehicle.setCode(200);
            responseListVehicle.setStatus("OK");
            responseListVehicle.getMessages().add("Data tidak ditemukan");
            responseListVehicle.setData(null);
            return responseListVehicle;
        }
        
        responseListVehicle.setCode(200);
        responseListVehicle.setStatus("OK");
        responseListVehicle.setMessages(null);
        responseListVehicle.setData(vehicles);
        return responseListVehicle;

    }

    private ResponseEntity<ResponseData<VehicleData>> createOrUpdate(VehicleData vehicleData, Errors errors,
            String messages) {
        ResponseData<VehicleData> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setCode(400);
            responseData.setStatus("BAD REQUEST");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setCode(200);
        responseData.setStatus("OK");
        responseData.getMessages().add(messages);
        responseData.setData(vehicleService.create(vehicleData));
        return ResponseEntity.ok().body(responseData);
    }

}
