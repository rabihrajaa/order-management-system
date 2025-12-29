package rabih.rajaa.orderservice.querys.querys;


import lombok.Getter;

public class GetOrdersByCustomerQuery {
    @Getter
    private String customerId;

    public GetOrdersByCustomerQuery(String customerId) {
        this.customerId = customerId;
    }
}
