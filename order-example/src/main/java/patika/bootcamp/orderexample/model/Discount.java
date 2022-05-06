package patika.bootcamp.orderexample.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Discount extends BaseModel{
	
	@JsonIgnore
	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, 
    		fetch = FetchType.LAZY)
	private List<Basket> baskets = new ArrayList<Basket>();
	
	/*@JsonIgnore
	@OneToMany(mappedBy = "discount", cascade = CascadeType.ALL, 
    		fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Order> orders = new ArrayList<Order>();*/
	
	@ManyToMany(mappedBy = "discounts")
	private List<Order> orders = new ArrayList<Order>();
	
    private String code;

    private Double discountPercent;

    private boolean status;
}
