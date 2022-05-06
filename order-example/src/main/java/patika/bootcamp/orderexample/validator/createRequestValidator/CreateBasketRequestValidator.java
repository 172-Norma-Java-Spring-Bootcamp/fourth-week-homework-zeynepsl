package patika.bootcamp.orderexample.validator.createRequestValidator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import patika.bootcamp.orderexample.constant.validatorExceptionMessage.BasketValidatorMessage;
import patika.bootcamp.orderexample.dto.basket.BasketDto;
import patika.bootcamp.orderexample.exception.BaseValidationException;
import patika.bootcamp.orderexample.exception.ControllerOperationException;
import patika.bootcamp.orderexample.validator.Validator;

@Component
public class CreateBasketRequestValidator implements Validator<BasketDto>{
	@Override
	public void validate(BasketDto basketDto) throws BaseValidationException {
		if (Objects.isNull(basketDto)) {
            throw new ControllerOperationException.BasketNotValidException(BasketValidatorMessage.canNotNullOrEmpty);
        }
        if (Objects.isNull(basketDto.getDiscountPrice()) && Objects.isNull(basketDto.getPrice()) &&
        		Objects.isNull(basketDto.getShippingPrice()) && Objects.isNull(basketDto.getTaxPrice()) &&
        		Objects.isNull(basketDto.getTotalPrice())) {
            throw new ControllerOperationException.BasketNotValidException(BasketValidatorMessage.fieldsCanNotNullOrEmpty);
        }
       
		
	}
}
