package patika.bootcamp.orderexample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.ProductExceptionMessage;
import patika.bootcamp.orderexample.converter.product.ProductConverter;
import patika.bootcamp.orderexample.dto.product.CreateProductRequestDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.ServiceOperationException;
import patika.bootcamp.orderexample.model.Product;
import patika.bootcamp.orderexample.repository.ProductRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
	private final ProductConverter productConverter;
	
	@Override
	public void create(CreateProductRequestDto createProductRequestDto) {
		Product product = productConverter.toProduct(createProductRequestDto);
		productRepository.save(product);
		log.info("brand -> {}", product.getBrand().getName());
	}

	@Override
	public Product getProduct(Long id) throws BaseException {
		Product product = productRepository
				.findById(id).
				orElseThrow(() -> new ServiceOperationException.ProductNotFoundException(ProductExceptionMessage.notFound));
		return product;
	}

	@Override
	public void delete(Long id) throws BaseException {
		Product product = productRepository
				.findById(id).
				orElseThrow(() -> new ServiceOperationException.ProductNotFoundException(ProductExceptionMessage.notFound));
		productRepository.delete(product);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

}
