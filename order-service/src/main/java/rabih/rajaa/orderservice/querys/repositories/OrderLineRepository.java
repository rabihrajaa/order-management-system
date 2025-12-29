package rabih.rajaa.orderservice.querys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rabih.rajaa.orderservice.querys.entities.OrderLineEntity;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Long> {
    List<OrderLineEntity> findByOrderId(String orderId);
}
