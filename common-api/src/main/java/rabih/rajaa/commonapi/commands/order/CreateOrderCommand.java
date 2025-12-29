package rabih.rajaa.commonapi.commands.order;

import lombok.Getter;
import rabih.rajaa.commonapi.commands.Inventory.BaseCommand;
import rabih.rajaa.commonapi.dtos.OrderLineDTO;

import java.util.List;

public class CreateOrderCommand extends BaseCommand<String> {
    @Getter private String customerId;
    @Getter private String deliveryAddress;
    @Getter private List<OrderLineDTO> orderLines;

    public CreateOrderCommand(String id, String customerId, String deliveryAddress, List<OrderLineDTO> orderLines) {
        super(id);
        this.customerId = customerId;
        this.deliveryAddress = deliveryAddress;
        this.orderLines = orderLines;
    }
}



