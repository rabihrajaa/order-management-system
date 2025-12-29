package rabih.rajaa.commonapi.events.order;


import lombok.Getter;
import rabih.rajaa.commonapi.dtos.OrderLineDTO;
import rabih.rajaa.commonapi.enums.OrderStatus;
import rabih.rajaa.commonapi.events.Inventory.BaseEvent;

import java.util.List;

public class OrderCreatedEvent extends BaseEvent<String> {
    @Getter
    private String customerId;
    @Getter private String deliveryAddress;
    @Getter private List<OrderLineDTO> orderLines;
    @Getter private OrderStatus status;

    public OrderCreatedEvent(String id, String customerId, String deliveryAddress, List<OrderLineDTO> orderLines, OrderStatus status) {
        super(id);
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.orderLines = orderLines;
        this.status = status;
    }
}


