package my.shop.com.myshopjava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Characteristic extends BaseEntity{

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "size")
    private Double size;

    @Column(name = "platform")
    private String platform;

    @Column(name = "main_camera")
    private Float mainCamera;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Products product;

}
