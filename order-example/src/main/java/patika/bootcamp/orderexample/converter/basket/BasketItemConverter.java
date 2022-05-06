package patika.bootcamp.orderexample.converter.basket;

import patika.bootcamp.orderexample.dto.basket.BasketItemDto;
import patika.bootcamp.orderexample.model.BasketItem;

public interface BasketItemConverter {
	BasketItem toBasketItem(BasketItemDto basketItemDto);

	BasketItemDto toBasketItemDto(BasketItem basketItem);
}
