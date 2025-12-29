package rabih.rajaa.orderservice.querys.services;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rabih.rajaa.commonapi.events.order.OrderCreatedEvent;
import rabih.rajaa.commonapi.events.order.OrderStatusUpdatedEvent;
import rabih.rajaa.orderservice.querys.entities.OrderEntity;
import rabih.rajaa.orderservice.querys.entities.OrderLineEntity;
import rabih.rajaa.orderservice.querys.repositories.OrderLineRepository;
import rabih.rajaa.orderservice.querys.repositories.OrderRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderEventHandlerService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderLineRepository orderLineRepository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        // Calculate total amount
        double totalAmount = event.getOrderLines().stream()
                .mapToDouble(line -> line.getQuantity() * line.getUnitPrice() * (1 - line.getDiscount()))
                .sum();

        // Save order
        OrderEntity order = new OrderEntity(
                event.getId(),
                event.getCustomerId(),
                event.getDeliveryAddress(),
                event.getStatus(),
                totalAmount,
                event.getTimestamp(),
                null,
                event.getTimestamp()
        );
        orderRepository.save(order);

        List<OrderLineEntity> orderLines = event.getOrderLines().stream()
                .map(dto -> new OrderLineEntity(
                        null,
                        event.getId(),
                        dto.getProductId(),
                        dto.getQuantity(),
                        dto.getUnitPrice(),
                        dto.getDiscount(),
                        dto.getQuantity() * dto.getUnitPrice() * (1 - dto.getDiscount()),
                        event.getTimestamp()
                ))
                .collect(Collectors.toList());

        orderLineRepository.saveAll(orderLines);
    }

    @EventHandler
    public void on(OrderStatusUpdatedEvent event) {
        OrderEntity order = orderRepository.findById(event.getId()).orElseThrow();
        order.setStatus(event.getStatus());
        order.setUpdatedAt(event.getTimestamp());

        // If delivered, set delivery date
        if (event.getStatus().name().equals("DELIVERED")) {
            order.setDeliveryDate(event.getTimestamp());
        }

        orderRepository.save(order);
    }
}
