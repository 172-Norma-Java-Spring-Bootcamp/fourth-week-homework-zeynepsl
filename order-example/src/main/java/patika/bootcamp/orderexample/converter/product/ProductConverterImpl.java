package patika.bootcamp.orderexample.converter.product;

import java.util.UUID;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.dto.product.CreateProductRequestDto;
import patika.bootcamp.orderexample.model.Brand;
import patika.bootcamp.orderexample.model.Category;
import patika.bootcamp.orderexample.model.Product;
import patika.bootcamp.orderexample.service.BrandService;
import patika.bootcamp.orderexample.service.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverterImpl implements ProductConverter{
	private final BrandService brandService;
	private final CategoryService categoryService;

	@Override
	public Product toProduct(CreateProductRequestDto productDto) {
		Product product = new  Product();
		product.setName(productDto.getName());
		product.setBarcode(UUID.randomUUID());
		product.setPrice(productDto.getPrice());
		product.setStockAmount(productDto.getStockAmount());
		
		Brand brand = brandService.get(productDto.getBrandId());
		product.setBrand(brand);
		
		Category category = categoryService.get(productDto.getCategoryId());
		product.setCategory(category);
		
		return product;
	}
	

}
