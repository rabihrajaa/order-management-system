package rabih.rajaa.orderservice.commands.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rabih.rajaa.commonapi.commands.order.CreateOrderCommand;
import rabih.rajaa.commonapi.commands.order.UpdateOrderStatusCommand;
import rabih.rajaa.commonapi.dtos.CreateOrderRequestDTO;
import rabih.rajaa.commonapi.enums.OrderStatus;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/orders")
public class OrderCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public CompletableFuture<String> createOrder(@RequestBody CreateOrderRequestDTO request) {
        String orderId = UUID.randomUUID().toString();
        return commandGateway.send(new CreateOrderCommand(
                orderId,
                request.getCustomerId(),
                request.getDeliveryAddress(),
                request.getOrderLines()
        ));
    }

    @PutMapping("/{id}/status")
    public CompletableFuture<String> updateOrderStatus(
            @PathVariable String id,
            @RequestBody OrderStatus status) {
        return commandGateway.send(new UpdateOrderStatusCommand(
                id,
                status
        ));
    }
}
