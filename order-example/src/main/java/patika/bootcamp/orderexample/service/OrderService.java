package patika.bootcamp.orderexample.service;

import java.util.List;

import patika.bootcamp.orderexample.dto.order.CreateOrderRequestDto;
import patika.bootcamp.orderexample.dto.order.OrderResponseDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.model.Order;

public interface OrderService {
	OrderResponseDto create(CreateOrderRequestDto createOrderRequest) throws BaseException;
	void delete(Long id) throws BaseException;
	OrderResponseDto getOrder(Long id) throws BaseException;
	List<OrderResponseDto> getAll();
	List<Order> findByCustomer_Id(Long customerId);
}
