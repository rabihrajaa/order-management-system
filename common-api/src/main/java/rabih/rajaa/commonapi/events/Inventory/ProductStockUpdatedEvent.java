package rabih.rajaa.commonapi.events.Inventory;

import lombok.Getter;

public class ProductStockUpdatedEvent extends BaseEvent<String> {
    @Getter private int quantity;
    @Getter private String operationType;
    @Getter private int newStock;

    public ProductStockUpdatedEvent(String id, int quantity, String operationType, int newStock) {
        super(id);
        this.quantity = quantity;
        this.operationType = operationType;
        this.newStock = newStock;
    }
}