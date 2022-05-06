package patika.bootcamp.orderexample.dto.basket;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketItemDto {
	private Long basketId;
	private Long productId;
    private Integer quantity;
    private BigDecimal price = BigDecimal.ZERO;
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private Double taxPrice = 0.0;
    private BigDecimal shippingPrice = BigDecimal.ZERO;
}
