package zero.programmer.data.kendaraan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import zero.programmer.data.kendaraan.error.UnauthorizedException;
import zero.programmer.data.kendaraan.models.ResponseData;

@RestControllerAdvice
public class ErrorController {
    
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ResponseData<String>> unauthorized(UnauthorizedException unauthorizedException){
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setCode(401);
        responseData.setStatus("UNAUTHORIZED");
        responseData.getMessages().add("Please put your api key");
        responseData.setData(null);
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseData);
    }

}
