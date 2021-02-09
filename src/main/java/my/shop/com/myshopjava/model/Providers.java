package my.shop.com.myshopjava.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "providers")
@Data
public class Providers extends BaseEntity{

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "mid_name")
    private String midName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "age")
    private Integer age;
}
