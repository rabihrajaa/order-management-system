package rabih.rajaa.commonapi.events.order;

import lombok.Getter;
import rabih.rajaa.commonapi.enums.OrderStatus;
import rabih.rajaa.commonapi.events.Inventory.BaseEvent;

public class OrderStatusUpdatedEvent extends BaseEvent<String> {
    @Getter private OrderStatus status;

    public OrderStatusUpdatedEvent(String id, OrderStatus status) {
        super(id);
        this.status = status;
    }
}

