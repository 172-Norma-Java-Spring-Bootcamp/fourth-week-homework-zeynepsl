package patika.bootcamp.orderexample.service;

import java.util.List;

import patika.bootcamp.orderexample.model.OrderItem;

public interface OrderItemService {
	void create(OrderItem orderItem);
	void delete(OrderItem orderItem);
	OrderItem getOrderItem(Long id);
	List<OrderItem> getAll();
}
