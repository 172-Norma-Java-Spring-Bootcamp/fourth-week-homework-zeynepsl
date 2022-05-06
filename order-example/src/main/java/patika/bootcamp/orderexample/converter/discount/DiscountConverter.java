package patika.bootcamp.orderexample.converter.discount;

import patika.bootcamp.orderexample.dto.discount.CreateDiscountRequestDto;
import patika.bootcamp.orderexample.model.Discount;

public interface DiscountConverter {
	Discount toDiscount(CreateDiscountRequestDto createDiscountRequestDto);
}
