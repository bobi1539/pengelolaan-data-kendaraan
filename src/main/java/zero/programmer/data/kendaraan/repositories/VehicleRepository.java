package zero.programmer.data.kendaraan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import zero.programmer.data.kendaraan.entities.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String>{
    
}
