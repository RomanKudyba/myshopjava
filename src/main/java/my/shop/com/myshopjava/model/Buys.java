package my.shop.com.myshopjava.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "buys")
@Data
public class Buys extends BaseEntity{

    @Column(name = "count")
    private Integer count;

    @Column(name = "full_price")
    private Double fullPrice;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;

    @ManyToOne
    @JoinColumn(name="provider_id")
    private Providers provider;
}
