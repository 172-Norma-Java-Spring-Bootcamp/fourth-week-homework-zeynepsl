package patika.bootcamp.orderexample.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.BrandExceptionMessage;
import patika.bootcamp.orderexample.converter.brand.BrandConverter;
import patika.bootcamp.orderexample.dto.product.BrandDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.ServiceOperationException;
import patika.bootcamp.orderexample.model.Brand;
import patika.bootcamp.orderexample.repository.BrandRepository;
import patika.bootcamp.orderexample.service.BrandService;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
	private final BrandRepository brandRepository;
	private final BrandConverter brandConverter;
	
	@Override
	public void create(BrandDto brandDto) {
		Brand brand = brandConverter.toBrand(brandDto);
		brandRepository.save(brand);
	}

	@Override
	public Brand get(Long id) throws BaseException {
		Brand brand = brandRepository
				.findById(id)
				.orElseThrow(() -> new ServiceOperationException.BrandNotFoundException(BrandExceptionMessage.notFound));
		return brand;
	}

	@Override
	public void delete(Long id) throws BaseException {
		Brand brand = brandRepository
				.findById(id)
				.orElseThrow(() -> new ServiceOperationException.BrandNotFoundException(BrandExceptionMessage.notFound));
		brandRepository.delete(brand);
	}

	@Override
	public List<Brand> getAll() {
		return brandRepository.findAll();
	}

}
