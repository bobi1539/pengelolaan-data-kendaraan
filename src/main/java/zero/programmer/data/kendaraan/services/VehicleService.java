package zero.programmer.data.kendaraan.services;

import java.util.List;
import java.util.Map;

import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.error.NullPointerException;
import zero.programmer.data.kendaraan.models.VehicleData;

public interface VehicleService {
    
    public VehicleData create(VehicleData vehicleData);

    public VehicleData updateVehicle(VehicleData vehicleData);

    public VehicleData getVehicle(String registrationNumber) throws NullPointerException;

    public List<Vehicle> listVehicle() throws NullPointerException;

    public String remove(String registrationNumber);

    public Vehicle updatePartial(String registrationNumber, Map<Object, Object> fields);

}
