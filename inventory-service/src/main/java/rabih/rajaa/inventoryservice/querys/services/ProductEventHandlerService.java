package rabih.rajaa.inventoryservice.querys.services;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabih.rajaa.commonapi.events.Inventory.CategoryCreatedEvent;
import rabih.rajaa.commonapi.events.Inventory.ProductCreatedEvent;
import rabih.rajaa.commonapi.events.Inventory.ProductStatusUpdatedEvent;
import rabih.rajaa.commonapi.events.Inventory.ProductStockUpdatedEvent;
import rabih.rajaa.inventoryservice.querys.entities.CategoryEntity;
import rabih.rajaa.inventoryservice.querys.entities.ProductEntity;
import rabih.rajaa.inventoryservice.querys.repository.CategoryRepository;
import rabih.rajaa.inventoryservice.querys.repository.ProductRepository;

import java.time.Instant;

@Service
public class ProductEventHandlerService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @EventHandler
    public void on(CategoryCreatedEvent event) {
        CategoryEntity category = new CategoryEntity(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getTimestamp()
        );
        categoryRepository.save(category);
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity product = new ProductEntity(
                event.getId(),
                event.getName(),
                event.getPrice(),
                event.getQuantity(),
                event.getCategoryId(),
                event.getStatus(),
                event.getTimestamp(),
                event.getTimestamp()
        );
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductStockUpdatedEvent event) {
        ProductEntity product = productRepository.findById(event.getId()).orElseThrow();
        product.setQuantity(event.getNewStock());
        product.setUpdatedAt(event.getTimestamp());
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductStatusUpdatedEvent event) {
        ProductEntity product = productRepository.findById(event.getId()).orElseThrow();
        product.setStatus(event.getStatus());
        product.setUpdatedAt(event.getTimestamp());
        productRepository.save(product);
    }
}
