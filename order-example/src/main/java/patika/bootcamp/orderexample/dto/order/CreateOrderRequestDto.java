package patika.bootcamp.orderexample.dto.order;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequestDto {
	private Long customerId;
	private Long basketId;
	private String shipName;
	private String shipAddress;
	private String billingAddress;
	private String city;
	private String country;
	private String zipCode;
	private String cargoFirm;
}
