package patika.bootcamp.orderexample.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import patika.bootcamp.orderexample.model.Gender;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequestDTO {
	private String userName;
	private String email;
	private Long identity;
	private Gender gender;
	private String password;
	private CustomerAdressDTO customerAdress;
	private String phone;

}
