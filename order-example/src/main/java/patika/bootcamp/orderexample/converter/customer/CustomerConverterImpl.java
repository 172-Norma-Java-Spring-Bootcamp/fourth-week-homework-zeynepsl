package patika.bootcamp.orderexample.converter.customer;

import org.springframework.stereotype.Component;

import patika.bootcamp.orderexample.dto.customer.CreateCustomerRequestDTO;
import patika.bootcamp.orderexample.dto.customer.CustomerAdressDTO;
import patika.bootcamp.orderexample.dto.customer.CustomerResponseDto;
import patika.bootcamp.orderexample.model.Customer;
import patika.bootcamp.orderexample.model.CustomerAddress;

import java.util.Date;

@Component
public class CustomerConverterImpl implements CustomerConverter {

    @Override
    public Customer toCustomer(CreateCustomerRequestDTO customerDTO) {

        Customer customer = new Customer();
        customer.setUsername(customerDTO.getUserName());
        customer.setEmail(customerDTO.getEmail());
        customer.setIdentity(customerDTO.getIdentity());
        customer.setGender(customerDTO.getGender());
        customer.setPassword(customerDTO.getPassword());
        customer.setCreatedAt(new Date());
        customer.setCreatedBy("BaranBuyuk");
        customer.setCreatedAt(new Date());

        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setPhoneNumber(customerDTO.getCustomerAdress().getPhoneNumber());
        customerAddress.setCountry(customerDTO.getCustomerAdress().getCountry());
        customerAddress.setCity(customerDTO.getCustomerAdress().getCity());
        customerAddress.setPostalCode(customerDTO.getCustomerAdress().getPostalCode());
        customerAddress.setDescription(customerDTO.getCustomerAdress().getDescription());

        customer.setCustomerAddress(customerAddress);

        return customer;
    }

    @Override
    public CreateCustomerRequestDTO toCreateCustomerRequestDTO(Customer customer) {
        return new CreateCustomerRequestDTO(customer.getUsername(),
                customer.getEmail(),
                customer.getIdentity(),
                customer.getGender(),
                customer.getPassword(),
                convertCustomerAddressDto(customer.getCustomerAddress()),
                customer.getPhone());

    }

    private CustomerAdressDTO convertCustomerAddressDto(CustomerAddress customerAddress) {
        return new CustomerAdressDTO(customerAddress.getPhoneNumber(),
                customerAddress.getCountry(),
                customerAddress.getCity(),
                customerAddress.getPostalCode(),
                customerAddress.getDescription());
    }

	@Override
	public CustomerResponseDto toGetCustomerResponseDto(Customer customer) {
		return new CustomerResponseDto(customer.getId(),
				customer.getUsername(), 
				customer.getEmail(), 
				customer.getGender(), 
				convertCustomerAddressDto(customer.getCustomerAddress()),
				customer.getPhone());
	}
}
