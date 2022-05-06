package patika.bootcamp.orderexample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import patika.bootcamp.orderexample.model.BasketItem;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long>{
	List<BasketItem> findByBasket_Id(Long basketId);
	
	void deleteById(Long id);
	
	@Modifying
	@Query("delete from BasketItem u where u.id in ?1")
	void deleteBasketItemsWithIds(List<Long> ids);
}
