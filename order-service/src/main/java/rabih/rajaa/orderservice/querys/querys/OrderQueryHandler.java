package rabih.rajaa.orderservice.querys.querys;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabih.rajaa.orderservice.querys.entities.OrderEntity;
import rabih.rajaa.orderservice.querys.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderQueryHandler {

    @Autowired
    private OrderRepository orderRepository;

    @QueryHandler
    public List<OrderEntity> handle(GetAllOrdersQuery query) {
        return orderRepository.findAll();
    }

    @QueryHandler
    public OrderEntity handle(GetOrderByIdQuery query) {
        return orderRepository.findById(query.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @QueryHandler
    public List<OrderEntity> handle(GetOrdersByCustomerQuery query) {
        return orderRepository.findByCustomerId(query.getCustomerId());
    }
}
