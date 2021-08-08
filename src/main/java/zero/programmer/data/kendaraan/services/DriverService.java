package zero.programmer.data.kendaraan.services;

import java.util.List;
import java.util.Map;

import zero.programmer.data.kendaraan.entities.Driver;


public interface DriverService {

    public Driver createDriver(Driver driver);

    public Driver updateDriver(Driver driver);

    public Driver getDriver(String idDriver);

    public List<Driver> listDriver();

    public String deleteDriver(String idDriver);

    public Driver updatePartialDriver(String idDriver, Map<Object, Object> fields);
    
}
