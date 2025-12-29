package rabih.rajaa.orderservice.querys.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderId;
    private String productId;
    private int quantity;
    private double unitPrice;
    private double discount;
    private double lineTotal;
    private Instant createdAt;
}

