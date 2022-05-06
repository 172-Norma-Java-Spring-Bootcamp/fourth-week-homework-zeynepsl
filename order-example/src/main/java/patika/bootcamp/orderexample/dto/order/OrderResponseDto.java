package patika.bootcamp.orderexample.dto.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
	private List<OrderItemDto> orderItems = new ArrayList<OrderItemDto>();

	private Long customerId;
	private String phone;
	
	private String shipName;
	private String shipAddress;
	private String billingAddress;
	private String city;
	private String country;
	
	private String zipCode;
	private Date date;
	private boolean shipped;
	
	private String cargoFirm;
	private String trackingNumber;

	private BigDecimal discountPrice;
	private Double taxPrice;
	private BigDecimal shippingPrice;
	private BigDecimal totalPrice;
	private BigDecimal totalCargoPrice;
}
