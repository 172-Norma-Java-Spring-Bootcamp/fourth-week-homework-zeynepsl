package patika.bootcamp.orderexample.service;

import java.util.List;

import patika.bootcamp.orderexample.dto.product.BrandDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.model.Brand;

public interface BrandService {
	void create(BrandDto brandDto);
	Brand get(Long id) throws BaseException ;
	void delete(Long id) throws BaseException ;
	List<Brand> getAll();
}
