package zero.programmer.data.kendaraan.services;

import java.util.List;

import zero.programmer.data.kendaraan.entities.BorrowVehicle;
import zero.programmer.data.kendaraan.error.DriverIsOnDutyException;
import zero.programmer.data.kendaraan.error.NotFoundException;
import zero.programmer.data.kendaraan.error.VehicleIsBorrowException;
import zero.programmer.data.kendaraan.models.BorrowVehicleData;

public interface BorrowVehicleService {
    
    public BorrowVehicle createBorrowVehicle(BorrowVehicleData borrowVehicleData) throws NotFoundException, VehicleIsBorrowException, DriverIsOnDutyException;

    public List<BorrowVehicle> listBorrowVehicle() throws NotFoundException;

    public List<BorrowVehicle> listBorrowVehicleByUsername(String username) throws NotFoundException;

    public List<BorrowVehicle> listBorrowVehicleByType(String borrowType) throws NotFoundException;

}
