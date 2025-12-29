package rabih.rajaa.orderservice.querys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rabih.rajaa.orderservice.querys.entities.OrderEntity;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    List<OrderEntity> findByCustomerId(String customerId);
    List<OrderEntity> findByStatus(String status);
}


