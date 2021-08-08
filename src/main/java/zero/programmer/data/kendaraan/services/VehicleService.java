package zero.programmer.data.kendaraan.services;

import java.util.List;
import java.util.Map;

import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.models.VehicleData;

public interface VehicleService {
    
    public VehicleData create(VehicleData vehicleData);

    public VehicleData getVehicle(String registrationNumber);

    public List<Vehicle> listVehicle();

    public String remove(String registrationNumber);

    public Vehicle updatePartial(String registrationNumber, Map<Object, Object> fields);

}
