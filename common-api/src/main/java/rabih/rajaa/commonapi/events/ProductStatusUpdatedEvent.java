package rabih.rajaa.commonapi.events;

import lombok.Getter;
import rabih.rajaa.commonapi.enums.ProductStatus;

public class ProductStatusUpdatedEvent extends BaseEvent<String> {
    @Getter private ProductStatus status;

    public ProductStatusUpdatedEvent(String id, ProductStatus status) {
        super(id);
        this.status = status;
    }
}
