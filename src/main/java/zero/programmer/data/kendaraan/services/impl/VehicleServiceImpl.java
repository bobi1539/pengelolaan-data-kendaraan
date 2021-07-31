package zero.programmer.data.kendaraan.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.repositories.VehicleRepository;
import zero.programmer.data.kendaraan.services.VehicleService;

public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
    
}
