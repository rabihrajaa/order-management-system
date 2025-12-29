package rabih.rajaa.inventoryservice.querys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rabih.rajaa.inventoryservice.querys.entities.ProductEntity;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {
    List<ProductEntity> findByCategoryId(String categoryId);
    List<ProductEntity> findByStatus(String status);
}

