package patika.bootcamp.orderexample.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseModel{
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	//iliskide sahip olan taraf:
	@ManyToMany
	@JoinTable(
			name = "order_discounts", 
			joinColumns = @JoinColumn(referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(referencedColumnName = "id"))
	private List<Discount> discounts = new ArrayList<Discount>();
	
	private String shipName;
    private String shipAddress;
    private String billingAddress;
    private String city;
    private String country;
    private String zipCode;
    private String phone;
    private BigDecimal totalPrice;
    private BigDecimal totalCargoPrice;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    private boolean shipped;
    private String cargoFirm;
    private String trackingNumber;
    
    private BigDecimal discountPrice = BigDecimal.ZERO;
    private Double taxPrice = 0.0;
    private BigDecimal shippingPrice = BigDecimal.ZERO;

}
