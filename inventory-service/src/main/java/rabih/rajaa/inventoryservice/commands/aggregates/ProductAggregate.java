package rabih.rajaa.inventoryservice.commands.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import rabih.rajaa.commonapi.enums.ProductStatus;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String categoryId;
    private ProductStatus status;

    public ProductAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand command) {
        // Validation
        if (command.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (command.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        // Determine initial status
        ProductStatus initialStatus = command.getQuantity() > 0 ?
                ProductStatus.DISPONIBLE : ProductStatus.RUPTURE;

        // Apply event
        AggregateLifecycle.apply(new ProductCreatedEvent(
                command.getId(),
                command.getName(),
                command.getPrice(),
                command.getQuantity(),
                command.getCategoryId(),
                initialStatus
        ));
    }

    @CommandHandler
    public void handle(UpdateProductStockCommand command) {
        int newStock;

        if ("ADD".equals(command.getOperationType())) {
            newStock = this.quantity + command.getQuantity();
        } else if ("REMOVE".equals(command.getOperationType())) {
            newStock = this.quantity - command.getQuantity();
            if (newStock < 0) {
                throw new InsufficientStockException(
                        "Insufficient stock for product: " + id +
                                ". Available: " + quantity +
                                ", Requested: " + command.getQuantity()
                );
            }
        } else {
            throw new IllegalArgumentException("Invalid operation type");
        }

        // Update status based on new stock
        ProductStatus newStatus = newStock > 0 ?
                ProductStatus.DISPONIBLE : ProductStatus.RUPTURE;

        AggregateLifecycle.apply(new ProductStockUpdatedEvent(
                command.getId(),
                command.getQuantity(),
                command.getOperationType(),
                newStock
        ));

        if (this.status != newStatus) {
            AggregateLifecycle.apply(new ProductStatusUpdatedEvent(
                    command.getId(),
                    newStatus
            ));
        }
    }

    @CommandHandler
    public void handle(UpdateProductStatusCommand command) {
        AggregateLifecycle.apply(new ProductStatusUpdatedEvent(
                command.getId(),
                command.getStatus()
        ));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.price = event.getPrice();
        this.quantity = event.getQuantity();
        this.categoryId = event.getCategoryId();
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(ProductStockUpdatedEvent event) {
        this.quantity = event.getNewStock();
    }

    @EventSourcingHandler
    public void on(ProductStatusUpdatedEvent event) {
        this.status = event.getStatus();
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getCategoryId() { return categoryId; }
    public ProductStatus getStatus() { return status; }
}
