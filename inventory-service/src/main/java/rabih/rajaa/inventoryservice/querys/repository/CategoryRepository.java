package rabih.rajaa.inventoryservice.querys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rabih.rajaa.inventoryservice.querys.entities.CategoryEntity;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}

