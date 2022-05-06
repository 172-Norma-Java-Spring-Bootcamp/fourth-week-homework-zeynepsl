package patika.bootcamp.orderexample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import patika.bootcamp.orderexample.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
