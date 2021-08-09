package zero.programmer.data.kendaraan.services;

import java.util.List;
import java.util.Map;

import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.models.LoginData;

public interface UserService {

    public User createUser(User user);

    public User updateUser(User user);

    public User getUser(String username);

    public List<User> listUser();

    public String removeUser(String username);

    public User updatePartial(String username, Map<Object, Object> fields);

    public User checkLoginUser(LoginData loginData) throws Exception;
    
}
