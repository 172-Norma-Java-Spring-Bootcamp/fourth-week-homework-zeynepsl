package patika.bootcamp.orderexample.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import patika.bootcamp.orderexample.dto.customer.CreateCustomerRequestDTO;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.ControllerOperationException;

import java.util.Objects;

@Component
public class CreateCustomerRequestValidator implements Validator<CreateCustomerRequestDTO> {
    @Override
    public void validate(CreateCustomerRequestDTO customerDTO) throws BaseException {
        // fail first approach.
        if (Objects.isNull(customerDTO)) {
            throw new ControllerOperationException.CustomerNotValidException("Customer can not be null or empty");
        }
        if (!(StringUtils.hasLength(customerDTO.getUserName()))) {
            throw new ControllerOperationException.CustomerNotValidException("Customer username can not be null or empty");
        }
        if (!(StringUtils.hasLength(customerDTO.getPassword()))) {
            throw new ControllerOperationException.CustomerNotValidException("Customer password can not be null or empty");
        }
        if (Objects.isNull(customerDTO.getIdentity())) {
            throw new ControllerOperationException.CustomerNotValidException("Customer identity can not be null or empty");
        }
        if (Objects.isNull(customerDTO.getGender())) {
            throw new ControllerOperationException.CustomerNotValidException("Customer gender can not be null or empty");
        }
        // customer address should validate

    }
}
