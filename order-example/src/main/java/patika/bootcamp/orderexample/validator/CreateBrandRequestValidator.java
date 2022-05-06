package patika.bootcamp.orderexample.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import patika.bootcamp.orderexample.constant.validatorExceptionMessage.BrandValidatorMessage;
import patika.bootcamp.orderexample.dto.product.BrandDto;
import patika.bootcamp.orderexample.exception.BaseValidationException;
import patika.bootcamp.orderexample.exception.ControllerOperationException;

@Component
public class CreateBrandRequestValidator implements Validator<BrandDto>{
	@Override
	public void validate(BrandDto brandDto) throws BaseValidationException {
		if (Objects.isNull(brandDto)) {
			throw new ControllerOperationException.BrandNotValidException(BrandValidatorMessage.canNotNullOrEmpty);
		}
		if (!(StringUtils.hasLength(brandDto.getName()))) {
			throw new ControllerOperationException.BrandNotValidException(BrandValidatorMessage.fieldsCanNotNullOrEmpty);
		}
	}
}
