package patika.bootcamp.orderexample.converter.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.dto.order.CreateOrderRequestDto;
import patika.bootcamp.orderexample.dto.order.OrderItemDto;
import patika.bootcamp.orderexample.dto.order.OrderResponseDto;
import patika.bootcamp.orderexample.model.Order;
import patika.bootcamp.orderexample.service.CustomerService;

@Component
@RequiredArgsConstructor
public class OrderConverterImpl implements OrderConverter{

	private final CustomerService customerService;
	@Override
	public Order toOrder(CreateOrderRequestDto createOrderRequestDto) {
		Order order = new Order();
		order.setCustomer(customerService.getCustomerAllInfo(createOrderRequestDto.getCustomerId()));
		return order;
	}
	
	@Override
	public OrderResponseDto toOrderResponseDto(Order order) {
		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setBillingAddress(order.getBillingAddress());
		orderResponseDto.setCargoFirm(order.getCargoFirm());
		orderResponseDto.setCity(order.getCity());
		orderResponseDto.setCountry(order.getCountry());
		orderResponseDto.setCustomerId(order.getCustomer().getId());
		orderResponseDto.setDate(order.getDate());
		orderResponseDto.setShipAddress(order.getShipAddress());
		orderResponseDto.setShipName(order.getShipName());
		orderResponseDto.setTotalCargoPrice(order.getTotalCargoPrice());
		orderResponseDto.setShipped(order.isShipped());
		orderResponseDto.setTotalPrice(order.getTotalPrice());
		orderResponseDto.setTrackingNumber(order.getTrackingNumber());
		orderResponseDto.setZipCode(order.getZipCode());
		orderResponseDto.setPhone(order.getPhone());
		orderResponseDto.setTaxPrice(order.getTaxPrice());
		orderResponseDto.setShippingPrice(order.getShippingPrice());
		
		List<OrderItemDto> orderItems = new ArrayList<OrderItemDto>();
		
		order.getOrderItems().forEach(orderItem -> {
			OrderItemDto item = new OrderItemDto();
			item.setOrderId(orderItem.getOrder().getId());
			item.setProductId(orderItem.getProduct().getId());
			item.setQuantity(orderItem.getQuantity());
			orderItems.add(item);
		});
		orderResponseDto.setOrderItems(orderItems);
		return orderResponseDto;
	}

}
