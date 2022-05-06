package patika.bootcamp.orderexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patika.bootcamp.orderexample.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long>{

}
