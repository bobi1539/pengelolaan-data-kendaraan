package zero.programmer.data.kendaraan.services;

import java.util.List;

import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.models.UserData;

public interface UserService {

    public UserData createUser(UserData userData);

    public UserData getUser(String username);

    public List<User> listUser();

    public String removeUser(String username);
    
}
