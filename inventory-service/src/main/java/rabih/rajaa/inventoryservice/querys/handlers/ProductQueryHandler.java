package rabih.rajaa.inventoryservice.querys.handlers;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rabih.rajaa.inventoryservice.querys.entities.ProductEntity;
import rabih.rajaa.inventoryservice.querys.querys.GetAllProductsQuery;
import rabih.rajaa.inventoryservice.querys.querys.GetProductByIdQuery;
import rabih.rajaa.inventoryservice.querys.querys.GetProductsByCategoryQuery;
import rabih.rajaa.inventoryservice.querys.repository.ProductRepository;

import java.util.List;

@Service
public class ProductQueryHandler {

    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public List<ProductEntity> handle(GetAllProductsQuery query) {
        return productRepository.findAll();
    }

    @QueryHandler
    public ProductEntity handle(GetProductByIdQuery query) {
        return productRepository.findById(query.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @QueryHandler
    public List<ProductEntity> handle(GetProductsByCategoryQuery query) {
        return productRepository.findByCategoryId(query.getCategoryId());
    }
}
