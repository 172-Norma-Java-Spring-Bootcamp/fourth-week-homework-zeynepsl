package patika.bootcamp.orderexample.validator;

import org.springframework.stereotype.Component;

import patika.bootcamp.orderexample.constant.validatorExceptionMessage.IdValidatorMessage;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.ControllerOperationException;

@Component
public class EntityIdValidator implements Validator<Long>{

	@Override
	public void validate(Long id) throws BaseException {
		if(id < 0) {
    		throw new ControllerOperationException.IDNotValidException(IdValidatorMessage.idNotValid);
    	}
	}

}
