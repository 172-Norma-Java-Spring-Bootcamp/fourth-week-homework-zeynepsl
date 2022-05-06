package patika.bootcamp.orderexample.converter.category;

import patika.bootcamp.orderexample.dto.product.CategoryDto;
import patika.bootcamp.orderexample.model.Category;

public interface CategoryConverter {
	Category toCategory(CategoryDto categoryDto);
}
