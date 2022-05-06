package patika.bootcamp.orderexample.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.BasketItemExceptionMessage;
import patika.bootcamp.orderexample.converter.basket.BasketItemConverter;
import patika.bootcamp.orderexample.dto.basket.BasketItemDto;
import patika.bootcamp.orderexample.exception.ServiceOperationException;
import patika.bootcamp.orderexample.model.BasketItem;
import patika.bootcamp.orderexample.repository.BasketItemRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketItemServiceImpl implements BasketItemService{

	private final BasketItemRepository basketItemRepository;
	private final BasketItemConverter basketItemConverter;
	
	@Override
	public void create(BasketItemDto basketItemDto) {
		BasketItem basketItem = basketItemConverter.toBasketItem(basketItemDto);
		
		basketItemRepository.save(basketItem);
		log.info("BasketItemServiceImpl -->{}", basketItem.getId());
	}
	
	@Override
	public BasketItem get(Long basketId) {
		BasketItem basketItem = basketItemRepository
				.findById(basketId)
				.orElseThrow(() -> new ServiceOperationException.BasketItemNotFoundException(BasketItemExceptionMessage.basketItemNotFound));
		return basketItem;
	}

	@Override
	public void createWithBasketItem(BasketItem basketItem) {
		basketItemRepository.save(basketItem);
	}

	@Override
	public List<BasketItem> findByBasket_Id(Long basketId) {
		List<BasketItem> basketItems = basketItemRepository.findByBasket_Id(basketId);
		if(basketItems.isEmpty()) {
			throw new ServiceOperationException.BasketItemNotFoundException(BasketItemExceptionMessage.basketItemNotFound);
		}
		return basketItems;
	}

	@Override
	public void delete(Long id) {
		BasketItem basketItem = basketItemRepository
				.findById(id)
				.orElseThrow(() -> new ServiceOperationException.BasketItemNotFoundException(BasketItemExceptionMessage.basketItemNotFound));
		log.info("delete basket item id-->{}", basketItem.getId());
		basketItemRepository.delete(basketItem);
		log.info("delete basket item id-->{}", basketItem.getId());
	}
	
	public void deleteAll(Long id) {
		//basketItemRepository.deleteAllById());
	}
	
	@Override
	public void deleteById(Long id) {
		basketItemRepository.deleteById(id);
	}
	
	@Transactional
	@Override
	public void deleteBasketItemsWithIds(Long[] ids) {
		basketItemRepository.deleteBasketItemsWithIds(Arrays.asList(ids));
	}

}
