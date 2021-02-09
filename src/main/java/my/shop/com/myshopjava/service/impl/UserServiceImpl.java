package my.shop.com.myshopjava.service.impl;

import lombok.extern.slf4j.Slf4j;
import my.shop.com.myshopjava.model.Role;
import my.shop.com.myshopjava.model.Status;
import my.shop.com.myshopjava.model.User;
import my.shop.com.myshopjava.repository.RoleRepository;
import my.shop.com.myshopjava.repository.UserRepository;
import my.shop.com.myshopjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User register(User user) {
        Role role = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(role);

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setStatus(Status.ACTIVE);
        user.setRoles(roles);

        User newUser = userRepository.save(user);
        log.info("register : user {} registered", newUser);

        return newUser;
    }

    @Override
    public User findByUsername(String name) {
        User users = userRepository.findByUsername(name);
        log.info("findByUsername : found by username: {}", name);
        return users;
    }

    @Override
    public User getById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            log.warn("getById: user by id {} not found", id);
            return  null;
        }
        log.info("getById: user by id {} was found", id);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        log.info("getAll: was found {} users", users.size());
        return users;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("delete: user with id {} was deleted", id);
    }
}
