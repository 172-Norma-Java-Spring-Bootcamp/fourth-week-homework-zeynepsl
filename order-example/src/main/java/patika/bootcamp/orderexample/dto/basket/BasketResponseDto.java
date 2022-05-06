package patika.bootcamp.orderexample.dto.basket;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketResponseDto {
    private BigDecimal totalPrice;
    private Long customerId;
    private List<BasketItemDto> items;
    private Integer totalNumberOfProducts;
}
