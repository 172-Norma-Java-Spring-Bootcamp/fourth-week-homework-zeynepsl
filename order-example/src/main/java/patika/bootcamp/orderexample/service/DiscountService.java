package patika.bootcamp.orderexample.service;

import java.math.BigDecimal;
import java.util.List;

import patika.bootcamp.orderexample.dto.discount.CreateDiscountRequestDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.model.Discount;

public interface DiscountService {
	void create(CreateDiscountRequestDto createDiscountRequestDto);
	void delete(Long id) throws BaseException;
	Discount getDiscount(Long id) throws BaseException;
	Discount getDiscountByCode(String code) throws BaseException;
	List<Discount> getAll();
	BigDecimal applyDiscount(BigDecimal totalPrice, String discountCode);
	void disableDiscount(Long id);
	List<Discount> findByStatusTrue();
	List<Discount> findByStatusFalse();
}
