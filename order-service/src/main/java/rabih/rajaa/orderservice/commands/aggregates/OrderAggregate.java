package rabih.rajaa.orderservice.commands.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import rabih.rajaa.commonapi.commands.order.CreateOrderCommand;
import rabih.rajaa.commonapi.commands.order.UpdateOrderStatusCommand;
import rabih.rajaa.commonapi.dtos.OrderLineDTO;
import rabih.rajaa.commonapi.enums.OrderStatus;
import rabih.rajaa.commonapi.events.order.OrderCreatedEvent;
import rabih.rajaa.commonapi.events.order.OrderStatusUpdatedEvent;
import rabih.rajaa.commonapi.exceptions.InvalidOrderStatusException;

import java.util.List;

@Aggregate
public class OrderAggregate {
    @AggregateIdentifier
    private String id;
    private String customerId;
    private String deliveryAddress;
    private List<OrderLineDTO> orderLines;
    private OrderStatus status;
    private double totalAmount;

    public OrderAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        // Calculate total amount
        double total = command.getOrderLines().stream()
                .mapToDouble(line -> line.getQuantity() * line.getUnitPrice() * (1 - line.getDiscount()))
                .sum();

        // Apply event
        AggregateLifecycle.apply(new OrderCreatedEvent(
                command.getId(),
                command.getCustomerId(),
                command.getDeliveryAddress(),
                command.getOrderLines(),
                OrderStatus.CREATED
        ));
    }

    @CommandHandler
    public void handle(UpdateOrderStatusCommand command) {
        // Validate status transition
        validateStatusTransition(command.getStatus());

        AggregateLifecycle.apply(new OrderStatusUpdatedEvent(
                command.getId(),
                command.getStatus()
        ));
    }

    private void validateStatusTransition(OrderStatus newStatus) {
        if (this.status == OrderStatus.CANCELED && newStatus != OrderStatus.CANCELED) {
            throw new InvalidOrderStatusException("Cannot change status of a canceled order");
        }
        if (this.status == OrderStatus.DELIVERED) {
            throw new InvalidOrderStatusException("Cannot change status of a delivered order");
        }
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.id = event.getId();
        this.customerId = event.getCustomerId();
        this.deliveryAddress = event.getDeliveryAddress();
        this.orderLines = event.getOrderLines();
        this.status = event.getStatus();

        // Calculate total
        this.totalAmount = event.getOrderLines().stream()
                .mapToDouble(line -> line.getQuantity() * line.getUnitPrice() * (1 - line.getDiscount()))
                .sum();
    }

    @EventSourcingHandler
    public void on(OrderStatusUpdatedEvent event) {
        this.status = event.getStatus();
    }

    // Getters
    public String getId() { return id; }
    public String getCustomerId() { return customerId; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public List<OrderLineDTO> getOrderLines() { return orderLines; }
    public OrderStatus getStatus() { return status; }
    public double getTotalAmount() { return totalAmount; }
}
