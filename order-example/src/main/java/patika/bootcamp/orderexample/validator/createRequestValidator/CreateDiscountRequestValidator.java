package patika.bootcamp.orderexample.validator.createRequestValidator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import patika.bootcamp.orderexample.constant.validatorExceptionMessage.DiscountValidatorMessage;
import patika.bootcamp.orderexample.dto.discount.CreateDiscountRequestDto;
import patika.bootcamp.orderexample.exception.BaseValidationException;
import patika.bootcamp.orderexample.exception.ControllerOperationException;
import patika.bootcamp.orderexample.validator.Validator;

@Component
public class CreateDiscountRequestValidator implements Validator<CreateDiscountRequestDto>{
	@Override
	public void validate(CreateDiscountRequestDto discountRequestDto) throws BaseValidationException {
		if (Objects.isNull(discountRequestDto)) {
            throw new ControllerOperationException.DiscountNotValidException(DiscountValidatorMessage.canNotNullOrEmpty);
        }
        if (Objects.isNull(discountRequestDto.getCode()) && Objects.isNull(discountRequestDto.getDiscountPercent())) {
            throw new ControllerOperationException.DiscountNotValidException(DiscountValidatorMessage.fieldsCanNotNullOrEmpty);
        }
	}
}
