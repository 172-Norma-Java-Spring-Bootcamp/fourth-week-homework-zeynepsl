package patika.bootcamp.orderexample.converter.order;

import patika.bootcamp.orderexample.dto.order.CreateOrderRequestDto;
import patika.bootcamp.orderexample.dto.order.OrderResponseDto;
import patika.bootcamp.orderexample.model.Order;

public interface OrderConverter {
	Order toOrder(CreateOrderRequestDto createOrderRequestDto);
	OrderResponseDto toOrderResponseDto(Order order);
}
