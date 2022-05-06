package patika.bootcamp.orderexample.service;

import java.util.List;

import patika.bootcamp.orderexample.dto.customer.CreateCustomerRequestDTO;
import patika.bootcamp.orderexample.dto.customer.CustomerResponseDto;
import patika.bootcamp.orderexample.exception.BaseException;
import patika.bootcamp.orderexample.model.Customer;

public interface CustomerService {
    void create(CreateCustomerRequestDTO customerDTO);
    
    void save(Customer customer);

    CreateCustomerRequestDTO getCustomer(Long id) throws BaseException;
    
    Customer getCustomerAllInfo(Long id) throws BaseException;

	void delete(Long id, boolean deleteIsHard) throws BaseException;

	List<CustomerResponseDto> getAll();
}
