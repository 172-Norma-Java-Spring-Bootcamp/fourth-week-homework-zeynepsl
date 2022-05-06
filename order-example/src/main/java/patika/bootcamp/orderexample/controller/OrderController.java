package patika.bootcamp.orderexample.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.dto.order.CreateOrderRequestDto;
import patika.bootcamp.orderexample.dto.order.OrderResponseDto;
import patika.bootcamp.orderexample.model.Order;
import patika.bootcamp.orderexample.service.OrderService;
import patika.bootcamp.orderexample.validator.Validator;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
	private final OrderService orderService;
	private final Validator<Long> idValidator;
	private final Validator<CreateOrderRequestDto> createOrderRequestDtoValidator;
	
	@PostMapping("/")
	public ResponseEntity<OrderResponseDto> create(@RequestBody CreateOrderRequestDto createOrderRequestDto) {
		createOrderRequestDtoValidator.validate(createOrderRequestDto);
		return ResponseEntity.ok(orderService.create(createOrderRequestDto));
	}
	
	@GetMapping("{customerId}")
	public ResponseEntity<List<Order>> findByCustomer_Id(@PathVariable Long customerId){
		idValidator.validate(customerId);
		return ResponseEntity.ok(orderService.findByCustomer_Id(customerId));
	}
}
