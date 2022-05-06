package patika.bootcamp.orderexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patika.bootcamp.orderexample.model.Basket;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long>{
	Basket findByCustomer_Id(Long customerId);
	
}
