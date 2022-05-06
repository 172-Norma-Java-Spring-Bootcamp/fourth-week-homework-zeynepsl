package patika.bootcamp.orderexample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.orderexample.dto.basket.BasketDto;
import patika.bootcamp.orderexample.dto.basket.BasketResponseDto;
import patika.bootcamp.orderexample.model.Basket;
import patika.bootcamp.orderexample.service.BasketService;
import patika.bootcamp.orderexample.validator.Validator;

@RestController
@RequestMapping("api/baskets")
@RequiredArgsConstructor
@Slf4j
public class BasketController {
	private final BasketService basketService;
	private final Validator<Long> idValidator;
	private final Validator<BasketDto> basketDtoValidator;
	
	@PostMapping("/")
	public BasketResponseDto addProductToBasket(@RequestParam Long productId, 
			@RequestParam Long customerId, @RequestParam Integer amount) {
		log.info("iceri giriliyor...");
		idValidator.validate(customerId);
		idValidator.validate(productId);
		return basketService.addProductToBasket(productId, customerId, amount);
	}
	
	@PostMapping("/remove")
	public void removeProductToBasket(@RequestParam Long productId, @RequestParam Long basketId) {
		idValidator.validate(basketId);
		idValidator.validate(productId);
		basketService.removeProductToBasket(productId, basketId);
	}
	
	@GetMapping("{customerId}")
	public ResponseEntity<Basket> getByCustomerId(Long customerId) {
		idValidator.validate(customerId);
		return ResponseEntity.ok(basketService.getByCustomerId(customerId));
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody BasketDto basketDto){
		idValidator.validate(basketDto.getCustomerId());
		idValidator.validate(basketDto.getDiscountId());
		basketDtoValidator.validate(basketDto);
		basketService.createWithBasketDto(basketDto);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{basketId}")
	public void emptyBasket(@PathVariable Long basketId) {
		idValidator.validate(basketId);
		basketService.emptyBasket(basketId);
	}
	
}
