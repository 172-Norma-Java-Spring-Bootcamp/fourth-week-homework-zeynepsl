package patika.bootcamp.orderexample.dto.basket;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketDto {
    private BigDecimal price;
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private Double taxPrice = 0.0;
    private BigDecimal shippingPrice = BigDecimal.ZERO;
    private BigDecimal totalPrice;
    private Long customerId;
    private Long discountId;
}
