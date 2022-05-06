package patika.bootcamp.orderexample.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAdressDTO {
	private String phoneNumber;
    private String country;
    private String city;
    private String postalCode;
    private String description;
}
