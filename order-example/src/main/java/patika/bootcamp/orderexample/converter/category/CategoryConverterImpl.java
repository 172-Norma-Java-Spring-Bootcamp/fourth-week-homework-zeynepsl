package patika.bootcamp.orderexample.converter.category;

import org.springframework.stereotype.Component;

import patika.bootcamp.orderexample.dto.product.CategoryDto;
import patika.bootcamp.orderexample.model.Category;

@Component
public class CategoryConverterImpl implements CategoryConverter{

	@Override
	public Category toCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setName(categoryDto.getName());
		category.setParent(categoryDto.getParentCategory());
		
		return category;
	}

}
