package zero.programmer.data.kendaraan.repositories;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import zero.programmer.data.kendaraan.entities.BorrowVehicle;

public interface BorrowVehicleRepository extends JpaRepository<BorrowVehicle, Integer>{

    @Query(value = "SELECT * FROM borrow_vehicle JOIN users   ON users.username = borrow_vehicle.username JOIN vehicles   ON vehicles.registration_number = borrow_vehicle.registration_number JOIN drivers   ON drivers.id_driver = borrow_vehicle.id_driver WHERE borrow_vehicle.borrow_type = 'DINAS'", nativeQuery = true)
    public List<BorrowVehicle> findBorrowVehicleForDinas();

    @Query(value = "SELECT * FROM borrow_vehicle JOIN users   ON users.username = borrow_vehicle.username JOIN vehicles   ON vehicles.registration_number = borrow_vehicle.registration_number JOIN drivers   ON drivers.id_driver = borrow_vehicle.id_driver WHERE borrow_vehicle.borrow_type = 'DINAS' AND borrow_vehicle.username = :username", nativeQuery = true)
    public List<BorrowVehicle> findBorrowVehicleForDinasByUsername(@PathParam("username") String username);

    @Query(value = "SELECT * FROM borrow_vehicle JOIN users ON users.username = borrow_vehicle.username JOIN vehicles ON vehicles.registration_number = borrow_vehicle.registration_number WHERE borrow_vehicle.borrow_type = 'PRIBADI'", nativeQuery = true)
    public List<BorrowVehicle> findBorrowVehicleForPersonal();

    @Query(value = "SELECT * FROM borrow_vehicle JOIN users ON users.username = borrow_vehicle.username JOIN vehicles ON vehicles.registration_number = borrow_vehicle.registration_number WHERE borrow_vehicle.borrow_type = 'PRIBADI' AND borrow_vehicle.username = :username", nativeQuery = true)
    public List<BorrowVehicle> findBorrowVehicleForPersonalByUsername(@PathParam("username") String username);

}
