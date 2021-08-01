package zero.programmer.data.kendaraan.services;

import zero.programmer.data.kendaraan.models.VehicleData;

public interface VehicleService {
    
    public VehicleData create(VehicleData vehicleData);

    public VehicleData getVehicle(String registrationNumber);

}
