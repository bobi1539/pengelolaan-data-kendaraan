package zero.programmer.data.kendaraan.services.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

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
        return userRepository.findAll();
    }

    @Override
    public String removeUser(String username) {
        boolean userIsExists = userRepository.findById(username).isPresent();
        if (!userIsExists){
            return null;
        }
        userRepository.deleteById(username);
        return "Data berhasil dihapus";
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

    @Override
    public User updatePartial(String username, Map<Object, Object> fields) {
        
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent()){
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(User.class, (String) key);
                field.setAccessible(true);

                // cek jika yang di ubah adalah password, maka di encode
                if (key.equals("password")){
                    value = bCryptPasswordEncoder.encode(value.toString());
                }

                ReflectionUtils.setField(field, user.get(), value);
            });
            return userRepository.save(user.get());
        }

        return null;
    }

}
