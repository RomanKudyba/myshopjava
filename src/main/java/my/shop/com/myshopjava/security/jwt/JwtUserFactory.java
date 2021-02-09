package my.shop.com.myshopjava.security.jwt;

import my.shop.com.myshopjava.model.Role;
import my.shop.com.myshopjava.model.Status;
import my.shop.com.myshopjava.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory(){
    }

    public static JwtUser create(User user){
        return new JwtUser(
            user.getId(),
            user.getUsername(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPassword(),
            user.getStatus().equals(Status.ACTIVE),
            user.getUpdated(),
            mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
