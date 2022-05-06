package patika.bootcamp.orderexample.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
	private Long orderId;
	private Long productId;
	private Integer quantity;
}
