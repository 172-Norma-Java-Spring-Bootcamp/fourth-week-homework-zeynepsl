package patika.bootcamp.orderexample.validator.createRequestValidator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import patika.bootcamp.orderexample.dto.product.CreateProductRequestDto;
import patika.bootcamp.orderexample.exception.BaseValidationException;
import patika.bootcamp.orderexample.exception.ControllerOperationException;
import patika.bootcamp.orderexample.validator.Validator;

@Component
public class CreateProductRequestValidator implements Validator<CreateProductRequestDto>{

	@Override
	public void validate(CreateProductRequestDto input) throws BaseValidationException {
		if (input.equals(null)) {
            throw new ControllerOperationException.ProductNotValidException("Product can not be null or empty");
        }
        if (!StringUtils.hasLength(input.getName()) && !StringUtils.hasLength(input.getImage())) {
            throw new ControllerOperationException.ProductNotValidException("Name and image of Product can not be null or empty");
        }
        if (input.getPrice().equals(null)) {
            throw new ControllerOperationException.ProductNotValidException("Product price can not be null or empty");
        }
	}

}
