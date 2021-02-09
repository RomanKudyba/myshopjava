package my.shop.com.myshopjava.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Data
public class Products extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

//    @OneToMany(targetEntity=Characteristic.class, mappedBy="product",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Characteristic> characteristics = new ArrayList<>();
//
//    @OneToMany(targetEntity=Buys.class, mappedBy="product",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Buys> buys = new ArrayList<>();
//
//    @OneToMany(targetEntity=Sells.class, mappedBy="product",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Sells> sells = new ArrayList<>();
}
