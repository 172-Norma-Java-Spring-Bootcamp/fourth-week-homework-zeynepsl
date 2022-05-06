package patika.bootcamp.orderexample.converter.product;

import patika.bootcamp.orderexample.dto.product.CreateProductRequestDto;
import patika.bootcamp.orderexample.model.Product;

public interface ProductConverter {
	Product toProduct(CreateProductRequestDto createProductRequestDto);

}
