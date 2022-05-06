package patika.bootcamp.orderexample.converter.discount;

import org.springframework.stereotype.Component;

import patika.bootcamp.orderexample.dto.discount.CreateDiscountRequestDto;
import patika.bootcamp.orderexample.model.Discount;

@Component
public class DiscountConverterImpl implements DiscountConverter{
	@Override
	public Discount toDiscount(CreateDiscountRequestDto createDiscountRequestDto) {
		Discount discount = new Discount();
		discount.setCode(createDiscountRequestDto.getCode());
		discount.setDiscountPercent(createDiscountRequestDto.getDiscountPercent());
		discount.setStatus(createDiscountRequestDto.isStatus());
		return discount;
	}
}
