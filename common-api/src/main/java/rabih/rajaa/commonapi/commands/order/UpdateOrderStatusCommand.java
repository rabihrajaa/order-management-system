package rabih.rajaa.commonapi.commands.order;

import lombok.Getter;

public class UpdateOrderStatusCommand extends BaseCommand<String> {
    @Getter private OrderStatus status;

    public UpdateOrderStatusCommand(String id, OrderStatus status) {
        super(id);
        this.status = status;
    }
}