package zero.programmer.data.kendaraan.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.models.ResponseData;
import zero.programmer.data.kendaraan.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * registrasi user
     * @param user
     * @param errors
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<User>> create(@Valid @RequestBody User user,
            Errors errors) {
        return createOrUpdate(user, errors, "Data berhasil ditambahkan");
    }

    /**
     * private function untuk membuat atau mengubah data user/ update profile
     * @param user
     * @param errors
     * @param messages
     * @return
     */
    private ResponseEntity<ResponseData<User>> createOrUpdate(User user, Errors errors,
            String messages) {
        ResponseData<User> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setCode(400);
            responseData.setStatus("BAD REQUEST");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // save ke service
        User userReturnFromService = userService.createUser(user);
        
        if (userReturnFromService == null){
            responseData.setCode(400);
            responseData.setStatus("BAD REQUEST");
            responseData.getMessages().add("Data dengan username telah tersedia");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        // agar tidak menampilkan password
        userReturnFromService.setPassword("This secret password hash");

        responseData.setCode(200);
        responseData.setStatus("OK");
        responseData.getMessages().add(messages);
        responseData.setData(userReturnFromService);
        return ResponseEntity.ok().body(responseData);
    }

}
