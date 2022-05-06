package patika.bootcamp.orderexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patika.bootcamp.orderexample.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long>{
	Discount findByCode(String code);
	List<Discount> findByStatusTrue();
	List<Discount> findByStatusFalse();
}
