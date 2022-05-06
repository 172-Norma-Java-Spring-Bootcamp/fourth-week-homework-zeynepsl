package patika.bootcamp.orderexample.controller;

import lombok.RequiredArgsConstructor;
import patika.bootcamp.orderexample.dto.customer.CreateCustomerRequestDTO;
import patika.bootcamp.orderexample.dto.customer.CustomerResponseDto;
import patika.bootcamp.orderexample.service.CustomerService;
import patika.bootcamp.orderexample.validator.Validator;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private final Validator<CreateCustomerRequestDTO> createCustomerRequestValidator;
    private final Validator<Long> idValidator;
    private final CustomerService customerService;


    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody CreateCustomerRequestDTO customerDTO) {
    	createCustomerRequestValidator.validate(customerDTO);
        customerService.create(customerDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateCustomerRequestDTO> getCustomer(@PathVariable Long id) {
    	idValidator.validate(id);
        return ResponseEntity.ok(customerService.getCustomer(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestParam Boolean deleteIsHard){
    	idValidator.validate(id);
    	customerService.delete(id, deleteIsHard);
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/")
    public ResponseEntity<List<CustomerResponseDto>> getAll(){
    	return ResponseEntity.ok(customerService.getAll());
    }
}
