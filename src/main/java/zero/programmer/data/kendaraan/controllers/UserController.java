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
import zero.programmer.data.kendaraan.models.UserData;
import zero.programmer.data.kendaraan.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<UserData>> create(@Valid @RequestBody UserData userData,
            Errors errors) {
        return createOrUpdate(userData, errors, "Data berhasil ditambahkan");
    }

    private ResponseEntity<ResponseData<UserData>> createOrUpdate(UserData userData, Errors errors,
            String messages) {
        ResponseData<UserData> responseData = new ResponseData<>();
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
        responseData.setData(userService.createUser(userData));
        return ResponseEntity.ok().body(responseData);
    }

}
