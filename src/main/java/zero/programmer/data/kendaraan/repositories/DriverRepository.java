package zero.programmer.data.kendaraan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import zero.programmer.data.kendaraan.entities.Driver;

public interface DriverRepository extends JpaRepository<Driver, String> {

}
