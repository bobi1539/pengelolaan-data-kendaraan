package zero.programmer.data.kendaraan.services.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import zero.programmer.data.kendaraan.entities.Vehicle;
import zero.programmer.data.kendaraan.error.NullPointerException;
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

        if (isVehicleExists(vehicleData.getRegistrationNumber())){
            return null;
        }
        
        Vehicle vehicle = modelMapper.map(vehicleData, Vehicle.class);
        vehicleRepository.save(vehicle);
        return vehicleData;
    }

    @Override
    public VehicleData getVehicle(String registrationNumber) throws NullPointerException{
        Optional<Vehicle> vehicle = vehicleRepository.findById(registrationNumber);
        if (!vehicle.isPresent()){
            throw new NullPointerException();
        }
        VehicleData vehicleData = modelMapper.map(vehicle.get(), VehicleData.class);
        return vehicleData;
    }

    @Override
    public List<Vehicle> listVehicle() throws NullPointerException{
        List<Vehicle> listVehicle = vehicleRepository.findAll();
        if (listVehicle.isEmpty()){
            throw new NullPointerException();
        }
        return listVehicle;
    }

    @Override
    public String remove(String registrationNumber) {
        if (!isVehicleExists(registrationNumber)){
            return null;
        }
        vehicleRepository.deleteById(registrationNumber);
        return "Data berhasil dihapus";
    }

    @Override
    public Vehicle updatePartial(String registrationNumber, Map<Object, Object> fields) {
        
        Optional<Vehicle> vehicle = vehicleRepository.findById(registrationNumber);
        if(vehicle.isPresent()){
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Vehicle.class, (String) key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, vehicle.get(), value);
            });
            return vehicleRepository.save(vehicle.get());
        }

        return null;
    }

    @Override
    public VehicleData updateVehicle(VehicleData vehicleData) {
        
        if (!isVehicleExists(vehicleData.getRegistrationNumber())){
            return null;
        }

        Vehicle vehicle = modelMapper.map(vehicleData, Vehicle.class);
        vehicleRepository.save(vehicle);
        return vehicleData;
    }

    private boolean isVehicleExists(String registrationNumber){
        return vehicleRepository.findById(registrationNumber).isPresent();
    }
    
}
