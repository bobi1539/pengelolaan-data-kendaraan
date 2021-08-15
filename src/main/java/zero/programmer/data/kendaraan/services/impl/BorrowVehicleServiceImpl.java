package zero.programmer.data.kendaraan.services.impl;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import zero.programmer.data.kendaraan.entities.BorrowVehicle;
import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.entities.Driver;
import zero.programmer.data.kendaraan.error.DriverIsOnDutyException;
import zero.programmer.data.kendaraan.error.NotFoundException;
import zero.programmer.data.kendaraan.error.NullPointerException;
import zero.programmer.data.kendaraan.error.VehicleIsBorrowException;
import zero.programmer.data.kendaraan.models.BorrowVehicleData;
import zero.programmer.data.kendaraan.models.UpdateRequestBorrowVehicle;
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

            // membuat agar borrow status true
            borrowVehicle.setBorrowStatus(true);

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
            // cek jika status sedang dipinjam tidak bisa di hapus data peminjamannya
            if (borrowVehicle.get().getBorrowStatus()) {
                return null;
            } else {
                repository.deleteById(idBorrow);
                return "Data berhasil dihapus";
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
        if (!borrowVehicle.isPresent()) {
            throw new NotFoundException();
        }
        return borrowVehicle.get();
    }

    @Override
    public BorrowVehicle updatePartial(Integer idBorrow, Map<Object, Object> fields) throws NotFoundException {

        // find data from database
        Optional<BorrowVehicle> borrowVehicle = repository.findById(idBorrow);

        // cek jika data tidak ada di db
        if (!borrowVehicle.isPresent()) {
            throw new NotFoundException();
        } else {

            // set data to request update borrow vehicle agar bisa direfleksikan yang tipe
            // data date
            UpdateRequestBorrowVehicle requestBorrow = new UpdateRequestBorrowVehicle(
                    borrowVehicle.get().getNecessity(), String.valueOf(borrowVehicle.get().getBorrowDate()),
                    String.valueOf(borrowVehicle.get().getReturnDate()), borrowVehicle.get().getDestination(),
                    borrowVehicle.get().getBorrowStatus());

            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(UpdateRequestBorrowVehicle.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, requestBorrow, value);
            });

            // update status vehicle menjadi ready / false
            Map<Object, Object> updateVehicle = new HashMap<>();
            updateVehicle.put("isBorrow", requestBorrow.getBorrowStatus());
            vehicleService.updatePartial(borrowVehicle.get().getVehicle().getRegistrationNumber(), updateVehicle);
            // over write status vehicle
            borrowVehicle.get().getVehicle().setIsBorrow(requestBorrow.getBorrowStatus());

            // cek jika driver tidak null
            if (borrowVehicle.get().getDriver() != null) {
                // update status driver menjadi ready / false
                Map<Object, Object> updateDriver = new HashMap<>();
                updateDriver.put("isOnDuty", requestBorrow.getBorrowStatus());
                driverService.updatePartialDriver(borrowVehicle.get().getDriver().getIdDriver(), updateDriver);
                // over write status driver
                borrowVehicle.get().getDriver().setIsOnDuty(requestBorrow.getBorrowStatus());
            }

            // set data baru dari requestBorrow
            borrowVehicle.get().setNecessity(requestBorrow.getNecessity());
            System.out.println("tanggal " + requestBorrow.getBorrowDate());

            // cek apakah tanggal pinjam berubah
            if (!borrowVehicle.get().getBorrowDate().toString().equals(requestBorrow.getBorrowDate())) {
                borrowVehicle.get().setBorrowDate(Date.valueOf(requestBorrow.getBorrowDate()));
            }
            // cek apakah tanggal kembali berubah
            if (!borrowVehicle.get().getReturnDate().toString().equals(requestBorrow.getReturnDate())) {
                borrowVehicle.get().setReturnDate(Date.valueOf(requestBorrow.getReturnDate()));
            }

            borrowVehicle.get().setDestination(requestBorrow.getDestination());
            borrowVehicle.get().setBorrowStatus(requestBorrow.getBorrowStatus());
            return repository.save(borrowVehicle.get());
        }
    }

    @Override
    public List<BorrowVehicle> listBorrowVehicleForDinasByDateOfFilling(String dateOfFilling) throws NotFoundException {
        List<BorrowVehicle> listBorrow = repository.findBorrowVehicleForDinasLike("%" + dateOfFilling + "%");
        if (listBorrow.isEmpty()) {
            throw new NotFoundException();
        }
        return listBorrow;
    }

    @Override
    public List<BorrowVehicle> listBorrowVehicleForPersonalByDateOfFilling(String dateOfFilling)
            throws NotFoundException {
        List<BorrowVehicle> listBorrow = repository.findBorrowVehicleForPersonalLike("%" + dateOfFilling + "%");
        if (listBorrow.isEmpty()) {
            throw new NotFoundException();
        }
        return listBorrow;
    }

}
