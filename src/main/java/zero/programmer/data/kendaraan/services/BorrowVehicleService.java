package zero.programmer.data.kendaraan.services;

import zero.programmer.data.kendaraan.entities.BorrowVehicle;
import zero.programmer.data.kendaraan.error.DriverIsOnDutyException;
import zero.programmer.data.kendaraan.error.NotFoundException;
import zero.programmer.data.kendaraan.error.VehicleIsBorrowException;
import zero.programmer.data.kendaraan.models.BorrowVehicleData;

public interface BorrowVehicleService {
    
    public BorrowVehicle createBorrowVehicle(BorrowVehicleData borrowVehicleData) throws NotFoundException, VehicleIsBorrowException, DriverIsOnDutyException;

}
