package rabih.rajaa.commonapi.events;

import lombok.Getter;
import rabih.rajaa.commonapi.enums.ProductStatus;

public class ProductCreatedEvent extends BaseEvent<String> {
    @Getter private String name;
    @Getter private double price;
    @Getter private int quantity;
    @Getter private String categoryId;
    @Getter private ProductStatus status;

    public ProductCreatedEvent(String id, String name, double price, int quantity, String categoryId, ProductStatus status) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
        this.status = status;
    }
}

