package rabih.rajaa.commonapi.commands.Inventory;

import lombok.Getter;

public class UpdateProductStockCommand extends BaseCommand<String> {
    @Getter private int quantity;
    @Getter private String operationType; // ADD, REMOVE

    public UpdateProductStockCommand(String id, int quantity, String operationType) {
        super(id);
        this.quantity = quantity;
        this.operationType = operationType;
    }
}
