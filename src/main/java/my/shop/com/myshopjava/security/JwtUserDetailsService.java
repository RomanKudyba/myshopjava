package my.shop.com.myshopjava.security;

import lombok.extern.slf4j.Slf4j;
import my.shop.com.myshopjava.model.User;
import my.shop.com.myshopjava.security.jwt.JwtUser;
import my.shop.com.myshopjava.security.jwt.JwtUserFactory;
import my.shop.com.myshopjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService  implements UserDetailsService {
    private  final  UserService userService;

    @Autowired
    JwtUserDetailsService (UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("loadUserByUsername: user by name {} loaded", username);
        return jwtUser;
    }
}
