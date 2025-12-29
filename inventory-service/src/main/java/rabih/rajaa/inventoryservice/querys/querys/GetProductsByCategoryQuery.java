package rabih.rajaa.inventoryservice.querys.querys;


import lombok.Getter;

public class GetProductsByCategoryQuery {
    @Getter
    private String categoryId;

    public GetProductsByCategoryQuery(String categoryId) {
        this.categoryId = categoryId;
    }
}
