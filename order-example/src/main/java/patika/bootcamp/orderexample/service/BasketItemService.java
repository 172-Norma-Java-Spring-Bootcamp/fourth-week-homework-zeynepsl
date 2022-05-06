package patika.bootcamp.orderexample.service;

import java.util.List;

import patika.bootcamp.orderexample.dto.basket.BasketItemDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.model.BasketItem;

public interface BasketItemService {
	void create(BasketItemDto basketItemDto);
	
	void delete(Long id);
	
	void createWithBasketItem(BasketItem basketItem);

	BasketItem get(Long basketId) throws BaseException;
	
	List<BasketItem> findByBasket_Id(Long basketId) throws BaseException;
	
	void deleteById(Long id);
	
	void deleteBasketItemsWithIds(Long[] ids);
}
