package zero.programmer.data.kendaraan.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.models.UserData;
import zero.programmer.data.kendaraan.repositories.UserRepository;
import zero.programmer.data.kendaraan.services.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserData createUser(UserData userData) {
        User user = modelMapper.map(userData, User.class);
        userRepository.save(user);
        return userData;
    }

    @Override
    public UserData getUser(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> listUser() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String removeUser(String username) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
