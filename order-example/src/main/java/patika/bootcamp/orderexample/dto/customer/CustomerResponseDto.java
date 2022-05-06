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
public class CustomerResponseDto {
	private Long id;
	private String username;
    private String email;
    private Gender gender;
    private CustomerAdressDTO customerAdress;
    private String phone;
}
