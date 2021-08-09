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

import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.models.LoginData;
import zero.programmer.data.kendaraan.models.ResponseData;
import zero.programmer.data.kendaraan.models.ResponseDataList;
import zero.programmer.data.kendaraan.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * registrasi user
     * 
     * @param user
     * @param errors
     * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<User>> create(@Valid @RequestBody User user, Errors errors) {

        List<String> messagesList = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                messagesList.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(setResponseData(400, "BAD REQUEST", messagesList, null));
        }

        return createOrUpdate(
            userService.createUser(user),
            errors, 
            "Data dengan username tersebut telah tersedia",
            "Data berhasil di simpan"
        );
    }

    /**
     * update user dengan method PUT
     * @param user
     * @param errors
     * @return
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<User>> update(@Valid @RequestBody User user, Errors errors){
        
        List<String> messagesList = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                messagesList.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(setResponseData(400, "BAD REQUEST", messagesList, null));
        }

        return createOrUpdate(
            userService.updateUser(user),
            errors,
            "Data dengan username tidak ditemukan",
            "Data berhasil diubah"
            );
    }

    /**
     * update user dengan partial/tidak semua field di update/ sebagian field di update
     * @param username
     * @param fields
     * @return
     */
    @PatchMapping(path = "/{username}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<User>> updatePartial(@PathVariable("username") String username, @RequestBody Map<Object, Object> fields){
        User userData = userService.updatePartial(username, fields);
        if (userData == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                setResponseData(404, "NOT FOUND", Arrays.asList("Data dengan username : " + username + " tidak ditemukan"), null)
            );
        }
        return ResponseEntity.ok().body(
            setResponseData(200, "OK", Arrays.asList("Data berhasil di update"), userData)
        );
    }

    /**
     * ambil data user berdasarkan username
     * @param username
     * @return
     */
    @GetMapping("/{username}")
    public ResponseEntity<ResponseData<User>> getUser(@PathVariable("username") String username) {

        User user = userService.getUser(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    setResponseData(404, "NOT FOUND", Arrays.asList("Data dengan username tidak ditemukan"), null));
        }
        return ResponseEntity.ok().body(setResponseData(200, "OK", null, user));

    }

    /**
     * get list data user
     * @return
     */
    @GetMapping()
    public ResponseEntity<ResponseDataList<User>> listUser(){
        ResponseDataList<User> responseDataList = new ResponseDataList<>();

        List<User> listUser = userService.listUser();
        if (listUser.isEmpty()){
            responseDataList.setCode(404);
            responseDataList.setStatus("NOT FOUND");
            responseDataList.getMessages().add("Data tidak ditemukan");
            responseDataList.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDataList);
        }
        responseDataList.setCode(200);
        responseDataList.setStatus("OK");
        responseDataList.setMessages(null);
        responseDataList.setData(listUser);
        return ResponseEntity.ok().body(responseDataList);

    }

    @DeleteMapping("/{username}")
    public ResponseEntity<ResponseData<String>> deleteUser(@PathVariable("username") String username){
        ResponseData<String> responseData = new ResponseData<>();
        String message = userService.removeUser(username);
        if (message == null){
            responseData.setCode(404);
            responseData.setStatus("NOT FOUND");
            responseData.getMessages().add("Data dengan username tidak tersedia");
            responseData.setData(null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseData);
        }
        responseData.setCode(200);
        responseData.setStatus("OK");
        responseData.getMessages().add(message);
        responseData.setData(null);
        return ResponseEntity.ok().body(responseData);
    }

    

    /**
     * function teting login untuk user
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping(path = "/login",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseData<User>> loginUser(@Valid @RequestBody LoginData loginData, Errors errors) throws Exception{

        List<String> messagesList = new ArrayList<>();
        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                messagesList.add(error.getDefaultMessage());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(setResponseData(400, "BAD REQUEST", messagesList, null));
        }

        User user = userService.checkLoginUser(loginData);
        return ResponseEntity.ok().body(
            new ResponseData<User>(
                200,
                "OK",
                Arrays.asList("Login berhasil"),
                user
            )
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
    private ResponseEntity<ResponseData<User>> createOrUpdate(
        User user, 
        Errors errors, 
        String messages1,
        String messages2) {

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    setResponseData(400, "BAD REQUEST", Arrays.asList(messages1), null));
        }
        // agar tidak menampilkan password hash
        user.setPassword("This secret password hash");

        return ResponseEntity.ok().body(setResponseData(200, "OK", Arrays.asList(messages2), user));
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
