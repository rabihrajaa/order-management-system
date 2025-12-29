package rabih.rajaa.inventoryservice.querys.controllers;


import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rabih.rajaa.inventoryservice.querys.entities.ProductEntity;
import rabih.rajaa.inventoryservice.querys.querys.GetAllProductsQuery;
import rabih.rajaa.inventoryservice.querys.querys.GetProductByIdQuery;
import rabih.rajaa.inventoryservice.querys.querys.GetProductsByCategoryQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/products")
public class ProductQueryController {

    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public CompletableFuture<List<ProductEntity>> getAllProducts() {
        return queryGateway.query(new GetAllProductsQuery(),
                org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf(ProductEntity.class));
    }

    @GetMapping("/{id}")
    public CompletableFuture<ProductEntity> getProductById(@PathVariable String id) {
        return queryGateway.query(new GetProductByIdQuery(id),
                org.axonframework.messaging.responsetypes.ResponseTypes.instanceOf(ProductEntity.class));
    }

    @GetMapping("/category/{categoryId}")
    public CompletableFuture<List<ProductEntity>> getProductsByCategory(@PathVariable String categoryId) {
        return queryGateway.query(new GetProductsByCategoryQuery(categoryId),
                org.axonframework.messaging.responsetypes.ResponseTypes.multipleInstancesOf(ProductEntity.class));
    }
}
