package patika.bootcamp.orderexample.validator;

import java.util.Objects;

import org.springframework.stereotype.Component;

import patika.bootcamp.orderexample.constant.validatorExceptionMessage.OrderValidatorMessage;
import patika.bootcamp.orderexample.dto.order.CreateOrderRequestDto;
import patika.bootcamp.orderexample.exception.BaseValidationException;
import patika.bootcamp.orderexample.exception.ControllerOperationException;

@Component
public class CreateOrderRequestValidator implements Validator<CreateOrderRequestDto>{
	@Override
	public void validate(CreateOrderRequestDto orderRequestDto) throws BaseValidationException {
		if (Objects.isNull(orderRequestDto)) {
            throw new ControllerOperationException.OrderNotValidException(OrderValidatorMessage.canNotNullOrEmpty);
        }
        if (Objects.isNull(orderRequestDto.getBillingAddress()) && Objects.isNull(orderRequestDto.getCargoFirm()) &&
        		Objects.isNull(orderRequestDto.getCity()) && Objects.isNull(orderRequestDto.getCountry()) && 
        		Objects.isNull(orderRequestDto.getShipAddress()) && Objects.isNull(orderRequestDto.getShipName()) &&
        		Objects.isNull(orderRequestDto.getZipCode()) && Objects.isNull(orderRequestDto.getBasketId()) && 
        		Objects.isNull(orderRequestDto.getCustomerId())) {
            throw new ControllerOperationException.OrderNotValidException(OrderValidatorMessage.fieldsCanNotNullOrEmpty);
        }
	}
}
