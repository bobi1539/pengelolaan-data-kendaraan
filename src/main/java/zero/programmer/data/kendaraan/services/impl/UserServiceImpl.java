package zero.programmer.data.kendaraan.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import zero.programmer.data.kendaraan.entities.User;
import zero.programmer.data.kendaraan.repositories.UserRepository;
import zero.programmer.data.kendaraan.services.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) {
        boolean userExists = userRepository.findById(user.getUsername()).isPresent();
        if (userExists) {
            return null;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        Optional<User> user = userRepository.findById(username);
        if (!user.isPresent()) {
            return null;
        }
        return user.get();
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

    @Override
    public User updateUser(User user) {
        boolean userExists = userRepository.findById(user.getUsername()).isPresent();
        if (!userExists){
            return null;
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

}
