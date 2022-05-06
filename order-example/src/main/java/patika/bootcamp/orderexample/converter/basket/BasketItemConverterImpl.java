package patika.bootcamp.orderexample.converter.basket;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.dto.basket.BasketItemDto;
import patika.bootcamp.orderexample.model.BasketItem;
import patika.bootcamp.orderexample.service.ProductService;

@Component
@RequiredArgsConstructor
public class BasketItemConverterImpl implements BasketItemConverter{
	
	//private final BasketService basketService;
	private final ProductService productService;
	
	@Override
	public BasketItem toBasketItem(BasketItemDto basketItemDto) {
		BasketItem basketItem = new BasketItem();
		basketItem.setDiscountPrice(basketItem.getDiscountPrice());
		basketItem.setPrice(basketItem.getPrice());
		
		basketItem.setShippingPrice(basketItem.getShippingPrice());
		basketItem.setTaxPrice(basketItem.getTaxPrice());
		//basketItem.setBasket(basketItemDto.getBasketId());
		basketItem.setProduct(productService.getProduct(basketItemDto.getProductId()));
		
		return basketItem;
	}

	@Override
	public BasketItemDto toBasketItemDto(BasketItem basketItem) {
		BasketItemDto basketItemDto = new BasketItemDto();
		basketItemDto.setDiscountPrice(basketItem.getDiscountPrice());
		basketItemDto.setPrice(basketItem.getPrice());
		basketItemDto.setBasketId(basketItem.getBasket().getId());
		basketItemDto.setProductId(basketItem.getProduct().getId());
		basketItemDto.setQuantity(basketItem.getQuantity());
		basketItemDto.setShippingPrice(basketItem.getShippingPrice());
		basketItemDto.setTaxPrice(basketItem.getTaxPrice());
		return basketItemDto;
	}
}
