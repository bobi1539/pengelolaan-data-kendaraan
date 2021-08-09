package zero.programmer.data.kendaraan.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.error.NullPointerException;
import zero.programmer.data.kendaraan.models.ResponseData;
import zero.programmer.data.kendaraan.models.ResponseDataList;
import zero.programmer.data.kendaraan.models.VehicleData;
import zero.programmer.data.kendaraan.services.VehicleService;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * Insert data vehicle
     * 
     * @param vehicleData
     * @param errors
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<VehicleData>> create(@Valid @RequestBody VehicleData vehicleData,
            Errors errors) {
        return createOrUpdate(vehicleService.create(vehicleData), errors,
                "Data dengan no registrasi : " + vehicleData.getRegistrationNumber() + " telah tersedia",
                "Data berhasil ditambahkan");
    }

    /**
     * Melihat detail kendaraan
     * 
     * @param registrationNumber
     * @return
     * @throws NullPointerException
     */
    @GetMapping("/{registrationNumber}")
    public ResponseEntity<ResponseData<VehicleData>> getVehicle(
            @PathVariable("registrationNumber") String registrationNumber) throws NullPointerException {
        return ResponseEntity.ok().body(new ResponseData<VehicleData>(200, "OK", null, vehicleService.getVehicle(registrationNumber)));
    }

    /**
     * Update data kendaraan dengan method PUT
     * 
     * @param vehicleData
     * @param errors
     * @return
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<VehicleData>> update(@Valid @RequestBody VehicleData vehicleData,
            Errors errors) {
        return createOrUpdate(vehicleService.updateVehicle(vehicleData), errors,
                "Data dengan no registrasi : " + vehicleData.getRegistrationNumber() + " tidak ditemukan",
                "Data berhasil diubah");
    }

    /**
     * update secara partial/tidak semua field yang di update dengan method PATCH
     * 
     * @param registrationNumber
     * @param fields
     * @return
     */
    @PatchMapping("/{registrationNumber}")
    public ResponseEntity<ResponseData<Vehicle>> patchUpdate(
            @PathVariable("registrationNumber") String registrationNumber, @RequestBody Map<Object, Object> fields) {
        ResponseData<Vehicle> responseData = new ResponseData<>();
        Vehicle vehicleData = vehicleService.updatePartial(registrationNumber, fields);
        if (vehicleData == null) {
            responseData.setCode(404);
            responseData.setStatus("NOT FOUND");
            responseData.getMessages().add("Data dengan no registrasi tersebut tidak ditemukan");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        responseData.setCode(200);
        responseData.setStatus("OK");
        responseData.getMessages().add("Data berhasil di update");
        responseData.setData(vehicleData);
        return ResponseEntity.ok().body(responseData);
    }

    /**
     * get list data kendaraan
     * 
     * @return
     * @throws NullPointerException
     */
    @GetMapping()
    public ResponseEntity<ResponseDataList<Vehicle>> listVehicle() throws NullPointerException {
        ResponseDataList<Vehicle> responseListVehicle = new ResponseDataList<>();

        responseListVehicle.setCode(200);
        responseListVehicle.setStatus("OK");
        responseListVehicle.setMessages(null);
        responseListVehicle.setData(vehicleService.listVehicle());
        return ResponseEntity.ok().body(responseListVehicle);

    }

    /**
     * menghapus data kendaraan
     * 
     * @param registrationNumber
     * @return
     */
    @DeleteMapping("/{registrationNumber}")
    public ResponseEntity<ResponseData<String>> delete(@PathVariable("registrationNumber") String registrationNumber) {
        ResponseData<String> responseData = new ResponseData<>();
        String removeVehicleById = vehicleService.remove(registrationNumber);
        if (removeVehicleById == null) {
            responseData.setCode(404);
            responseData.setStatus("NOT FOUND");
            responseData.getMessages().add("Data gagal dihapus");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        responseData.setCode(200);
        responseData.setStatus("OK");
        responseData.getMessages().add(removeVehicleById);
        responseData.setData(null);
        return ResponseEntity.ok().body(responseData);

    }

    /**
     * function untuk create or update
     * 
     * @param vehicleData
     * @param errors
     * @param messageNull
     * @param messageOk
     * @return
     */
    private ResponseEntity<ResponseData<VehicleData>> createOrUpdate(VehicleData vehicleData, Errors errors,
            String messageNull, String messageOk) {
        List<String> listMessages = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                listMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData<>(400, "BAD REQUEST", listMessages, null));
        }

        if (vehicleData == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData<>(400, "BAD REQUEST", Arrays.asList(messageNull), null));
        }
        return ResponseEntity.ok()
                .body(new ResponseData<VehicleData>(200, "OK", Arrays.asList(messageOk), vehicleData));
    }

}
