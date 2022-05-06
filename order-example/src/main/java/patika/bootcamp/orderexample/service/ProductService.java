package patika.bootcamp.orderexample.service;

import java.util.List;

import patika.bootcamp.orderexample.dto.product.CreateProductRequestDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.model.Product;

public interface ProductService {
    void create(CreateProductRequestDto createProductRequestDto);

    Product getProduct(Long id) throws BaseException;

	void delete(Long id) throws BaseException;

	List<Product> getAll();
}
