package zero.programmer.data.kendaraan.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zero.programmer.data.kendaraan.entities.BorrowVehicle;
import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.entities.Driver;
import zero.programmer.data.kendaraan.error.DriverIsOnDutyException;
import zero.programmer.data.kendaraan.error.NotFoundException;
import zero.programmer.data.kendaraan.error.NullPointerException;
import zero.programmer.data.kendaraan.error.VehicleIsBorrowException;
import zero.programmer.data.kendaraan.models.BorrowVehicleData;
import zero.programmer.data.kendaraan.models.VehicleData;
import zero.programmer.data.kendaraan.repositories.BorrowVehicleRepository;
import zero.programmer.data.kendaraan.services.BorrowVehicleService;
import zero.programmer.data.kendaraan.services.DriverService;
import zero.programmer.data.kendaraan.services.UserService;
import zero.programmer.data.kendaraan.services.VehicleService;

@Service
public class BorrowVehicleServiceImpl implements BorrowVehicleService{

    @Autowired
    private BorrowVehicleRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private VehicleService vehicleService;

    private final String INVISIBLE = "invisible";

    @Override
    public BorrowVehicle createBorrowVehicle(BorrowVehicleData borrowVehicleData) throws NotFoundException, VehicleIsBorrowException, DriverIsOnDutyException {

        boolean allIsValid = true;
        
        // pindahkan data dari borrowVehicleData ke borrowVehicle
        BorrowVehicle borrowVehicle = modelMapper.map(borrowVehicleData, BorrowVehicle.class);

        // --------- cek bagian user ---------
        User user = userService.getUser(borrowVehicle.getUser().getUsername());

        // cek apakah user ada atau tidak
        if (user == null){
            allIsValid = false;
            throw new NotFoundException();
        }
        // --------- end cek bagian user ---------

        // --------- cek bagian kendaraan ---------
        VehicleData vehicleData = new VehicleData();

        // cek apakah kendaraan ada atau tidak
        try{
            vehicleData  = vehicleService.getVehicle(borrowVehicle.getVehicle().getRegistrationNumber());
        } catch (NullPointerException e){
            allIsValid = false;
            throw new NotFoundException();
        }

        // cek apakah kendaraan dipinjam atau tidak
        if (vehicleData.getIsBorrow()){
            allIsValid = false;
            throw new VehicleIsBorrowException();
        }
        // --------- end cek bagian kendaraan ---------

        // --------- cek bagian driver ---------
        Driver driver = driverService.getDriver(borrowVehicle.getDriver().getIdDriver());
        // cek driver ada atau tidak
        if (driver == null){
            allIsValid = false;
            throw new NotFoundException();
        }

        // cek driver sedang bertugas atau tidak
        if (driver.getIsOnDuty()){
            allIsValid = false;
            throw new DriverIsOnDutyException();
        }
        // --------- end cek bagian driver ---------
        
        if (allIsValid){
            // update is borrow menjadi true (tanda bahwa kendaraan sedang dipinjam)
            Map<Object, Object> updateVehicle = new HashMap<>();
            updateVehicle.put("isBorrow", true);
            vehicleService.updatePartial(vehicleData.getRegistrationNumber(), updateVehicle);

            // pindahkan data dari vehicle data ke vehicle
            Vehicle vehicle = modelMapper.map(vehicleData, Vehicle.class);
            // over write is borrow menjadi true di data yang akan dikembalikan
            vehicle.setIsBorrow(true);

            // update driver sedang bertugas
            Map<Object, Object> updateDriver = new HashMap<>();
            updateDriver.put("isOnDuty", true);
            driverService.updatePartialDriver(driver.getIdDriver(), updateDriver);
            
            // over write driver is on duty
            driver.setIsOnDuty(true);

            // over write user dengan data di database
            // set password agar tidak bisa dilihat
            user.setPassword(INVISIBLE);
            borrowVehicle.setUser(user);

            // over write vehicle dengan data di database
            borrowVehicle.setVehicle(vehicle);

            // over write driver dengan data di database
            borrowVehicle.setDriver(driver);

            return repository.save(borrowVehicle);
        } else {
            return null;
        }
    }
    
}
