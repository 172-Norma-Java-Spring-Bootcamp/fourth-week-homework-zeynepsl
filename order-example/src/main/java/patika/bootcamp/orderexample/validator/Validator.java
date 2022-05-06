package patika.bootcamp.orderexample.validator;

import patika.bootcamp.orderexample.exception.BaseValidationException;

public interface Validator<T> {
	void validate(T input) throws BaseValidationException;
}
