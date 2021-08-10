package zero.programmer.data.kendaraan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import zero.programmer.data.kendaraan.entities.BorrowVehicle;

public interface BorrowVehicleRepository extends JpaRepository<BorrowVehicle, Integer>{
    
}
