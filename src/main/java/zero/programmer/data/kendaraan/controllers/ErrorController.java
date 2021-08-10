package zero.programmer.data.kendaraan.controllers;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import zero.programmer.data.kendaraan.error.AuthorizationPassword;
import zero.programmer.data.kendaraan.error.DriverIsOnDutyException;
import zero.programmer.data.kendaraan.error.NotFoundException;
import zero.programmer.data.kendaraan.error.NullPointerException;
import zero.programmer.data.kendaraan.error.UnauthorizedException;
import zero.programmer.data.kendaraan.error.VehicleIsBorrowException;
import zero.programmer.data.kendaraan.models.ResponseData;

@RestControllerAdvice
public class ErrorController {
    
    /**
     * handler jika ada throw UnauthorizedException
     * @param unauthorizedException
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ResponseData<String>> unauthorized(UnauthorizedException unauthorizedException){
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
            new ResponseData<>(
                401,
                "UNAUTHORIZED",
                Arrays.asList("Please put your api key"),
                null
            )
        );
    }

    /**
     * handler jika ada throw NullPointerException
     * @param nullPointerException
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ResponseData<String>> nullPointerException(NullPointerException nullPointerException){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseData<String>(
                404,
                "NOT FOUND",
                Arrays.asList("Data tidak ditemukan"),
                null
            )
        );
    }

    @ExceptionHandler(value = AuthorizationPassword.class)
    public ResponseEntity<ResponseData<String>> authorizationPassword(AuthorizationPassword authorizationPassword){
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
            new ResponseData<>(
                403,
                "FORBIDDEN",
                Arrays.asList("Password yang anda masukan salah"),
                null
            )
        );
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseData<String>> userNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            new ResponseData<>(
                404,
                "NOT FOUND",
                Arrays.asList("Data tidak ditemukan"),
                null
            )
        );
    }

    @ExceptionHandler(value = VehicleIsBorrowException.class)
    public ResponseEntity<ResponseData<String>> vehicleIsBorrow(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseData<>(
                400,
                "BAD REQUEST",
                Arrays.asList("Kendaraan sedang dipinjam"),
                null
            )
        );
    }

    @ExceptionHandler(value = DriverIsOnDutyException.class)
    public ResponseEntity<ResponseData<String>> driverIsOnDuty(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ResponseData<>(
                400,
                "BAD REQUEST",
                Arrays.asList("Sopir sedang bertugas"),
                null
            )
        );
    }
}
