package rabih.rajaa.commonapi.events.order;

import lombok.Getter;
import rabih.rajaa.commonapi.dtos.OrderLineDTO;
import rabih.rajaa.commonapi.events.Inventory.BaseEvent;

public class OrderLineAddedEvent extends BaseEvent<String> {
    @Getter private OrderLineDTO orderLine;

    public OrderLineAddedEvent(String id, OrderLineDTO orderLine) {
        super(id);
        this.orderLine = orderLine;
    }
}
