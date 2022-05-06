package patika.bootcamp.orderexample.converter.basket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.dto.basket.BasketDto;
import patika.bootcamp.orderexample.dto.basket.BasketItemDto;
import patika.bootcamp.orderexample.dto.basket.BasketResponseDto;
import patika.bootcamp.orderexample.model.Basket;
import patika.bootcamp.orderexample.service.CustomerService;
import patika.bootcamp.orderexample.service.DiscountService;

@Component
@RequiredArgsConstructor
public class BasketConverterImpl implements BasketConverter{
	private final CustomerService customerService;
	private final DiscountService discountService;
	
	@Override
	public Basket toBasket(BasketDto basketDto) {
		Basket basket = new Basket();
		basket.setCustomer(customerService.getCustomerAllInfo(basketDto.getCustomerId()));
		basket.setDiscount(discountService.getDiscount(basketDto.getDiscountId()));
		basket.setDiscountPrice(basketDto.getDiscountPrice());
		basket.setPrice(basketDto.getPrice());
		basket.setShippingPrice(basketDto.getShippingPrice());
		basket.setTaxPrice(basketDto.getTaxPrice());
		basket.setTotalPrice(basketDto.getTotalPrice());
		return basket;
	}

	@Override
	public BasketResponseDto toBasketResponseDto(Basket basket) {
		BasketResponseDto basketResponseDto = new BasketResponseDto();
		basketResponseDto.setCustomerId(basket.getCustomer().getId());
		basketResponseDto.setTotalPrice(basket.getTotalPrice());
		
		List<BasketItemDto> basketItemDtos = new ArrayList<BasketItemDto>();
		
		basket.getBasketItems().forEach(basketItem -> {
			BasketItemDto basketItemDto = new BasketItemDto();
			basketItemDto.setBasketId(basketItem.getBasket().getId());
			basketItemDto.setDiscountPrice(basketItem.getDiscountPrice());
			basketItemDto.setPrice(basketItem.getPrice());
			basketItemDto.setProductId(basketItem.getProduct().getId());
			basketItemDto.setQuantity(basketItem.getQuantity());
			basketItemDto.setShippingPrice(basketItem.getShippingPrice());
			basketItemDto.setTaxPrice(basketItem.getTaxPrice());
			basketItemDtos.add(basketItemDto);
		});
		basketResponseDto.setItems(basketItemDtos);
		basketResponseDto.setTotalNumberOfProducts(basketItemDtos.size());
		return basketResponseDto;
	}

	@Override
	public BasketDto toBasketDto(Basket basket) {
		BasketDto basketDto = new BasketDto();
		basketDto.setDiscountPrice(basket.getDiscountPrice());
		basketDto.setPrice(basket.getPrice());
		basketDto.setShippingPrice(basket.getShippingPrice());
		basketDto.setTaxPrice(basket.getTaxPrice());
		basketDto.setTotalPrice(basket.getTotalPrice());
		basketDto.setCustomerId(basket.getCustomer().getId());
		return basketDto;
	}
}
