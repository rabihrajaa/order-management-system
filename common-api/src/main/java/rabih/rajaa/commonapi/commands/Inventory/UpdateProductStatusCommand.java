package rabih.rajaa.commonapi.commands.Inventory;


import lombok.Getter;
import rabih.rajaa.commonapi.enums.ProductStatus;

public class UpdateProductStatusCommand extends BaseCommand<String> {
    @Getter private ProductStatus status;

    public UpdateProductStatusCommand(String id, ProductStatus status) {
        super(id);
        this.status = status;
    }
}
