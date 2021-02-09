package my.shop.com.myshopjava.service;

import my.shop.com.myshopjava.model.User;

import java.util.List;

public interface UserService {
    User register(User user);

    User findByUsername(String name);

    User getById(Long id);

    List<User> getAll();

    void delete(Long id);
}
