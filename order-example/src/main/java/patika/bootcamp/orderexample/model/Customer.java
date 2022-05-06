package patika.bootcamp.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
public class Customer extends BaseExtendedModel {

    private String username;
    private String email;
    private Long identity;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;
    private boolean isDeleted = false;
    private String phone;
    
    //şu an adres tablosunda customerId var
    //orphanremoval
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_address_id", referencedColumnName = "id")
    private CustomerAddress customerAddress;
    
    @OneToOne(mappedBy = "customer",orphanRemoval = true, cascade = CascadeType.ALL)
    private Basket basket;
    
    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, 
    		fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Order> orders = new ArrayList<Order>();
}
