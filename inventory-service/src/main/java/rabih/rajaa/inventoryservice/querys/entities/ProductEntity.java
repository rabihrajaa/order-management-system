package rabih.rajaa.inventoryservice.querys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rabih.rajaa.commonapi.enums.ProductStatus;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String categoryId;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private Instant createdAt;
    private Instant updatedAt;
}
