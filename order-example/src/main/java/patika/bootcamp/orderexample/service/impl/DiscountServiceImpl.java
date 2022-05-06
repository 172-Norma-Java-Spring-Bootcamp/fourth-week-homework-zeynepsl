package patika.bootcamp.orderexample.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.DiscountExceptionMessage;
import patika.bootcamp.orderexample.converter.discount.DiscountConverter;
import patika.bootcamp.orderexample.dto.discount.CreateDiscountRequestDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.ServiceOperationException;
import patika.bootcamp.orderexample.model.Discount;
import patika.bootcamp.orderexample.repository.DiscountRepository;
import patika.bootcamp.orderexample.service.DiscountService;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountServiceImpl implements DiscountService {

	private final DiscountRepository discountRepository;
	private final DiscountConverter discountConverter;

	@Override
	public void create(CreateDiscountRequestDto createDiscountRequestDto) {
		discountRepository.save(discountConverter.toDiscount(createDiscountRequestDto));
	}

	@Override
	public void delete(Long id) throws BaseException {
		Discount discount = discountRepository.findById(id)
				.orElseThrow(() -> new ServiceOperationException.DiscountNotFoundException(DiscountExceptionMessage.notFound));
		discountRepository.delete(discount);
	}

	@Override
	public Discount getDiscount(Long id) {
		Discount discount = discountRepository.findById(id)
				.orElseThrow(() -> new ServiceOperationException.DiscountNotFoundException(DiscountExceptionMessage.notFound));
		return discount;
	}

	@Override
	public List<Discount> getAll() {
		return discountRepository.findAll();
	}

	@Override
	public Discount getDiscountByCode(String code) throws BaseException {
		Discount discount = discountRepository.findByCode(code);
		if (discount == null) {
			throw new ServiceOperationException.DiscountNotFoundException(DiscountExceptionMessage.notFound);
		}
		return discount;
	}
	
	@Override
	public BigDecimal applyDiscount(BigDecimal totalPrice, String discountCode) {
		Discount discount = getDiscountByCode(discountCode);
		BigDecimal percent = BigDecimal.valueOf(discount.getDiscountPercent()).divide(BigDecimal.valueOf(100));
		log.info("indirim kodu --> {}", discountCode);
		return totalPrice.subtract(percent.multiply(totalPrice));
	}
	
	@Override
	public void disableDiscount(Long id) {
		Discount discount = discountRepository
				.findById(id)
				.orElseThrow(() -> new ServiceOperationException.DiscountNotFoundException(DiscountExceptionMessage.notFound));
	    if(!discount.isStatus()) {
	    	throw new ServiceOperationException.DiscountAlreadyPassived(DiscountExceptionMessage.alreadyPassived);
	    }
	    discount.setStatus(false);
	    discountRepository.save(discount);
	}
	
	@Override
	public List<Discount> findByStatusTrue() {
		return discountRepository.findByStatusTrue();
	}
	
	@Override
	public List<Discount> findByStatusFalse() {
		return discountRepository.findByStatusFalse();
	}

}
