package patika.bootcamp.orderexample.converter.brand;

import org.springframework.stereotype.Component;

import patika.bootcamp.orderexample.dto.product.BrandDto;
import patika.bootcamp.orderexample.model.Brand;

@Component
public class BrandConverterImpl implements BrandConverter{

	@Override
	public Brand toBrand(BrandDto brandDto) {
		Brand brand = new Brand();
		brand.setName(brandDto.getName());
		return brand;
	}

}
