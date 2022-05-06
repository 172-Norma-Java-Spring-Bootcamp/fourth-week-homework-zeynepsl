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
import patika.bootcamp.orderexample.dto.product.CategoryDto;
import patika.bootcamp.orderexample.model.Category;
import patika.bootcamp.orderexample.service.CategoryService;
import patika.bootcamp.orderexample.validator.Validator;

@RestController
@RequestMapping("api/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;
	private final Validator<Long> idValidator;
	private final Validator<CategoryDto> categoryDtoValidator;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CategoryDto categoryDto) {
		categoryDtoValidator.validate(categoryDto);
		categoryService.create(categoryDto);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Category> get(@PathVariable Long id) {
		idValidator.validate(id);
		return ResponseEntity.ok(categoryService.get(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		idValidator.validate(id);
		categoryService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(categoryService.getAll());
	}
}
