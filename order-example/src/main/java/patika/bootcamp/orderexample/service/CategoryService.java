package patika.bootcamp.orderexample.service;

import java.util.List;

import patika.bootcamp.orderexample.dto.product.CategoryDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.model.Category;

public interface CategoryService{
	void create(CategoryDto categoryDto);
	Category get(Long id) throws BaseException;
	void delete(Long id) throws BaseException;
	List<Category> getAll();
}
