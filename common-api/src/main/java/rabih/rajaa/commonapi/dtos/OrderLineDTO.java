package rabih.rajaa.commonapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDTO {
    private String productId;
    private int quantity;
    private double unitPrice;
    private double discount;
}





