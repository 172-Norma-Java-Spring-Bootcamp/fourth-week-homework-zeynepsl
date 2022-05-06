package patika.bootcamp.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class CustomerAddress extends BaseModel {

    private String phoneNumber;
    private String country;
    private String city;
    private String postalCode;
    private String description;
    
    //cascade = CascadeType.ALL, orphanRemoval = true
    @OneToOne(mappedBy = "customerAddress")
    private Customer customer;
 
}
