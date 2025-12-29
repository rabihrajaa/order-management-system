package rabih.rajaa.orderservice.querys.controllers;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rabih.rajaa.orderservice.querys.entities.OrderEntity;
import rabih.rajaa.orderservice.querys.querys.GetAllOrdersQuery;
import rabih.rajaa.orderservice.querys.querys.GetOrderByIdQuery;
import rabih.rajaa.orderservice.querys.querys.GetOrdersByCustomerQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/orders")
public class OrderQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public CompletableFuture<List<OrderEntity>> getAllOrders() {
        return queryGateway.query(new GetAllOrdersQuery(),
                org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf(OrderEntity.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<OrderEntity> getOrderById(@PathVariable String id) {
        return queryGateway.query(new GetOrderByIdQuery(id),
                org.axonframework.messaging.responsetypes.ResponseTypes.instanceOf(OrderEntity.class));
    }

    @GetMapping("/customer/{customerId}")
    public CompletableFuture<List<OrderEntity>> getOrdersByCustomer(@PathVariable String customerId) {
        return queryGateway.query(new GetOrdersByCustomerQuery(customerId),
                org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf(OrderEntity.class));
    }
}
