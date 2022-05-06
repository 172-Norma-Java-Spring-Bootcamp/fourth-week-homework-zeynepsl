package patika.bootcamp.orderexample.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.dto.product.BrandDto;
import patika.bootcamp.orderexample.model.Brand;
import patika.bootcamp.orderexample.service.BrandService;
import patika.bootcamp.orderexample.validator.Validator;

@RestController
@RequestMapping("api/brands")
@RequiredArgsConstructor
public class BrandController {
	private final BrandService brandService;
	private final Validator<Long> idValidator;
	private final Validator<BrandDto> brandDtoValidator;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody BrandDto brandDto) {
		brandDtoValidator.validate(brandDto);
		brandService.create(brandDto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Brand> get(@PathVariable Long id) {
		idValidator.validate(id);
		return ResponseEntity.ok(brandService.get(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		idValidator.validate(id);
		brandService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Brand>> getAll() {
		return ResponseEntity.ok(brandService.getAll());
	}
}
