package patika.bootcamp.orderexample.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import patika.bootcamp.orderexample.constant.serviceExceptionMessage.CustomerExceptionMessage;
import patika.bootcamp.orderexample.converter.customer.CustomerConverter;
import patika.bootcamp.orderexample.dto.customer.CreateCustomerRequestDTO;
import patika.bootcamp.orderexample.dto.customer.CustomerResponseDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.exception.CustomerServiceOperationException;
import patika.bootcamp.orderexample.model.Customer;
import patika.bootcamp.orderexample.repository.CustomerRepository;
import patika.bootcamp.orderexample.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	private final CustomerConverter customerConverter;
	private final CustomerRepository customerRepository;

	@Override
	public void create(CreateCustomerRequestDTO customerDTO) {
		Customer customer = customerConverter.toCustomer(customerDTO);
		log.info("Customer ID should be null -> {}", customer.getId());
		customerRepository.save(customer);
		log.info("Customer ID should not be null -> {}", customer.getId());
		log.info("adres->{}", customer.getCustomerAddress().getId());

		// customer.setGender(Gender.OTHER);
		// customerRepository.save(customer);
	}

	@Override
	public CreateCustomerRequestDTO getCustomer(Long id) throws BaseException{
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFoundException(CustomerExceptionMessage.notFound));
		if (customer.isDeleted()) {
			throw new CustomerServiceOperationException.CustomerAlreadyDeletedException(CustomerExceptionMessage.notFoundAlreadyDeleted);
		}
		return customerConverter.toCreateCustomerRequestDTO(customer);
	}
	
	@Override
	public Customer getCustomerAllInfo(Long id) throws BaseException{
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFoundException(CustomerExceptionMessage.notFound));
		if (customer.isDeleted()) {
			throw new CustomerServiceOperationException.CustomerAlreadyDeletedException(CustomerExceptionMessage.notFoundAlreadyDeleted);
		}
		return customer;
	}

	@Override
	public void delete(Long id, boolean deleteIsHard) throws BaseException {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerServiceOperationException.CustomerNotFoundException(CustomerExceptionMessage.notFound));
		if (customer.isDeleted()) {
			throw new CustomerServiceOperationException.CustomerAlreadyDeletedException(CustomerExceptionMessage.notFoundAlreadyDeleted);
		}
		if (deleteIsHard) {
			customerRepository.delete(customer);
			return;
		}
		customer.setDeleted(true);
		customerRepository.save(customer);
	}

	@Override
	public List<CustomerResponseDto> getAll() {
		List<CustomerResponseDto> customerResponseDtos = new ArrayList<CustomerResponseDto>();
		for (Customer customer : customerRepository.findAllByIsDeleted(false)) {
			customerResponseDtos.add(customerConverter.toGetCustomerResponseDto(customer));
		}
		return customerResponseDtos;
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);
	}
}
