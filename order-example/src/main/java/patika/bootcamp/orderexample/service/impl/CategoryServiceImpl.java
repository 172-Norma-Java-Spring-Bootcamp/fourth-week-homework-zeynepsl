package patika.bootcamp.orderexample.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.CategoryExceptionMessage;
import patika.bootcamp.orderexample.converter.category.CategoryConverter;
import patika.bootcamp.orderexample.dto.product.CategoryDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.ServiceOperationException;
import patika.bootcamp.orderexample.model.Category;
import patika.bootcamp.orderexample.repository.CategoryRepository;
import patika.bootcamp.orderexample.service.CategoryService;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
	private final CategoryRepository categoryRepository;
	private final CategoryConverter categoryConverter;

	@Override
	public void create(CategoryDto categoryDto) {
		Category category = categoryConverter.toCategory(categoryDto);
		categoryRepository.save(category);
	}

	@Override
	public Category get(Long id) throws BaseException{
		Category category = categoryRepository
				.findById(id)
				.orElseThrow(() -> new ServiceOperationException.CategoryNotFoundException(CategoryExceptionMessage.notFound));
		return category;
	}

	@Override
	public void delete(Long id) {
		Category category = categoryRepository
				.findById(id)
				.orElseThrow(() -> new ServiceOperationException.CategoryNotFoundException(CategoryExceptionMessage.notFound));
		categoryRepository.delete(category);
	}

	@Override
	public List<Category> getAll() {
		return categoryRepository.findAll();
	}

}
