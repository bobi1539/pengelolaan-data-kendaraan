package zero.programmer.data.kendaraan.services.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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
        if (isDriverExists(driver.getIdDriver())){
            return null;
        }

        return driverRepository.save(driver);
    }

    @Override
    public Driver updateDriver(Driver driver) {
        if (!isDriverExists(driver.getIdDriver())){
            return null;    
        }
        return driverRepository.save(driver);
    }

    @Override
    public Driver getDriver(String idDriver) {
        Optional<Driver> driver = driverRepository.findById(idDriver);
        if (!isDriverExists(idDriver)){
            return null;
        }
        return driver.get();
    }

    @Override
    public List<Driver> listDriver() {
        List<Driver> listDriver = driverRepository.findAll();
        if (listDriver.isEmpty()){
            return null;
        }
        return listDriver;
    }

    @Override
    public String deleteDriver(String idDriver) {
        if (!isDriverExists(idDriver)){
            return null;
        }
        driverRepository.deleteById(idDriver);
        return "Data berhasil dihapus";
    }

    @Override
    public Driver updatePartialDriver(String idDriver, Map<Object, Object> fields) {
        
        Optional<Driver> driver = driverRepository.findById(idDriver);
        if (!driver.isPresent()){
            return null;
        }
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Driver.class, (String) key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, driver.get(), value);
        });
        return driverRepository.save(driver.get());

    }
    
    private boolean isDriverExists(String idDriver){
        return driverRepository.findById(idDriver).isPresent();
    }

}
