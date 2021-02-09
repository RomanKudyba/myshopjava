package my.shop.com.myshopjava.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category extends BaseEntity{

    @NotBlank()
    @Column(name = "name")
    private String name;

//    @OneToMany(targetEntity=Products.class, mappedBy="category",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Products> products = new ArrayList<>();
}
