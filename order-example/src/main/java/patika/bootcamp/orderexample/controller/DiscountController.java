package patika.bootcamp.orderexample.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.dto.discount.CreateDiscountRequestDto;
import patika.bootcamp.orderexample.model.Discount;
import patika.bootcamp.orderexample.service.DiscountService;
import patika.bootcamp.orderexample.validator.Validator;

@RestController
@RequestMapping("api/discounts")
@RequiredArgsConstructor
public class DiscountController {
	private final DiscountService discountService;
	private final Validator<Long> idValidator;
	private final Validator<CreateDiscountRequestDto> createDiscountRequestDtoValidator;
	
	@PostMapping("/")
	public void create(@RequestBody CreateDiscountRequestDto createDiscountRequestDto) {
		createDiscountRequestDtoValidator.validate(createDiscountRequestDto);
		discountService.create(createDiscountRequestDto);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Discount>> getAll(){
		return ResponseEntity.ok(discountService.getAll());
	}
	
	@GetMapping("{code}")
	public ResponseEntity<Discount> getDiscountByCode(@PathVariable String code) {
		return ResponseEntity.ok(discountService.getDiscountByCode(code));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(Long id) {
		idValidator.validate(id);
		discountService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/disable/{id}")
	public void disableDiscount(@PathVariable Long id) {
		idValidator.validate(id);
		discountService.disableDiscount(id);
	}
	
	@GetMapping("/isStatus")
	public List<Discount> findByStatusTrue() {
		return discountService.findByStatusTrue();
	}
	
	@GetMapping("/isNotStatus")
	public List<Discount> findByStatusFalse() {
		return discountService.findByStatusFalse();
	}
}
