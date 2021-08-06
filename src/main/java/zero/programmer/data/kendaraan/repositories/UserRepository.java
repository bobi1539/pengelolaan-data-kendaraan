package zero.programmer.data.kendaraan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import zero.programmer.data.kendaraan.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
