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
import patika.bootcamp.orderexample.dto.product.CreateProductRequestDto;
import patika.bootcamp.orderexample.model.Product;
import patika.bootcamp.orderexample.service.ProductService;
import patika.bootcamp.orderexample.validator.Validator;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	private final Validator<CreateProductRequestDto> createProductRequestValidator;
	private final Validator<Long> idValidator;
	
	@PostMapping("/")
    public ResponseEntity<?> create(@RequestBody CreateProductRequestDto productDto) {
		createProductRequestValidator.validate(productDto);
        productService.create(productDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    	idValidator.validate(id);
        return ResponseEntity.ok(productService.getProduct(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
    	idValidator.validate(id);
    	productService.delete(id);
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Product>> getAll(){
    	return ResponseEntity.ok(productService.getAll());
    }
}
