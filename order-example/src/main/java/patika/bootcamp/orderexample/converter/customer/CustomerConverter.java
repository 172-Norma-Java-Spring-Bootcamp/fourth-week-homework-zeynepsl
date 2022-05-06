package patika.bootcamp.orderexample.converter.customer;

import patika.bootcamp.orderexample.dto.customer.CreateCustomerRequestDTO;
import patika.bootcamp.orderexample.dto.customer.CustomerResponseDto;
import patika.bootcamp.orderexample.model.Customer;

public interface CustomerConverter {

    Customer toCustomer(CreateCustomerRequestDTO customerDTO);

    CreateCustomerRequestDTO toCreateCustomerRequestDTO(Customer customer);
    
    CustomerResponseDto toGetCustomerResponseDto(Customer customer);
 }
