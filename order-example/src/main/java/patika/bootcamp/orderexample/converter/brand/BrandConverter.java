package patika.bootcamp.orderexample.converter.brand;

import patika.bootcamp.orderexample.dto.product.BrandDto;
import patika.bootcamp.orderexample.model.Brand;

public interface BrandConverter {
	Brand toBrand(BrandDto brandDto);
}
