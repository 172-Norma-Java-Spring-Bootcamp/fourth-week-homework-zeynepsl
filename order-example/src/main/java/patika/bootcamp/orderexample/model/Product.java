package patika.bootcamp.orderexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseExtendedModel {

    private String name;
    private BigDecimal price;
    private UUID barcode;
    private String image;
    /*@Transient
	private MultipartFile productImage;*/
    private Integer stockAmount;
    private Integer sellCount = 0;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<BasketItem> basketItems = new ArrayList<BasketItem>();
    
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    //the category has no connection with the product
    //but the product has connection with category 
    //so we added category to product
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();
    
    
}
