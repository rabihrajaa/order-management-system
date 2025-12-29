package rabih.rajaa.inventoryservice.querys.querys;


import lombok.Getter;

public class GetProductByIdQuery {
    @Getter
    private String productId;

    public GetProductByIdQuery(String productId) {
        this.productId = productId;
    }
}