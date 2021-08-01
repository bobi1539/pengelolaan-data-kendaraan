package zero.programmer.data.kendaraan.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.models.VehicleData;
import zero.programmer.data.kendaraan.repositories.VehicleRepository;
import zero.programmer.data.kendaraan.services.VehicleService;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VehicleData create(VehicleData vehicleData) {
        Vehicle vehicle = modelMapper.map(vehicleData, Vehicle.class);
        vehicleRepository.save(vehicle);
        return vehicleData;
    }

    @Override
    public VehicleData getVehicle(String registrationNumber) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(registrationNumber);
        if (!vehicle.isPresent()){
            return null;
        }
        VehicleData vehicleData = modelMapper.map(vehicle.get(), VehicleData.class);
        return vehicleData;
    }

    @Override
    public List<Vehicle> listVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public String remove(String registrationNumber) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(registrationNumber);
        if (!vehicle.isPresent()){
            return null;
        }
        vehicleRepository.deleteById(registrationNumber);
        return "Data berhasil dihapus";
    }
    
}
