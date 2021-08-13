package zero.programmer.data.kendaraan.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
public class BorrowVehicleServiceImpl implements BorrowVehicleService {

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

    @Override
    public BorrowVehicle createBorrowVehicle(BorrowVehicleData borrowVehicleData)
            throws NotFoundException, VehicleIsBorrowException, DriverIsOnDutyException {

        boolean allIsValid = true;

        // pindahkan data dari borrowVehicleData ke borrowVehicle
        BorrowVehicle borrowVehicle = modelMapper.map(borrowVehicleData, BorrowVehicle.class);

        // --------- cek bagian user ---------
        User user = userService.getUser(borrowVehicle.getUser().getUsername());

        // cek apakah user ada atau tidak
        if (user == null) {
            allIsValid = false;
            throw new NotFoundException();
        }
        // --------- end cek bagian user ---------

        // --------- cek bagian kendaraan ---------
        VehicleData vehicleData = new VehicleData();

        // cek apakah kendaraan ada atau tidak
        try {
            vehicleData = vehicleService.getVehicle(borrowVehicle.getVehicle().getRegistrationNumber());
        } catch (NullPointerException e) {
            allIsValid = false;
            throw new NotFoundException();
        }

        // cek apakah kendaraan dipinjam atau tidak
        if (vehicleData.getIsBorrow()) {
            allIsValid = false;
            throw new VehicleIsBorrowException();
        }
        // --------- end cek bagian kendaraan ---------

        // --------- cek bagian driver ---------

        if (borrowVehicle.getDriver() != null) {

            Driver driver = new Driver();
            driver = driverService.getDriver(borrowVehicle.getDriver().getIdDriver());
            // cek driver ada atau tidak
            if (driver == null) {
                allIsValid = false;
                throw new NotFoundException();
            }

            // cek driver sedang bertugas atau tidak
            if (driver.getIsOnDuty()) {
                allIsValid = false;
                throw new DriverIsOnDutyException();
            }
            // --------- end cek bagian driver ---------

            if (allIsValid) {
                // update driver sedang bertugas
                Map<Object, Object> updateDriver = new HashMap<>();
                updateDriver.put("isOnDuty", true);
                driverService.updatePartialDriver(driver.getIdDriver(), updateDriver);

                // over write driver is on duty
                driver.setIsOnDuty(true);

                // over write driver dengan data di database
                borrowVehicle.setDriver(driver);
            }
        }

        if (allIsValid) {
            // update is borrow menjadi true (tanda bahwa kendaraan sedang dipinjam)
            Map<Object, Object> updateVehicle = new HashMap<>();
            updateVehicle.put("isBorrow", true);
            vehicleService.updatePartial(vehicleData.getRegistrationNumber(), updateVehicle);

            // pindahkan data dari vehicle data ke vehicle
            Vehicle vehicle = modelMapper.map(vehicleData, Vehicle.class);
            // over write is borrow menjadi true di data yang akan dikembalikan
            vehicle.setIsBorrow(true);

            // over write user dengan data di database
            borrowVehicle.setUser(user);

            // over write vehicle dengan data di database
            borrowVehicle.setVehicle(vehicle);

            // membuat agar borrow status false 
            borrowVehicle.setBorrowStatus(false);

            return repository.save(borrowVehicle);
        } else {
            return null;
        }
    }

    @Override
    public List<BorrowVehicle> listBorrowVehicle() throws NotFoundException {
        return getListBorrowVehicle(repository.findAll());
    }

    @Override
    public List<BorrowVehicle> listBorrowVehicleForDinas() throws NotFoundException {
        return getListBorrowVehicle(repository.findBorrowVehicleForDinas());
    }

    @Override
    public List<BorrowVehicle> listBorrowVehicleForPersonal() throws NotFoundException {
        return getListBorrowVehicle(repository.findBorrowVehicleForPersonal());
    }

    @Override
    public List<BorrowVehicle> listBorrowVehicleForDinasByUsername(String username) throws NotFoundException {
        return getListBorrowVehicle(repository.findBorrowVehicleForDinasByUsername(username));
    }

    @Override
    public List<BorrowVehicle> listBorrowVehicleForPersonalByUsername(String username) throws NotFoundException {
        return getListBorrowVehicle(repository.findBorrowVehicleForPersonalByUsername(username));
    }

    @Override
    public String deleteBorrowVehicle(Integer idBorrow) throws NotFoundException {
        Optional<BorrowVehicle> borrowVehicle = repository.findById(idBorrow);
        if (!borrowVehicle.isPresent()) {
            throw new NotFoundException();
        } else {

            try{
                // ambil data kendaraan
                VehicleData vehicle = vehicleService.getVehicle(borrowVehicle.get().getVehicle().getRegistrationNumber());
                // cek jika kendaraan sedang dipinjam tidak bisa di hapus data peminjamannya
                if (vehicle.getIsBorrow()){
                    return null;
                } else {
                    repository.deleteById(idBorrow);
                    return "Data berhasil dihapus";
                }
            } catch (NullPointerException e){
                throw new NotFoundException();
            }
        }
    }

    private List<BorrowVehicle> getListBorrowVehicle(List<BorrowVehicle> repository) throws NotFoundException {
        if (repository.isEmpty()) {
            throw new NotFoundException();
        }
        return repository;
    }

    @Override
    public BorrowVehicle getBorrowVehicle(Integer idBorrow) throws NotFoundException {
        Optional<BorrowVehicle> borrowVehicle = repository.findById(idBorrow);
        if (!borrowVehicle.isPresent()){
            throw new NotFoundException();
        }
        return borrowVehicle.get();
    }

}
