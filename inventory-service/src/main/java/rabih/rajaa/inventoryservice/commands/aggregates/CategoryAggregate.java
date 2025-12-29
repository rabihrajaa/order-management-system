package rabih.rajaa.inventoryservice.commands.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import rabih.rajaa.commonapi.commands.Inventory.CreateCategoryCommand;
import rabih.rajaa.commonapi.events.Inventory.CategoryCreatedEvent;

@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String description;

    public CategoryAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand command) {
        // Validation
        if (command.getName() == null || command.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }

        // Apply event
        AggregateLifecycle.apply(new CategoryCreatedEvent(
                command.getId(),
                command.getName(),
                command.getDescription()
        ));
    }

    @EventSourcingHandler
    public void on(CategoryCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        this.description = event.getDescription();
    }
}