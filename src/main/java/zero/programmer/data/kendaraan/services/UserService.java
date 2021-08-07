package zero.programmer.data.kendaraan.services;

import java.util.List;

import zero.programmer.data.kendaraan.entities.User;

public interface UserService {

    public User createUser(User user);

    public User getUser(String username);

    public List<User> listUser();

    public String removeUser(String username);
    
}
