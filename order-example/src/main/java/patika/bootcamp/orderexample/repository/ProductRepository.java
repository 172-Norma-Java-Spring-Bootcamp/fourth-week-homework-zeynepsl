package patika.bootcamp.orderexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patika.bootcamp.orderexample.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
