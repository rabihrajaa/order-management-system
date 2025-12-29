package rabih.rajaa.orderservice.querys.querys;

import lombok.Getter;

public class GetOrderByIdQuery {
    @Getter
    private String orderId;

    public GetOrderByIdQuery(String orderId) {
        this.orderId = orderId;
    }
}
