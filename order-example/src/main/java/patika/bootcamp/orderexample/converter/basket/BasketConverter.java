package patika.bootcamp.orderexample.converter.basket;

import patika.bootcamp.orderexample.dto.basket.BasketDto;
import patika.bootcamp.orderexample.dto.basket.BasketResponseDto;
import patika.bootcamp.orderexample.model.Basket;

public interface BasketConverter {
	Basket toBasket(BasketDto basketDto);
	BasketResponseDto toBasketResponseDto(Basket basket);
	BasketDto toBasketDto(Basket basket);
}
