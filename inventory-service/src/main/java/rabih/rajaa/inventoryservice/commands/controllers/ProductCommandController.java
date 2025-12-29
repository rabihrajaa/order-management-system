package rabih.rajaa.inventoryservice.commands.controllers;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rabih.rajaa.commonapi.commands.Inventory.CreateCategoryCommand;
import rabih.rajaa.commonapi.commands.Inventory.CreateProductCommand;
import rabih.rajaa.commonapi.commands.Inventory.UpdateProductStatusCommand;
import rabih.rajaa.commonapi.commands.Inventory.UpdateProductStockCommand;
import rabih.rajaa.commonapi.dtos.CreateCategoryRequestDTO;
import rabih.rajaa.commonapi.dtos.CreateProductRequestDTO;
import rabih.rajaa.commonapi.enums.ProductStatus;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/commands/products")
public class ProductCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping("/categories")
    public CompletableFuture<String> createCategory(@RequestBody CreateCategoryRequestDTO request) {
        String categoryId = UUID.randomUUID().toString();
        return commandGateway.send(new CreateCategoryCommand(
                categoryId,
                request.getName(),
                request.getDescription()
        ));
    }

    @PostMapping
    public CompletableFuture<String> createProduct(@RequestBody CreateProductRequestDTO request) {
        String productId = UUID.randomUUID().toString();
        return commandGateway.send(new CreateProductCommand(
                productId,
                request.getName(),
                request.getPrice(),
                request.getQuantity(),
                request.getCategoryId()
        ));
    }

    @PutMapping("/{id}/stock")
    public CompletableFuture<String> updateProductStock(
            @PathVariable String id,
            @RequestParam int quantity,
            @RequestParam String operationType) {
        return commandGateway.send(new UpdateProductStockCommand(
                id,
                quantity,
                operationType
        ));
    }

    @PutMapping("/{id}/status")
    public CompletableFuture<String> updateProductStatus(
            @PathVariable String id,
            @RequestBody ProductStatus status) {
        return commandGateway.send(new UpdateProductStatusCommand(
                id,
                status
        ));
    }
}
