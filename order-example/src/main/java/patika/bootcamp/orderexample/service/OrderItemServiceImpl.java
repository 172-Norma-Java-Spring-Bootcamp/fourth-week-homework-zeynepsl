package patika.bootcamp.orderexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.OrderItemExceptionMessage;
import patika.bootcamp.orderexample.exception.OrderItemServiceOperationException;
import patika.bootcamp.orderexample.model.OrderItem;
import patika.bootcamp.orderexample.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{

	private final OrderItemRepository orderItemRepository;
	
	@Override
	public void create(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}

	@Override
	public void delete(OrderItem orderItem) {
		orderItemRepository.delete(orderItem);
	}

	@Override
	public OrderItem getOrderItem(Long id) {
		OrderItem orderItem = orderItemRepository
				.findById(id)
				.orElseThrow(() -> new OrderItemServiceOperationException.OrderItemNotFoundException(OrderItemExceptionMessage.notFound));
		return orderItem;
	}

	@Override
	public List<OrderItem> getAll() {
		return orderItemRepository.findAll();
	}

}
