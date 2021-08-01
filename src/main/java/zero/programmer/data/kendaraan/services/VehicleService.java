package zero.programmer.data.kendaraan.services;

import java.util.List;

import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.models.VehicleData;

public interface VehicleService {
    
    public VehicleData create(VehicleData vehicleData);

    public VehicleData getVehicle(String registrationNumber);

    public List<Vehicle> listVehicle();

}
