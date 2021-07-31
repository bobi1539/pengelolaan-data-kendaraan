package zero.programmer.data.kendaraan.controllers;

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

import zero.programmer.data.kendaraan.models.ResponseData;
import zero.programmer.data.kendaraan.models.VehicleData;
import zero.programmer.data.kendaraan.services.VehicleService;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;

    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseData<VehicleData>> create(@Valid @RequestBody VehicleData vehicleData, Errors errors){
        ResponseData<VehicleData> responseData = new ResponseData<>();
        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()){
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setCode(400);
            responseData.setStatus("BAD REQUEST");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setCode(200);
        responseData.setStatus("OK");
        responseData.getMessages().add("Data berhasil ditambahkan");
        responseData.setData(vehicleService.create(vehicleData));
        return ResponseEntity.ok().body(responseData);
    }

}
