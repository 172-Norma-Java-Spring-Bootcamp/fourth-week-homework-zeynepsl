package patika.bootcamp.orderexample.validator.createRequestValidator;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import patika.bootcamp.orderexample.constant.validatorExceptionMessage.CategoryValidatorMessage;
import patika.bootcamp.orderexample.dto.product.CategoryDto;
import patika.bootcamp.orderexample.exception.BaseValidationException;
import patika.bootcamp.orderexample.exception.ControllerOperationException;
import patika.bootcamp.orderexample.validator.Validator;

@Component
public class CreateCategoryRequestValidator implements Validator<CategoryDto> {
	@Override
	public void validate(CategoryDto categoryDto) throws BaseValidationException {
		if (Objects.isNull(categoryDto)) {
			throw new ControllerOperationException.CategoryNotValidException(CategoryValidatorMessage.canNotNullOrEmpty);
		}
		if (!(StringUtils.hasLength(categoryDto.getName()))) {
			throw new ControllerOperationException.CategoryNotValidException(CategoryValidatorMessage.fieldsCanNotNullOrEmpty);
		}
	}
}
