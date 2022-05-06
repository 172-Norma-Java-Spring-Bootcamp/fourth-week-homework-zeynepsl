package patika.bootcamp.orderexample.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.BasketExceptionMessage;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.OrderExceptionMessage;
import patika.bootcamp.orderexample.converter.order.OrderConverter;
import patika.bootcamp.orderexample.dto.order.CreateOrderRequestDto;
import patika.bootcamp.orderexample.dto.order.OrderResponseDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.OrderServiceOperationException;
import patika.bootcamp.orderexample.model.Basket;
import patika.bootcamp.orderexample.model.Customer;
import patika.bootcamp.orderexample.model.Discount;
import patika.bootcamp.orderexample.model.Order;
import patika.bootcamp.orderexample.model.OrderItem;
import patika.bootcamp.orderexample.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;
	private final CustomerService customerService;
	private final DiscountService discountService;
	private final OrderItemService orderItemService;
	private final BasketService basketService;
	private final OrderConverter orderConverter;

	@Override
	public OrderResponseDto create(CreateOrderRequestDto createOrderRequestDto) throws BaseException {
		Customer customer = customerService.getCustomerAllInfo(createOrderRequestDto.getCustomerId());
		Basket basket = basketService.get(createOrderRequestDto.getBasketId());// Basket basket = customer.getBasket();
		if (Objects.isNull(basket) || Objects.isNull(basket.getBasketItems())) {
			throw new OrderServiceOperationException.BasketIsNotValidForOrderException(BasketExceptionMessage.basketIsNotValid);
		}
		Order order = new Order();
		order.setCustomer(customer);

		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		order.setDate(date);
		
		order.setBillingAddress(createOrderRequestDto.getBillingAddress());
		order.setCargoFirm(createOrderRequestDto.getCargoFirm());
		order.setCity(createOrderRequestDto.getCity());
		order.setCountry(createOrderRequestDto.getCountry());
		order.setShipAddress(createOrderRequestDto.getShipAddress());
		order.setShipName(createOrderRequestDto.getShipName());
		order.setShipped(false);
		order.setTrackingNumber(UUID.randomUUID().toString());
		order.setZipCode(createOrderRequestDto.getZipCode());
		order.setPhone(customer.getPhone());
		
		basket = basketService.calculateTotalPriceOfBasket(basket);
		
		order.setTotalCargoPrice(basket.getShippingPrice());
		order.setTotalPrice(basket.getTotalPrice());
		order.setTaxPrice(basket.getTaxPrice());
		order.setShippingPrice(basket.getShippingPrice());
		
		//ilk siparis ise ve ilk siparis kuponu aktif ise:
		if (findByCustomer_Id(customer.getId()).isEmpty() && discountService.getDiscountByCode("ILK_SIPARIS").isStatus()) {
			Discount discount = discountService.getDiscountByCode("ILK_SIPARIS");
			order.getDiscounts().add(discount);
			orderRepository.save(order);
			
			BigDecimal totalPrice = discountService.applyDiscount(basket.getTotalPrice(), "ILK_SIPARIS");
			basket.setTotalPrice(totalPrice);
		}
		
		//toplam tutar 150 nin üstünde ise:
		if (basket.getTotalPrice().compareTo(BigDecimal.valueOf(150)) > 0 && discountService.getDiscountByCode("SEPET20").isStatus()) {
			Discount discount = discountService.getDiscountByCode("SEPET20");
			order.getDiscounts().add(discount);
			orderRepository.save(order);
			
			basket.setTotalPrice(discountService.applyDiscount(basket.getTotalPrice(), "SEPET20"));
		}

		orderRepository.save(order);
		basket.getBasketItems().forEach(basketItem -> {
			basketItem.getProduct().setSellCount(basketItem.getProduct().getSellCount() + basketItem.getQuantity());
			OrderItem orderItem = new OrderItem();
			orderItem.setQuantity(basketItem.getQuantity());
			orderItem.setOrder(order);
			orderItem.setProduct(basketItem.getProduct());
			order.getOrderItems().add(orderItem);
			orderItemService.create(orderItem);
		});
		
		basketService.emptyBasket(basket.getId());
		orderRepository.save(order);
		basketService.createWithBasket(basket);
		return orderConverter.toOrderResponseDto(order);
	}

	@Override
	public void delete(Long id) throws BaseException {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderServiceOperationException.OrderNotFound(OrderExceptionMessage.notFound));
		orderRepository.delete(order);
	}

	@Override
	public OrderResponseDto getOrder(Long id) throws BaseException {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderServiceOperationException.OrderNotFound(OrderExceptionMessage.notFound));
		return orderConverter.toOrderResponseDto(order);
	}

	@Override
	public List<OrderResponseDto> getAll() {
		List<OrderResponseDto> orderResponseDtos = new ArrayList<OrderResponseDto>();
		orderRepository.findAll().forEach(order -> {
			orderResponseDtos.add(orderConverter.toOrderResponseDto(order));
		});
		return orderResponseDtos;
	}

	@Override
	public List<Order> findByCustomer_Id(Long customerId) {
		List<Order> orders = null;
		orders = orderRepository.findByCustomer_Id(customerId);
		return orders;
	}

}