package patika.bootcamp.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class BasketItem extends BaseModel {
	
	//(optional = false)
	@ManyToOne
	@JoinColumn(name = "basket_id")
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private Integer quantity;
    
    @Column(nullable = false)
    private BigDecimal price = BigDecimal.ZERO;
    
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private Double taxPrice = 0.0;
    private BigDecimal shippingPrice = BigDecimal.ZERO;

}
