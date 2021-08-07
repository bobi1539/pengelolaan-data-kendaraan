package zero.programmer.data.kendaraan.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
     * 
     * @param user
     * @param errors
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<User>> create(@Valid @RequestBody User user, Errors errors) {
        return createOrUpdate(user, errors, "Data berhasil ditambahkan");
    }

    @GetMapping("/{username}")
    public ResponseEntity<ResponseData<User>> getUser(@PathVariable("username") String username) {

        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    setResponseData(404, "NOT FOUND", Arrays.asList("Data dengan username tidak ditemukan"), null));
        }
        return ResponseEntity.ok().body(setResponseData(200, "OK", null, user));

    }

    @GetMapping("/login")
    public ResponseEntity<ResponseData<User>> tesUser(@RequestBody User user){
        User userData = userService.getUser(user.getUsername());
        if (userData == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                setResponseData(404, "NOT FOUND", Arrays.asList("Username tidak ditemukan"), null)
            );
        }
        if (passwordEncoder.matches(user.getPassword(), userData.getPassword())){
            return ResponseEntity.ok().body(
                setResponseData(200, "OK", Arrays.asList("Password sama"), userData)
            );
        }
        return ResponseEntity.ok().body(
                setResponseData(401, "UNAUTHORIZED", Arrays.asList("Password Tidak sama"), null)
            );
    }

    /**
     * private function untuk membuat atau mengubah data user/ update profile
     * 
     * @param user
     * @param errors
     * @param messages
     * @return
     */
    private ResponseEntity<ResponseData<User>> createOrUpdate(User user, Errors errors, String messages) {
        ResponseData<User> responseData = new ResponseData<>();

        List<String> messagesList = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                messagesList.add(error.getDefaultMessage());
            }
            responseData.setCode(400);
            responseData.setStatus("BAD REQUEST");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(setResponseData(400, "BAD REQUEST", messagesList, null));
        }
        // save ke service
        User userReturnFromService = userService.createUser(user);

        if (userReturnFromService == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    setResponseData(400, "BAD REQUEST", Arrays.asList("Data dengan username telah tersedia"), null));
        }
        // agar tidak menampilkan password hash
        userReturnFromService.setPassword("This secret password hash");

        return ResponseEntity.ok().body(setResponseData(200, "OK", Arrays.asList(messages), userReturnFromService));
    }

    /**
     * untuk set response data agar lebih singkat
     * 
     * @param code
     * @param status
     * @param messages
     * @param data
     * @return
     */
    private ResponseData<User> setResponseData(int code, String status, List<String> messages, User data) {
        ResponseData<User> responseData = new ResponseData<>();
        responseData.setCode(code);
        responseData.setStatus(status);
        responseData.setMessages(messages);
        responseData.setData(data);
        return responseData;
    }

}
