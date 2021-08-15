package zero.programmer.data.kendaraan.services;

import java.util.List;
import java.util.Map;

import zero.programmer.data.kendaraan.entities.BorrowVehicle;
import zero.programmer.data.kendaraan.error.DriverIsOnDutyException;
import zero.programmer.data.kendaraan.error.NotFoundException;
import zero.programmer.data.kendaraan.error.VehicleIsBorrowException;
import zero.programmer.data.kendaraan.models.BorrowVehicleData;

public interface BorrowVehicleService {
    
    public BorrowVehicle createBorrowVehicle(BorrowVehicleData borrowVehicleData) throws NotFoundException, VehicleIsBorrowException, DriverIsOnDutyException;

    public List<BorrowVehicle> listBorrowVehicle() throws NotFoundException;

    public List<BorrowVehicle> listBorrowVehicleForDinas() throws NotFoundException;

    public List<BorrowVehicle> listBorrowVehicleForDinasByUsername(String username) throws NotFoundException;

    public List<BorrowVehicle> listBorrowVehicleForPersonal() throws NotFoundException;

    public List<BorrowVehicle> listBorrowVehicleForPersonalByUsername(String username) throws NotFoundException;

    public List<BorrowVehicle> listBorrowVehicleForDinasByDateOfFilling(String dateOfFilling) throws NotFoundException;

    public List<BorrowVehicle> listBorrowVehicleForPersonalByDateOfFilling(String dateOfFilling) throws NotFoundException;

    public BorrowVehicle getBorrowVehicle(Integer idBorrow) throws NotFoundException;

    public String deleteBorrowVehicle(Integer idBorrow) throws NotFoundException;

    public BorrowVehicle updatePartial(Integer idBorrow, Map<Object, Object> fields) throws NotFoundException;

}
