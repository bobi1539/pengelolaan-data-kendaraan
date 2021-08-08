package zero.programmer.data.kendaraan.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zero.programmer.data.kendaraan.entities.Driver;
import zero.programmer.data.kendaraan.repositories.DriverRepository;
import zero.programmer.data.kendaraan.services.DriverService;

@Service
@Transactional
public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverRepository driverRepository;

    @Override
    public Driver createDriver(Driver driver) {
        
        boolean driverExists = driverRepository.findById(driver.getIdDriver()).isPresent();
        if (driverExists){
            return null;
        }

        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(Driver driver) {
        
        return null;
    }

    @Override
    public Driver getDriver(String idDriver) {
        
        return null;
    }

    @Override
    public List<Driver> listDriver() {
        
        return null;
    }

    @Override
    public String deleteDriver(String idDriver) {
        
        return null;
    }

    @Override
    public Driver updatePartialDriver(String idDriver, Map<Object, Object> fields) {
        
        return null;
    }
    
}
