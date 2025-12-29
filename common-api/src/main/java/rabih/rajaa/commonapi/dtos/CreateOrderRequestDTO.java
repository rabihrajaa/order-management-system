package rabih.rajaa.commonapi.dtos;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequestDTO {
    private String customerId;
    private String deliveryAddress;
    private List<OrderLineDTO> orderLines;
}
