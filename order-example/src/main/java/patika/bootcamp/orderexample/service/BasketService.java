package patika.bootcamp.orderexample.service;

import patika.bootcamp.orderexample.dto.basket.BasketDto;
import patika.bootcamp.orderexample.dto.basket.BasketResponseDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.model.Basket;
import patika.bootcamp.orderexample.model.Customer;

public interface BasketService {
	void createWithBasketDto(BasketDto basketDto);
	
	public Basket createWithCustomer(Customer customer);
	
	public void createWithBasket(Basket basket);

	Basket get(Long basketId);
	
	public BasketResponseDto addProductToBasket(Long productId, Long customerId, Integer amount) throws BaseException;
	
	public void removeProductToBasket(Long productId, Long basketId) throws BaseException;
	
	public Basket getByCustomerId(Long customerId) throws BaseException;
	
	void emptyBasket(Long basketId);
	
	public Basket calculateTotalPriceOfBasket(Basket basket);
}
