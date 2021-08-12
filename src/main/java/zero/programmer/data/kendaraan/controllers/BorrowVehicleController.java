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
import zero.programmer.data.kendaraan.models.ResponseDataList;

@RestController
@RequestMapping("/api/borrow-vehicle")
public class BorrowVehicleController {

    @Autowired
    private BorrowVehicleService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<BorrowVehicle>> createBorrowVehicle(
            @Valid @RequestBody BorrowVehicleData borrowVehicleData, Errors errors)
            throws NotFoundException, VehicleIsBorrowException, DriverIsOnDutyException {

        List<String> messages = new ArrayList<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                messages.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseData<>(400, "BAD REQUEST", messages, null));
        }

        return ResponseEntity.ok()
                .body(new ResponseData<BorrowVehicle>(200, "OK", Arrays.asList("Data berhasil ditambahkan"), service.createBorrowVehicle(borrowVehicleData)));
    }

    @GetMapping()
    public ResponseEntity<ResponseDataList<BorrowVehicle>> listBorrowVehicle() throws NotFoundException {

        ResponseDataList<BorrowVehicle> responseDataList = new ResponseDataList<>();
        responseDataList.setCode(200);
        responseDataList.setStatus("OK");
        responseDataList.setMessages(null);
        responseDataList.setData(service.listBorrowVehicle());
        return ResponseEntity.ok().body(responseDataList);
    }

    @GetMapping("/dinas")
    public ResponseEntity<ResponseDataList<BorrowVehicle>> listBorrowVehicleForDinas() throws NotFoundException{
        return ResponseEntity.ok().body(new ResponseDataList<BorrowVehicle>(
            200, "OK", null, service.listBorrowVehicleForDinas()
        ));
    }

    @GetMapping("/dinas/{username}")
    public ResponseEntity<ResponseDataList<BorrowVehicle>> listBorrowVehicleForDinasByUsername(@PathVariable("username") String username) throws NotFoundException{
        return ResponseEntity.ok().body(new ResponseDataList<BorrowVehicle>(
            200, "OK", null, service.listBorrowVehicleForDinasByUsername(username)
        ));
    }

    @GetMapping("/personal")
    public ResponseEntity<ResponseDataList<BorrowVehicle>> listBorrowVehicleForPersonal() throws NotFoundException{
        return ResponseEntity.ok().body(new ResponseDataList<BorrowVehicle>(
            200, "OK", null, service.listBorrowVehicleForPersonal()
        ));
    }

    @GetMapping("/personal/{username}")
    public ResponseEntity<ResponseDataList<BorrowVehicle>> listBorrowVehicleForPersonalByUsername(@PathVariable("username") String username) throws NotFoundException{
        return ResponseEntity.ok().body(new ResponseDataList<BorrowVehicle>(
            200, "OK", null, service.listBorrowVehicleForPersonalByUsername(username)
        ));
    }

    @DeleteMapping("/{idBorrow}")
    public ResponseEntity<ResponseData<String>> deleteBorrowVehicle(@PathVariable("idBorrow" )Integer idBorrow) throws NotFoundException{
        String message = service.deleteBorrowVehicle(idBorrow);
        if (message == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseData<>(400, "BAD REQUEST", Arrays.asList("Data tidak bisa dihapus karena kendaraan masih belum dikembalikan"), null)
            );
        }
        return ResponseEntity.ok().body(
            new ResponseData<>(200, "OK", Arrays.asList(message), null)
        );
    }

}
