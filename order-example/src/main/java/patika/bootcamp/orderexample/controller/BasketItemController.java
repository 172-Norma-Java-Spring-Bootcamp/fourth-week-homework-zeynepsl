package patika.bootcamp.orderexample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.model.BasketItem;
import patika.bootcamp.orderexample.service.BasketItemService;
import patika.bootcamp.orderexample.validator.Validator;

@RestController
@RequestMapping("api/basketItems")
@RequiredArgsConstructor
public class BasketItemController {
	private final BasketItemService basketItemService;
	private final Validator<Long> idValidator;
	
	@GetMapping("/{basketId}")
	public List<BasketItem> findByBasket_Id(@PathVariable Long basketId) {
		idValidator.validate(basketId);
		return basketItemService.findByBasket_Id(basketId);
	}
	
	@GetMapping("/")
	public BasketItem get(@RequestParam Long basketItemId) {
		idValidator.validate(basketItemId);
		return basketItemService.get(basketItemId);
	}
	
	@DeleteMapping("/{id}")
	public void delete(Long id) {
		idValidator.validate(id);
		basketItemService.delete(id);
	}
	
}
