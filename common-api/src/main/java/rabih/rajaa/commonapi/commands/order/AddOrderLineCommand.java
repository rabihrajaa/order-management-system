package rabih.rajaa.commonapi.commands.order;

import lombok.Getter;
import rabih.rajaa.commonapi.commands.Inventory.BaseCommand;
import rabih.rajaa.commonapi.dtos.OrderLineDTO;

public class AddOrderLineCommand extends BaseCommand<String> {
    @Getter private OrderLineDTO orderLine;

    public AddOrderLineCommand(String id, OrderLineDTO orderLine) {
        super(id);
        this.orderLine = orderLine;
    }
}

