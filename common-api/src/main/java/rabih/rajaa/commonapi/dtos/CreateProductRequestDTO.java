package rabih.rajaa.commonapi.dtos;

import lombok.Data;

@Data
public class CreateProductRequestDTO {
    private String name;
    private double price;
    private int quantity;
    private String categoryId;
}
